import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T6_Create_An_Email {
    public T6_Create_An_Email() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
	System.out.println("T6_Create_An_Email");
    }
    String newListId;

    @Test(priority = 1)
    public void click_Email() throws InterruptedException {


	Main.driver.findElement(By.linkText("Email")).click();

	SeleniumHelper.waitForElementBy(Main.driver, By.linkText("Create a Message"));

	Assert.assertEquals(Main.driver.getTitle().replaceAll(" ", ""), "iContact  : Create".replaceAll(" ", ""));
    }

    @Test(priority = 2)
    public void click_CreateAMessage() throws InterruptedException {

	Main.driver.findElement(By.linkText("Create a Message")).click();

	SeleniumHelper.waitForElementBy(Main.driver, By.linkText("Create Text-Only Email"));

	Assert.assertEquals(Main.driver.getTitle().replaceAll(" ", ""), "iContact  : Create an Email Message".replaceAll(" ", ""));
    }

    @Test(priority = 3)
    public void click_CreateTextOnlyEmail() throws InterruptedException {

	Main.driver.findElement(By.linkText("Create Text-Only Email")).click();

	SeleniumHelper.waitForElementByXpath(Main.driver, "//h2[text()=\"Create an Email Message\"");
	assertEquals(true, true);
    }

    @Test(priority = 4)
    public void edit_Email_Subject() throws InterruptedException {

	WebElement subjectField = SeleniumHelper.waitForElementBy(Main.driver, By.id("subject"));

	subjectField.click();
	for (int i = 1; i <= 13; i++)
	    subjectField.sendKeys(Keys.BACK_SPACE);
	subjectField.sendKeys(Main.testEmailSubject);

	assertEquals(true, true);
    }

    @Test(priority = 5)
    public void edit_Email_MessageName() throws InterruptedException {

	WebElement messageNameField = SeleniumHelper.waitForElementBy(Main.driver, By.id("refname"));

	messageNameField.click();
	messageNameField.sendKeys(Main.testEmailMessageName);

	assertEquals(true, true);
    }

    //sTextBody

    @Test(priority = 6)
    public void edit_Email_Content() throws InterruptedException {

	WebElement emailContentField = SeleniumHelper.waitForElementBy(Main.driver, By.id("sTextBody"));

	emailContentField.click();
	emailContentField.sendKeys(Main.testEmailContent);

	assertEquals(true, true);
    }

    //BROKE
    @Test(priority = 7)
    public void click_TestMessage() throws InterruptedException {
	Main.driver.findElement(By.id("testMessagePlainText")).click();

	WebElement sendTestMessageDialog;
	SeleniumHelper.waitForElementBy(Main.driver, By.xpath("//*[@style=\"display: block; top: 100px; left: 557px;\"]"));
	WebElement testEmailField = SeleniumHelper.waitForElementBy(Main.driver, By.id("sEmail"));

	Main.driver.findElement(By.id("sEmail")).click();
	Main.driver.findElement(By.id("sEmail")).click();
	Main.driver.findElement(By.id("sEmail")).sendKeys("albertkrivohlavek@gmail.com");

	Main.driver.findElement(By.id("sendTestSubmit")).click();

    }


    //BROKE
    @Test(priority = 8)
    public void confirm_ReceiptOfTestEmail() throws InterruptedException {

	String host = "pop.gmail.com";// change accordingly
	String mailStoreType = "pop3";
	String username = "albertkrivohlavek@gmail.com";// change accordingly
	String password = "awefawef";// change accordingly

	int mailCheckTimeoutMillis = 20 * 1000;

	long time0 = (new Date()).getTime();

	for (; (new Date()).getTime() - time0 < mailCheckTimeoutMillis; Thread.sleep(1000)) {


	    String subjects = CheckingMails.getEmailSubjects(host, mailStoreType, username, password);

	    if (subjects.contains(Main.testEmailSubject)) {
		assertEquals(true, true);
		return;
	    }
	}
	assertEquals(false, true);

    }


}
