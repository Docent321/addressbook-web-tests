package ru.stqa.pft.addressbook.Group;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.TestBase;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }
}
