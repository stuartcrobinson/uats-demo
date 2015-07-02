import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T7_Send_The_Email {
    public T7_Send_The_Email() {
    }


    @BeforeClass
    public static void setUpClass() throws Exception {
	System.out.println("T7_Send_The_Email");
    }

    @Test(priority = 2)
    public void click_ProceedToSend_Green() throws InterruptedException {
	Main.driver.findElement(By.id("buttonProceed")).click();
	SeleniumHelper.waitForElementByXpath(Main.driver, "//*/h2[text()=\"Select Your Target Audience\"]");

	//check boxes in adding contacts:  aGroupIds-28301
    }

    @Test(priority = 3)
    public void select_List() throws InterruptedException {


//    WebElement listNameLabel = Main.driver.findElement(By.xpath("//label[text()=\"" + Main.testListName + "\"]"));
//    String checkBoxID = listNameLabel.getAttribute("for");
//
//	System.out.println(checkBoxID + ":  checkBoxID for list: " + Main.testListName);
//
//	WebElement listCheckBox = Main.driver.findElement(By.id(checkBoxID));	//aGroupIds-28301
//
//    Main.listGroupIDNumber  = checkBoxID.replace("aGroupIDs-", "");
//
	String checkBoxID = "bListGroups_" + Main.listGroupIDNumber;

	Thread.sleep(1000);
	Main.driver.findElement(By.xpath("//label[contains(text(), \"" + Main.testListName + "\")]")).click();

	Thread.sleep(1000);


    }

    @Test(priority = 4)
    public void click_ProceedToSend_Blue() throws InterruptedException {

	Main.driver.findElement(By.id("buttonMessageSchedule")).click();

	SeleniumHelper.waitForElementBy(Main.driver, By.id("textinputSpamFreeInitials"));

	System.out.println(Main.driver.getTitle() + " == iContact  : Send ??");
	Assert.assertEquals(Main.driver.getTitle().replaceAll("\\s", ""), "iContact  : Send".replaceAll("\\s", ""));

//	Main.driver.findElement(By.id("textinputSpamFreeInitials")).sendKeys(Keys.TAB);

    }


    @Test(priority = 5)
    public void initial_noSpamPromise() throws InterruptedException {

	Main.driver.findElement(By.id("textinputSpamFreeInitials")).click();
	Main.driver.findElement(By.id("textinputSpamFreeInitials")).sendKeys("ak" + Keys.TAB);
    }

    @Test(priority = 6)
    public void click_DeliverImmediately() throws InterruptedException {

	Main.driver.findElement(By.id("submitbuttonDeliverNow")).click();

	SeleniumHelper.waitForElementByXpath(Main.driver, "//*/a[text()=\"Cancel this message.\"]");

	//iContact  : Pending Messages
	System.out.println(Main.driver.getTitle() + " == iContact  : Pending Messages ??");
	Assert.assertEquals(Main.driver.getTitle().replaceAll("\\s", ""), "iContact  : Pending Messages".replaceAll("\\s", ""));
    }

    @Test(priority = 7)
    public void confirm_CustomerReceiptOfEmail() throws InterruptedException {

	String host = "pop.aol.com";
	String mailStoreType = "pop3";
	String username = Main.customerEmailAddress;
	String password = Main.customerEmailPassword;

	String subjects = CheckingMails.getEmailSubjects(host, mailStoreType, username, password);

	int count = 0;

	System.out.println("Waiting for an email with the subject line: " + Main.testEmailSubject);

	while (!subjects.contains(Main.testEmailSubject) && count++ < 60) {
	    subjects = CheckingMails.getEmailSubjects(host, mailStoreType, username, password);
	    Thread.sleep(2000);
	    System.out.println("Still waiting...");
	}

	if (subjects.contains(Main.testEmailSubject))
	    System.out.println("got it!");


	Assert.assertEquals(true, subjects.contains(Main.testEmailSubject));
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

	//copy emailable result to C:/

	//http://45.58.62.18/iContact/test-output/emailable-report.html");
	try {

	    File afile = new File("C:\\dist\\test-output\\emailable-report.html");

	    if (afile.renameTo(new File("C:\\index.html"))) {
		System.out.println("File is moved successful!");
	    } else {
		System.out.println("File is failed to move!");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	try {

	    File afile = new File("C:\\Users\\Administrator\\Desktop\\test-output\\emailable-report.html");

	    if (afile.renameTo(new File("C:\\web\\dist\\test-output\\emailable-report.html"))) {
		System.out.println("File is moved successful!");
	    } else {
		System.out.println("File is failed to move!");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	SendMail.emailTheReportURL();
	GoogleMail.Send("albertkrivohlavek", "awefawef", "albertkrivohlavek@gmail.com", "Tests are done!", "UAT Report: http://45.58.62.18/test-output/emailable-report.html");
    }


}
