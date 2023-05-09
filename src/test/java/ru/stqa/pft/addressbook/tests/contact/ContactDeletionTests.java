package ru.stqa.pft.addressbook.tests.contact;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("asd4").withLastname("asd4")
                    .withAddress("cbndfgsdfg").withPhone("1231231233")
                    .withEmail1("asd1@sa.asd"));
        }
    }

    @Test
    public void testContactDeletionTests() {
        Contacts before = app.contact().all();
        ContactData deleteContact  = before.iterator().next();
        app.contact().delete(deleteContact);
        app.goTo().contactPage();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deleteContact)));
    }

}
