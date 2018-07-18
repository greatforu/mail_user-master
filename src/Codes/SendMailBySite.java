package Codes;
/**
 * Created by aksha on 30-03-2017.
 */
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class SendMailBySite {
    //static public db ob=new db();
    public void sendMail(String To,String subject,String mess,String email,String pass,String Smtp,String attach) throws Exception {

        String host="gmail.com";
        final String user=email;
        final String password=pass;
        String smtp=Smtp;
        String to=To;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                }
        );

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(subject);
            message.setText(mess);
            MimeBodyPart msgBodyPart=new MimeBodyPart();
            msgBodyPart.setContent(mess,"text/html");

            Multipart multipart=new MimeMultipart();
            multipart.addBodyPart(msgBodyPart);

            if(attach!=null){
                MimeBodyPart attachPart=new MimeBodyPart();
                try{
                    attachPart.attachFile(attach);
                }catch (IOException e){e.printStackTrace();}
                multipart.addBodyPart(attachPart);
            }
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Message sent successfully...");

        } catch (MessagingException e) {e.printStackTrace();}
    }
}