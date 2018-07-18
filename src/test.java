/**
 * Created by aksha on 30-03-2017.
 */
import Codes.*;
import com.sun.mail.imap.IMAPFolder;

import javax.mail.*;
import java.util.Base64;
import java.util.Properties;
import Codes.SendMailBySite;

public class test {
    public static void main(String[] args) throws Exception{
        SendMailBySite sendMailBySite=new SendMailBySite();
        String s="<!DOCTYPE html><HTML>"+
                "<head></head>"+
                "<body>" +
                "<h1>hello</h1>" +
                "<table align=\"center\" width=\"300\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #ccc;\">" +
                "<tr>" +
                "<td>ok</td>" +
                "<td>lets see</td>" +
                "</tr>" +
                "</tr>" +
                "<td>issue</td>" +
                "<td>some</td>" +
                "</table>" +
                "</body>" +
                "<html>";
        sendMailBySite.sendMail("akshay.avinash@gmail.com","Test",s,"akshay_1998@live.com","Akshay@11167","smtp.live.com",null);

    }
}