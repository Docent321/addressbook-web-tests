package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0){
            File photo = new File("src/test/resources/icon.png");
            app.contact().create(new ContactData()
                    .withFirstname("F_name").withLastname("L_name")
                    .withAddress("cbndfgsdfg").withPhone("1231231233")
                    .withMobil("321 - 4").withWork("08778")
                    .withEmail1("asd1@sa.asd").withEmail2("asd2@sa.asd")
                    .withEmail3("asd3@sa.asd").withPhoto(photo));
        }
    }

    @Test
    public void testContactDeletionTests() {
        Contacts before = app.db().contacts();
        ContactData deleteContact  = before.iterator().next();
        app.contact().delete(deleteContact);
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deleteContact)));
        verifyContactListInUI();
    }

}
