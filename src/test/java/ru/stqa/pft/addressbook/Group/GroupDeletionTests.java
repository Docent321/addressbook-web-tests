package ru.stqa.pft.addressbook.Group;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }
}
