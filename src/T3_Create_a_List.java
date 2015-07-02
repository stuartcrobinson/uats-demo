import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T3_Create_a_List {
    public T3_Create_a_List() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
	System.out.println("T3_Create_a_List");
    }
    WebElement listNameField;

    @Test(priority = 1)
    public void goto_Create_a_List() throws InterruptedException {
	Main.driver.findElement(By.linkText("Create a List")).click();

	listNameField = SeleniumHelper.waitForElementBy(Main.driver, By.id("sListName"));	    //input field


	Assert.assertEquals(Main.driver.getTitle().replaceAll(" ", ""), "iContact  : Manage Lists".replaceAll(" ", ""));

    }

    @Test(priority = 2)
    public void enter_list_name() {

	listNameField.click();
	for (int i = 1; i <= 8; i++)
	    listNameField.sendKeys(Keys.BACK_SPACE);
	listNameField.sendKeys(Main.testListName);

	assertEquals(true, true);
    }

    @Test(priority = 3)
    public void enter_list_description() throws InterruptedException {

	WebElement listDescriptionField = SeleniumHelper.waitForElementBy(Main.driver, By.id("sListDescription"));	    //input field
	listDescriptionField.click();
	listDescriptionField.sendKeys(Main.testListDescription);

	assertEquals(true, true);
    }

    @Test(priority = 4)
    public void check_AlwaysVisibleOnManageYourSubscriptionPage() {

	Main.driver.findElement(By.id("mysvisible")).click();

	//id:  mysvisible
	assertEquals(true, true);
    }


    @Test(priority = 5)
    public void click_Save() throws InterruptedException {

	Main.driver.findElement(By.id("submitSaveReturn")).click();

	WebElement e = SeleniumHelper.waitForElementByXpath(Main.driver, "//*/h2[text()=\"My Lists\"]");

	System.out.println("My Lists thing should be: " + e.getText() + ", tag: " + e.getTagName());

	//id:  mysvisible
	Assert.assertEquals(Main.driver.getTitle().replaceAll(" ", ""), "iContact  : Manage Lists".replaceAll(" ", ""));
    }


    @Test(priority = 6)
    public void confirm_list_creation() throws InterruptedException {
	System.out.println("	confirm_list_creation");

	String pageSource = Main.driver.getPageSource();

	System.out.println("pageSource.contains(Main.testListName? " + pageSource.contains(Main.testListName));

	assertEquals(true, pageSource.contains(Main.testListName));
    }


    @Test(priority = 7)
    public void close_AssistanceBubbles() throws InterruptedException {
	System.out.println("	close_AssistanceBubbles");

	List<WebElement> bubbles = Main.driver.findElements(By.xpath("//img[@src=\"https://app.icontact.com/icp/static/human/images/icons/close-icon-12.png\"]"));


	Thread.sleep(500);	    //TODO remove these sleeps!!!  find a better way
	bubbles.get(0).click();
	SeleniumHelper.waitForElementByXpath(Main.driver, "//*/p[@style=\"display: none;\"]");
	Thread.sleep(500);
	bubbles.get(1).click();
	Thread.sleep(500);
	List list = SeleniumHelper.waitForNElementsBy(Main.driver, 2, By.xpath("//*/p[@style=\"display: none;\"]"));
	Thread.sleep(500);
	System.out.println(list.size() + " hidden objects in bubbles list");

	assertEquals(list.size(), 2);
    }
}
