/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helperPack;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author MesutOezil
 */
public class sendEmail {
    public static void autoSendEmail(String email, String userName, String pass) {
        try {
            Properties props = new Properties();
            //cau hinh email
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");


            Session s = Session.getInstance(props, null);
            MimeMessage message = new MimeMessage(s);
            InternetAddress from = new InternetAddress("tranhuucuong1991@gmail.com");
            message.setFrom(from);
            message.setContent("Your message", "text/plain");
            InternetAddress to = new InternetAddress(email);
            message.addRecipient(Message.RecipientType.TO, to);
            message.setSubject("You just have a account in our website");
           //gui noi dung
            message.setText("Username:" + userName+"<br/>Password: "+ pass);
            Transport tr = s.getTransport("smtp");
            tr.connect("smtp.gmail.com", "tranhuucuong1991@gmail.com", "tranhuucuong");
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
        } catch (AddressException ex) {
            Logger.getLogger(sendEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(sendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendMail_Contact(String name,String email,String content) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            Session s = Session.getInstance(props, null);
            MimeMessage message = new MimeMessage(s);
            InternetAddress from = new InternetAddress("accpvip@gmail.com");
            message.setFrom(from);
            message.setContent("Your message", "text/plain");
            InternetAddress to = new InternetAddress("ntb.contact@gmail.com");
            message.addRecipient(Message.RecipientType.TO, to);
            message.setSubject("Information Contact NTB website.");
            message.setText("Email : "+email+".\nName : "+name+".\nContent : "+content);
            Transport tr = s.getTransport("smtp");
            tr.connect("smtp.gmail.com", "accpvip@gmail.com", "aloaloalo");
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
        } catch (AddressException ex) {
            Logger.getLogger(sendEmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(sendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
