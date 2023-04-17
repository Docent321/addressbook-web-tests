package ru.stqa.pft.addressbook.Group;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.TestBase;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Name", "Header", "Footer"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
