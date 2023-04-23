package ru.stqa.pft.addressbook.tests.contact;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletionTests() {
        app.getNavigationHelper().gotoContactPage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("asd2", "asd1", "cbndfgsdfg wwert1", "1231231233", "asd1@sa.asd"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deletedSelectContact();
    }
}
