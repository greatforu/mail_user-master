/**
 * Created by aksha on 26-03-2017.
 */
package Codes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class db{
    static protected Connection conn;
    static protected Statement stat;
    static protected ResultSet rs;
    private String smtp1,imap1;
    static SendMailBySite sendMailBySite=new SendMailBySite();
    static ReceivemailBySite receivemailBySite=new ReceivemailBySite();
    static public String SMTP,IMAP,PASS,ID;
    public void sign_Up(String username,String pass,String f_Name,String l_Name,String email,String email_pass,String imap,String smtp) {
        try{
            int i=email.indexOf('@');
            if(smtp.equals("SMTP")) {
                System.out.println("what");
                smtp1=new String();
                smtp1 = "smtp." + email.substring(++i);
            }
            else{
                smtp1=smtp;
            }
            if(imap.equals("IMAP")) {
                imap1=new String();
                imap1 = "imap." + email.substring(i);
            }
            else{
                imap1=imap;
            }
                Connection connection=DriverManager.getConnection("jdbc:mysql://Localhost:3306/users","root","");
                String sql = "INSERT INTO users " + " (username, password, First_Name,Last_Name,Email,Email_pass,smtp_server,imap_server)" + " values (?, ?, ?, ?,?,?,?,?)";
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setString(1,username);
                statement.setString(2,pass);
                statement.setString(3,f_Name);
                statement.setString(4,l_Name);
                statement.setString(5,email);
                statement.setString(6,email_pass);
                statement.setString(7,smtp1);
                statement.setString(8,imap1);
                statement.executeUpdate();
        }
        catch (Exception e){e.printStackTrace();}
    }
    public boolean signIn(String username,String pass){
        try{
            Connection connection=DriverManager.getConnection("jdbc:mysql://Localhost:3306/users","root","");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()){
                if(resultSet.getString("username").equals(username))
                    break;
            }
            if(resultSet.getString("password").equals(pass)) {
                SMTP=resultSet.getString("smtp_server");
                IMAP=resultSet.getString("imap_server");
                PASS=resultSet.getString("Email_pass");
                ID=resultSet.getString("Email");
                return true;
            }
            else
                return false;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean check(String username)throws Exception{
        Connection connection=DriverManager.getConnection("jdbc:mysql://Localhost:3306/users","root","");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM users");
        while(resultSet.next()) {
            if (resultSet.getString("username").equals(username))
                return true;
        }
        return false;
    }
}