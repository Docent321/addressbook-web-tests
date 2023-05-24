package ru.stqa.pft.addressbook.tests;



import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.IOException;
import java.util.stream.Collectors;


public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "CHROME"));


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }


    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c.getId()).withFirstname(c.getFirstname())
                            .withLastname(c.getLastname()).withAddress(c.getAddress()).withEmail1(c.getEmail1()))
                    .collect(Collectors.toSet())));
        }
    }

}
