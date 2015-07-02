import java.util.Properties;
import java.util.Date;

import javax.mail.*;

import javax.mail.internet.*;

import com.sun.mail.smtp.*;


public class SendMail {
    public static void emailTheReportURL() throws Exception {
	Properties props = System.getProperties();
	props.put("mail.smtps.host", "smtp.mailgun.org");
	props.put("mail.smtps.auth", "true");
	Session session = Session.getInstance(props, null);
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress("strobinso@hotmail.com", "iContact Automated Test"));
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("zsdvzsdv1@aol.com", false));
	msg.setSubject("one two three");
	msg.setText("big sale on shoes today");	// http://45.58.62.18/iContact/test-output/emailable-report.html
	msg.setSentDate(new Date());
	SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
	t.connect("smtp.mailgun.com", "postmaster@sandbox52da2bc07d0d4a5ab866e6ab545a36f3.mailgun.org", "37f5966a95fa4ba9291bf4a03239b8b1");
	t.sendMessage(msg, msg.getAllRecipients());
	System.out.println("Response: " + t.getLastServerResponse());
	t.close();
    }

    public static void main1(String[] args) throws Exception {


	SendMail.emailTheReportURL();
    }


}
