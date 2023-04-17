package ru.stqa.pft.addressbook.Group;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.Group.TestBase;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
