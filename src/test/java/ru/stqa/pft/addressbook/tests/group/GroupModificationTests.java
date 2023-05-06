package ru.stqa.pft.addressbook.tests.group;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Set;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("Name"));
        }
    }

    @Test
    public void testGroupModification () {
        Set<GroupData> before = app.group().all();
        GroupData modifyGroup  = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifyGroup.getId()).withName("N_Name").withHeader("N_Header").withFooter("N_Footer");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifyGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }

}
