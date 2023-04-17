package ru.stqa.pft.addressbook.tests.contact;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.tests.TestBase;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletionTests() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deletedSelectContact();
    }
}
