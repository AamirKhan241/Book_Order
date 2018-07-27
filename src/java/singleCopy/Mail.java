package singleCopy;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    public boolean sentMail(String email,String subject,String msg){
        
        final String username="bookordered@gmail.com";
        final String password="Aamir@22647";        
        
        Properties props=new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session=Session.getInstance(props,new javax.mail.Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(username,password);
           }
        });
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress("bookordered@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setContent("<h:body style=background-color:white;font-family:verdana;>"
                    + "Mr./Ms./Mrs. :"+msg+"</body>","text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("mail sent");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    
}
