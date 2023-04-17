package ru.stqa.pft.addressbook.Contact;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.TestBase;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    initContactCreation();
    fillContactForm(new ContactData("asd1", "asd1", "cbndfgsdfg wwert1", "1231231233", "asd1@sa.asd"));
    submitContactCreation();
    gotoContactPage();
  }
}
