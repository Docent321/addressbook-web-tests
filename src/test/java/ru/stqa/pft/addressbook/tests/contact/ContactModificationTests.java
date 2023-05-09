package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("asd4").withLastname("asd4")
                    .withAddress("cbndfgsdfg").withPhone("1231231233")
                    .withEmail1("asd1@sa.asd").withEmail2("asd2@sa.asd")
                    .withEmail3("asd3@sa.asd"));
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = app.contact().all();
        ContactData modifyContact  = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId())
                .withFirstname("asd4").withLastname("asd4")
                .withAddress("cbndfgsdfg").withPhone("1231231233")
                .withMobil("321 - 4").withWork("08778")
                .withEmail1("asd1@sa.asd").withEmail2("asd2@sa.asd")
                .withEmail3("asd3@sa.asd");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(before.size(), after.size());
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    }
}
