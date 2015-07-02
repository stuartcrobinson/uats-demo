import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T5_Activate_Customer_Permission {
    public T5_Activate_Customer_Permission() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
	System.out.println("T5_Confirm_the_confirmationEmail");
    }
    String newListId;
    String confirmationEmailLink;

    @Test(priority = 1)
    public void get_ConfirmationLinkFromCustomerEmailAccount() throws InterruptedException {

	String host = "pop.aol.com";
	String mailStoreType = "pop3";
	String username = Main.customerEmailAddress;
	String password = Main.customerEmailPassword;


//                                https://app.icontact.com/icp/confirm.php?r=28998147&s=FQYM&gid=28329&c=1577445&l=14607&m=68699&cm=manual
	String a_confirmation_link = "";

	boolean correctActivationEmailNotFound = true;

	int trycount = 0;
	do {

	    List<String> messageBodies = CheckingMails.getEmailMessageContents(host, mailStoreType, username, password);
	    int emailCount = 1;
	    for (String st : messageBodies) {
		System.out.println("checking email " + emailCount++);

		String body = st;

		body = body.replaceAll("(?is).*https://app\\.icontact\\.com/icp/confirm\\.php", "https://app.icontact.com/icp/confirm.php");
		body = body.replaceAll("(?is)\\&cm=manual.*", "&cm=manual");
		body = body.replaceAll("\\s", "");


//		System.out.println("confirmation link? --> " + body + "|||END");
		if (body.length() > 80)
		    a_confirmation_link = body;

		if (a_confirmation_link.contains(Main.testListIDNumber)) {
		    correctActivationEmailNotFound = false;
		    break;
		}
	    }
	    System.out.println("mostRecentConfirmationLink " + a_confirmation_link);
	    Thread.sleep(2000);
	} while (correctActivationEmailNotFound && trycount++ < 60);

	Assert.assertEquals(correctActivationEmailNotFound, false);
	confirmationEmailLink = a_confirmation_link;
	//iContact  : Add One Subscriber
    }


    @Test(priority = 2)
    public void visit_url_to_activate_subscription() throws InterruptedException {

	WebDriver headless = new HtmlUnitDriver();
	headless.get(confirmationEmailLink);

	String source = headless.getPageSource();

	assertEquals(true, source.contains("Thank you for subscribing to"));
    }
}
