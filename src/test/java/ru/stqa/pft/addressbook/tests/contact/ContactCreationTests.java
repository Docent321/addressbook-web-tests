package ru.stqa.pft.addressbook.tests.contact;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }



  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    app.goTo().contactPage();
    Contacts before = app.db().contacts();
    app.contact().create(contact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }

  @Test
  public void testContactCreationAddIcon(){
    app.goTo().contactPage();
    app.contact().initContactCreation();
    File photo = new File("src/test/resources/icon.png");
    app.contact().fillContactForm(
            new ContactData().withFirstname("F_name").withLastname("L_name")
                    .withAddress("cbndfgsdfg").withPhone("1231231233")
                    .withMobil("321 - 4").withWork("08778")
                    .withEmail1("asd1@sa.asd").withEmail2("asd2@sa.asd")
                    .withEmail3("asd3@sa.asd").withPhoto(photo));
    app.contact().submitContactCreation();
    app.contact().returnToContactPage();
  }
}
