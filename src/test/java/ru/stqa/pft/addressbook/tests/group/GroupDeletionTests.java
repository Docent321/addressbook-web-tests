package ru.stqa.pft.addressbook.tests.group;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.tests.TestBase;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }
}
