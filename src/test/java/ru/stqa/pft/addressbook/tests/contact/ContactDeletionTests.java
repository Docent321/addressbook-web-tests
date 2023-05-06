package ru.stqa.pft.addressbook.tests.contact;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;
import java.util.Set;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("asd4").withLastname("asd4")
                    .withAddress("cbndfgsdfg").withPhone("1231231233")
                    .withEmail("asd1@sa.asd"));
        }
    }

    @Test
    public void testContactDeletionTests() {
        Set<ContactData> before = app.contact().all();
        ContactData deleteContact  = before.iterator().next();
        app.contact().delete(deleteContact);
        app.goTo().contactPage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deleteContact);
        Assert.assertEquals(after, before);
    }

}
