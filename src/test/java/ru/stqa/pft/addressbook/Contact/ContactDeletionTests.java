package ru.stqa.pft.addressbook.Contact;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.TestBase;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletionTests() throws Exception {
        gotoContact();
        selectContact();
        deletedSelectContact();
    }
}
