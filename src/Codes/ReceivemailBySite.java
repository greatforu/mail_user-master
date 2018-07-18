package Codes;
/**
 * Created by aksha on 01-04-2017.
 */
import java.io.*;
import java.sql.PreparedStatement;
import java.util.*;
import javax.mail.*;
import javax.mail.Flags.Flag;
import com.sun.mail.imap.IMAPFolder;


public class ReceivemailBySite {

    static private IMAPFolder folder = null;
    static private Store store = null;
    static private String subject = null;
    private static Flag flag = null;
    static private Properties props;
    private static Session session;
    private static Message[] messages;
    public static String FROM[]=new String[5];
    public static String DATE[]=new String[5];
    public static String MESSAGE[]=new String[5];
    public static String SUBJECT[]=new String[5];
    public void getMail(String email,String emailpass,String imap) throws MessagingException, IOException {

            props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");

            session = Session.getDefaultInstance(props, null);

            store = session.getStore("imaps");
            store.connect(imap,email, emailpass);
            folder = (IMAPFolder) store.getFolder("inbox");


            if(!folder.isOpen())
                folder.open(Folder.READ_WRITE);
            messages = folder.getMessages();
            System.out.println("No of Messages : " + folder.getMessageCount());
            System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
            System.out.println(messages.length);
            for (int i=messages.length-1; i > (messages.length)-6;i--)
            {
                System.out.println("MESSAGE " + (i + 1) + ":");
                Message msg =  messages[i];
                //System.out.println(msg.getMessageNumber());
                //Object String;
                //System.out.println(folder.getUID(msg)
                int j=0;

                SUBJECT[j]=subject = msg.getSubject();
                FROM[j]=msg.getFrom()[0].toString();
                DATE[j]=msg.getReceivedDate().toString();
                MESSAGE[j]=msg.getContent().toString();
                j++;
               /* System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: "+msg.getAllRecipients()[0]);
                System.out.println("Date: "+msg.getReceivedDate());
                System.out.println("Size: "+msg.getSize());
                System.out.println(msg.getFlags());
                System.out.println("Body: \n"+ msg.getContent());
                System.out.println(msg.getContentType());*/
            }
    }
    public String subject1(int i)throws Exception{
        return (messages[messages.length-i].getSubject());
    }
    /*public String subject2()throws Exception{
        return (messages[messages.length-2].getSubject());
    }
    public String subject3()throws Exception{
        return (messages[messages.length-3].getSubject());
    }
    public String subject4()throws Exception{
        return (messages[messages.length-4].getSubject());
    }
    public String subject5()throws Exception{
        return (messages[messages.length-5].getSubject());
    }*/
    public String getFrom(int i,String email,String emailpass,String imap)throws Exception{
        props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        session = Session.getDefaultInstance(props, null);

        store = session.getStore("imaps");
        store.connect(imap,email, emailpass);
        folder = (IMAPFolder) store.getFolder("inbox");


        if(!folder.isOpen())
            folder.open(Folder.READ_WRITE);
        messages = folder.getMessages();
        Address from=messages[messages.length-i].getFrom()[0];
        return from.toString();
    }
    public String getSubject(int i,String email,String emailpass,String imap)throws Exception{
        props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        session = Session.getDefaultInstance(props, null);

        store = session.getStore("imaps");
        store.connect(imap,email, emailpass);
        folder = (IMAPFolder) store.getFolder("inbox");


        if(!folder.isOpen())
            folder.open(Folder.READ_WRITE);
        messages = folder.getMessages();
        return messages[messages.length-i].getSubject();
    }
    public String getMessage(int i,String email,String emailpass,String imap)throws Exception{
        props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        session = Session.getDefaultInstance(props, null);

        store = session.getStore("imaps");
        store.connect(imap,email, emailpass);
        folder = (IMAPFolder) store.getFolder("inbox");


        if(!folder.isOpen())
            folder.open(Folder.READ_WRITE);
        messages = folder.getMessages();
        return messages[messages.length-i].getContent().toString();
    }
    public String getDate(int i,String email,String emailpass,String imap)throws Exception{
        props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        session = Session.getDefaultInstance(props, null);

        store = session.getStore("imaps");
        store.connect(imap,email, emailpass);
        folder = (IMAPFolder) store.getFolder("inbox");


        if(!folder.isOpen())
            folder.open(Folder.READ_WRITE);
        messages = folder.getMessages();
        //return messages[messages.length-i].getContent().toString();
        return messages[messages.length-1].getReceivedDate().toString();
    }
}
