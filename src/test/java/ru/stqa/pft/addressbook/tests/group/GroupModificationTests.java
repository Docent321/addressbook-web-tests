package ru.stqa.pft.addressbook.tests.group;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Name"));
        }
    }

    @Test
    public void testGroupModification () {
        Groups before = app.db().groups();
        GroupData modifyGroup  = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifyGroup.getId()).withName("N_Name").withHeader("N_Header").withFooter("N_Footer");
        app.goTo().groupPage();
        app.group().modify(group);
        Groups after = app.db().groups();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifyGroup).withAdded(group)));
    }

}
