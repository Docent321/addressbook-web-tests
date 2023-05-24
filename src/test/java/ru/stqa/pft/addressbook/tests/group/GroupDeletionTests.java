package ru.stqa.pft.addressbook.tests.group;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Name"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deleteGroup  = before.iterator().next();
    app.group().delete(deleteGroup);
    Groups after = app.db().groups();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deleteGroup)));
    verifyGroupListInUI();
  }
}
