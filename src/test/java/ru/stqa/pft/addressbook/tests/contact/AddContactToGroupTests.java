package ru.stqa.pft.addressbook.tests.contact;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    private void precondition() {
        if (app.db().contacts().size() == 0) {
            app.goTo().groupPage();
            if (app.db().groups().size() == 0) {
                app.group().create(new GroupData().withName("Name").withHeader("Header").withFooter("Footer"));
            }
            app.contact().create(new ContactData()
                    .withFirstname("F_name").withLastname("L_name")
                    .withAddress("cbndfgsdfg").withPhone("1231231233")
                    .withMobil("321 - 4").withWork("08778")
                    .withEmail1("asd1@sa.asd").withEmail2("asd2@sa.asd")
                    .withEmail3("asd3@sa.asd"));
        }
    }

    @Test
    public void testContactAddToGroup() {
        Contacts beforeContact = app.db().contacts();
        ContactData contact = beforeContact.iterator().next();
        Groups beforeGroup = app.db().groups();
        GroupData group = beforeGroup.iterator().next();
        if (!contact.getGroups().isEmpty() && contact.getGroups().contains(group)) {
            app.contact().removeGroup(contact, group);
        }
        Groups beforeLinkedGroup = app.db().contacts().stream().iterator().next().getGroups();
        app.contact().addToGroup(contact, group);
        Groups afterLinkedGroup = app.db().contacts().stream().iterator().next().getGroups();

        assertThat(afterLinkedGroup, equalTo(beforeLinkedGroup.withAdded(group)));
    }

    @Test
    public void testContactRemoveToGroup() {
        Contacts beforeContact = app.db().contacts();
        ContactData contact = beforeContact.iterator().next();
        Groups beforeGroup = app.db().groups();
        GroupData group = beforeGroup.iterator().next();
        if (contact.getGroups().isEmpty()) {
            app.contact().addToGroup(contact, group);
        }
        Groups beforeLinkedGroup = app.db().contacts().stream().iterator().next().getGroups();

        app.contact().removeGroup(contact, group);
        Contacts afterContact = app.db().contacts();
        Groups afterGroup = app.db().groups();

        Groups afterLinkedGroup = app.db().contacts().stream().iterator().next().getGroups();
        System.out.println("Группы связанные после " + afterLinkedGroup);

        System.out.println(beforeContact);
        System.out.println(afterContact);

        System.out.println(beforeLinkedGroup);
        System.out.println(afterLinkedGroup);

        System.out.println(beforeGroup);
        System.out.println(afterGroup);

        assertThat(afterContact, equalTo(beforeContact.withAdded(contact)));
        assertThat(afterLinkedGroup.withAdded(group), equalTo(beforeLinkedGroup));
    }
}
