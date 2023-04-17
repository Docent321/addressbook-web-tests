package ru.stqa.pft.addressbook.tests.contact;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.tests.TestBase;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletionTests() {
        app.gotoContact();
        app.selectContact();
        app.deletedSelectContact();
    }
}
