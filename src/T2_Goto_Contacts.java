import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T2_Goto_Contacts {

    public T2_Goto_Contacts() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
	System.out.println("T2_Goto_Contacts");
    }

    @Test
    public void go_to_contacts() throws InterruptedException {

	//	//*/a[@href="https://app.icontact.com/icp/core/mycontacts?token=7d079914df69e4381d771967855a65cd"]

	SeleniumHelper.waitForElementByXpath(Main.driver, "//*/a[contains(@href, 'mycontacts?token=')]");	    // Contacts link
	Main.driver.findElement(By.xpath("//*/a[contains(@href, 'mycontacts?token=')]")).click();


	SeleniumHelper.waitForElementBy(Main.driver, By.linkText("Create a List"));
	SeleniumHelper.waitForElementBy(Main.driver, By.linkText("Sign-up Forms"));

	System.out.println(Main.driver.getTitle().replaceAll(" ", "") + " == iContact  : My Contacts ?".replaceAll(" ", ""));

	Assert.assertEquals(Main.driver.getTitle().replaceAll(" ", ""), "iContact  : My Contacts".replaceAll(" ", ""));

    }

}
