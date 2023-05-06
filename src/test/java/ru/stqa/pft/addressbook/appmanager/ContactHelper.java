package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());
    }

    public void initContactCreation() {
      click(By.linkText("add new"));
    }

    public void deletedSelectContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void returnToContactPage() {
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData Contact) {
        initContactCreation();
        fillContactForm(Contact);
        submitContactCreation();
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        returnToContactPage();
    }

    public void delete(ContactData Contact) {
        selectContactById(Contact.getId());
        deletedSelectContact();
    }

    public int getContactCount() {
        return wd.findElements(By.name("entry")).size();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withLastname(lastName).withFirstname(firstName));
        }
        return contacts;
    }

}
