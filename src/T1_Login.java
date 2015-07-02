import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T1_Login {
    public T1_Login() {
    }


    @BeforeClass
    public static void setUpClass() throws Exception {
	System.out.println("T1_Login");
    }

    @Test(priority = 0)
    public void go_to_login() throws Exception {

	Main.driver.get("http://www.icontact.com/");
	SeleniumHelper.waitForElementByXpath(Main.driver, "//*[@id=\"menu-item-64\"]/a");	//wait for login button

	Main.driver.findElement(By.id("menu-item-64")).click();

	//	Log In - iContact

	SeleniumHelper.waitForElementByXpath(Main.driver, "//*[@id=\"formLogin\"]//a[@class=\"forgot-password\"]");

	Assert.assertEquals(Main.driver.getTitle(), "Log In - iContact");
    }

    @Test(priority = 1)
    public void do_login() throws Exception {

	Main.driver.findElement(By.id("login")).sendKeys("albertkrivohlavek@gmail.com");
	Main.driver.findElement(By.id("password")).sendKeys("ASDqwe123");
	Main.driver.findElement(By.xpath("//*[@id=\"formLogin\"]/div[3]/div[2]/div[1]/input")).click();

	SeleniumHelper.waitForElementBy(Main.driver, By.linkText("Download iContact's Getting Started Guide"));	//wait for contact lists summary thingy

	System.out.println(Main.driver.getTitle());

	System.out.println(Main.driver.getTitle() + " == iContact  : Email Marketing Simplified ??");
	Assert.assertEquals(Main.driver.getTitle().replaceAll(" ", ""), "iContact  : Email Marketing Simplified".replaceAll(" ", ""));
    }

}
