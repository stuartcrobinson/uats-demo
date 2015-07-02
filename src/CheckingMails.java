/**
 JavaMailtest
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;


public class CheckingMails {


    /** returns all subjects */
    public static String getEmailSubjects(String host, String storeType, String user, String password) {
	String subjects = "";
	try {

	    //create properties field
	    Properties properties = new Properties();

	    properties.put("mail.pop3.host", host);
	    properties.put("mail.pop3.port", "995");
	    properties.put("mail.pop3.starttls.enable", "true");
	    Session emailSession = Session.getDefaultInstance(properties);

	    //create the POP3 store object and connect with the pop server
	    Store store = emailSession.getStore("pop3s");

	    store.connect(host, user, password);

	    //create the folder object and open it
	    Folder emailFolder = store.getFolder("INBOX");
	    emailFolder.open(Folder.READ_ONLY);


	    // retrieve the messages from the folder in an array and print it
	    Message[] messages = emailFolder.getMessages();
	    System.out.println("messages.length---" + messages.length);


	    for (int i = 0, n = messages.length; i < n; i++) {
		Message message = messages[i];
//		System.out.println("---------------------------------");
//		System.out.println("Email Number " + (i + 1));
//		System.out.println("Subject: " + message.getSubject());
//		System.out.println("From: " + message.getFrom()[0]);
//		System.out.println("Text: " + message.getContent().toString());
		subjects += message.getSubject() + " ";
	    }

	    //close the store and folder objects
	    emailFolder.close(false);
	    store.close();

	    return subjects;
	} catch (NoSuchProviderException e) {
	    e.printStackTrace();
	} catch (MessagingException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return subjects;
    }

    /** returns all subjects */
    public static List getEmailMessageContents(String host, String storeType, String user, String password) {
	List<String> messageBodies = new ArrayList();
	try {

	    //create properties field
	    Properties properties = new Properties();

	    properties.put("mail.pop3.host", host);
	    properties.put("mail.pop3.port", "995");
	    properties.put("mail.pop3.starttls.enable", "true");
	    Session emailSession = Session.getDefaultInstance(properties);

	    //create the POP3 store object and connect with the pop server
	    Store store = emailSession.getStore("pop3s");

	    store.connect(host, user, password);

	    //create the folder object and open it
	    Folder emailFolder = store.getFolder("INBOX");
	    emailFolder.open(Folder.READ_ONLY);


	    // retrieve the messages from the folder in an array and print it
	    Message[] messages = emailFolder.getMessages();
	    System.out.println("messages.length---" + messages.length);


	    for (int i = 0, n = messages.length; i < n; i++) {
		Message message = messages[i];
//		System.out.println("---------------------------------");
//		System.out.println("Email Number " + (i + 1));
//		System.out.println("Subject: " + message.getSubject());
//		System.out.println("From: " + message.getFrom()[0]);
//		System.out.println("Text: " + message.getContent().toString());
		messageBodies.add(message.getContent().toString());
	    }

	    //close the store and folder objects
	    emailFolder.close(false);
	    store.close();

	    return messageBodies;
	} catch (NoSuchProviderException e) {
	    e.printStackTrace();
	} catch (MessagingException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return messageBodies;
    }

    public static void main1(String[] args) {

	String host = "pop.gmail.com";// change accordingly
//	String host = "pop.aol.com";
	String mailStoreType = "pop3";
//	String username = "zsdvzsdv9@aol.com";// change accordingly
	String username = "albertkrivohlavek@gmail.com";// change accordingly
//	String username = "strobinso@gmail.com";// change accordingly
	String password = "awefawef";// change accordingly

	getEmailSubjects(host, mailStoreType, username, password);

//	checkMailinator(username);

    }

}
