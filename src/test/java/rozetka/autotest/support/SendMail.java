package rozetka.autotest.support;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    public static boolean send(String setFileName) {

        final String username = "l.kogema@gmail.com";
        final String password = "tabren31";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ruslanmail1996@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(readFromFile()));
            message.setSubject("Hello");
            message.setText("PFA");


            Multipart multipart = new MimeMultipart();

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(setFileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(setFileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static String readFromFile()  {
        String emailAdress;
        try {
            FileReader fr= new FileReader("EmailAddress.txt");
            Scanner scan = new Scanner(fr);

            emailAdress = scan.nextLine();

            fr.close();
            return emailAdress;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
