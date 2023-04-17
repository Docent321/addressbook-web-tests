package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.tests.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.initContactCreation();
    app.fillContactForm(new ContactData("asd1", "asd1", "cbndfgsdfg wwert1", "1231231233", "asd1@sa.asd"));
    app.submitContactCreation();
    app.gotoContactPage();
  }
}
