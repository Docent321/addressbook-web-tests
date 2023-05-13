package ru.stqa.pft.addressbook.tests.group;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("test1").withHeader("test1").withFooter("test1")});
    list.add(new Object[] {new GroupData().withName("test2").withHeader("test2").withFooter("test2")});
    list.add(new Object[] {new GroupData().withName("test3").withHeader("test3").withFooter("test3")});
    return list.listIterator();
  }

  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
