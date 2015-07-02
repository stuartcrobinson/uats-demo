import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

public class Main {


    public static WebDriver driver;
    public static String timestamp;
    public static String testListName;
    public static String testListDescription;

    public static String testEmailSubject;
    public static String testEmailContent;
    public static String testEmailMessageName;
    public static String listGroupIDNumber;
    public static final String customerEmailAddress = "zsdvzsdv1@aol.com";
    public static final String customerEmailPassword = "awefawef";

    /** set in T4 */
    public static String testListIDNumber;

    /** OMG THIS WORKS!!!! */
    public static void main(String[] args) throws InterruptedException {


	System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe");
	driver = new ChromeDriver();


	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
	timestamp = sdf.format(new Date());

	testListName = "testListName_" + timestamp;
	testListDescription = "testListDescription_" + timestamp;

	testEmailSubject = "mySubject_" + timestamp;
	testEmailContent = "myContent_" + timestamp;
	testEmailMessageName = "myMessageName_" + timestamp;


	TestNG testng = new TestNG();

	String xmlFileName = "testng.xml";
	List<XmlSuite> suite;
	try {
	    suite = (List<XmlSuite>)(new Parser(xmlFileName).parse());
	    testng.setXmlSuites(suite);
	    testng.run();
	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	} catch (SAXException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	Thread.sleep(20000);
	//copy emailable-report to public html folder
	//email the link to myself
    }
}
