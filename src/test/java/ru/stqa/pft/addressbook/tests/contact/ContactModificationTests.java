package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("asd4", "asd4", "cbndfgsdfg wwert1", "1231231233", "asd1@sa.asd"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoContactPage();
    }
}
