import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T4_Add_One_Email {
    public T4_Add_One_Email() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
	System.out.println("T4_Add_One_Email");
    }
    String newListId;

    @Test(priority = 1)
    public void click_Add_for_new_list() throws InterruptedException {
	System.out.println("	click_Add_for_new_list");

	WebElement a = Main.driver.findElement(By.linkText(Main.testListName));

	String url = a.getAttribute("href");

	String specialNumber = url.replace("https://app.icontact.com/icp/core/mycontacts/lists/edit/", "");
	specialNumber = specialNumber.replaceAll("/.*", "");

	Main.testListIDNumber = specialNumber;

	//https://app.icontact.com/icp/core/mycontacts/lists/edit/28402/?token=53524ce41b8b0d119687bacfa9defbea

	String Add_link_search_str = "https://app.icontact.com/icp/core/mycontacts/contacts/add/" + specialNumber + "?token=";
//	https://app.icontact.com/icp/core/mycontacts/contacts/add/28402?token=53524ce41b8b0d119687bacfa9defbea

	System.out.println("Add_link_search_str:");
	System.out.println(Add_link_search_str);

	WebElement Add_link = Main.driver.findElement(By.xpath("//*/a[contains(@href, \"" + Add_link_search_str + "\")]"));

	System.out.println(Add_link + " Add_link");

	Add_link.click();

//	
//	
//	WebElement newRow = Main.driver.findElement(By.xpath("//*/tr[@class=\"row new\"]"));
//
//	String newListId = newRow.getAttribute("id");
//
//	System.out.println("new list id = " + newListId);
//
//	Main.driver.findElement(By.xpath("//*/tr[@class=\"row new\"]//a[text()=\"Add\"]")).click();

	SeleniumHelper.waitForElementBy(Main.driver, By.linkText("Add Contacts One by One"));

	System.out.println(Main.driver.getTitle() + " == iContact  : Add Subscribers ??");
	Assert.assertEquals(Main.driver.getTitle().replaceAll(" ", ""), "iContact  : Add Subscribers".replaceAll(" ", ""));
    }

    @Test(priority = 2)
    public void click_AddContactsOneByOne() throws InterruptedException {
	System.out.println("	click_AddContactsOneByOne");

	Main.driver.findElement(By.linkText("Add Contacts One by One")).click();

	SeleniumHelper.waitForElementByXpath(Main.driver, "//h2[text()=\"Add One Contact\"]");

	Assert.assertEquals(Main.driver.getTitle().replaceAll(" ", ""), "iContact  : Add One Subscriber".replaceAll(" ", ""));
    }

    @Test(priority = 3)
    public void enter_email() throws InterruptedException {
	System.out.println("	enter_email");

	WebElement emailInput = Main.driver.findElement(By.id("sEmail"));
	emailInput.click();
	emailInput.sendKeys(Main.customerEmailAddress);

	assertEquals(true, true);
    }

//    @Test(priority = 4)
//    public void select_the_new_list() {
//
//
//    WebElement listNameLabel = Main.driver.findElement(By.xpath("//label[text()=\"" + Main.testListName + "\"]"));
//    String checkBoxID = listNameLabel.getAttribute("for");
//
//	System.out.println(checkBoxID + ":  checkBoxID for list: " + Main.testListName);
//
//	WebElement listCheckBox = Main.driver.findElement(By.id(checkBoxID));	//aGroupIds-28301
//
//    Main.listGroupIDNumber  = checkBoxID.replace("aGroupIDs-", "");
//
//	listCheckBox.click();
//
//	assertEquals(true, true);
//    }

    @Test(priority = 5)
    public void check_ReusePreviousEmail() {
	System.out.println("	check_ReusePreviousEmail");

	Main.driver.findElement(By.id("bReuseConfirmation")).click();

	WebElement addContactAndSendEmailButton = Main.driver.findElement(By.id("sAddContactSendEmail"));

	String buttonStyle = addContactAndSendEmailButton.getAttribute("style");

	assertEquals(buttonStyle, "display: inline-block;");
    }


    @Test(priority = 6)
    public void click_addContactAndSendEmail() throws InterruptedException {
	System.out.println("	click_addContactAndSendEmail");

	Main.driver.findElement(By.id("sAddContactSendEmail")).click();

	WebElement e = SeleniumHelper.waitForElementByXpath(Main.driver, "//div[@class=\"divMessage divInfoMessage addNotification\"]");

	System.out.println("good message? " + e);

	String source = Main.driver.getPageSource();

//	SeleniumHelper.waitForElementByXpath(Main.driver, "//*/h2[text()=\"My Lists\"]");

	//id:  mysvisible
	Assert.assertEquals(true, source.contains(Main.customerEmailAddress) && source.contains("will be added to 1 list on confirmation"));

    }

}
