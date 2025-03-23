package utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Properties;

public class EmailUtils {

    public static void sendTestReport(String reportPath) {
        final String senderEmail = "seleniumjavatestautomation@gmail.com";
        final String appPassword = "ugzhwqtaxzyxdciu";
        final String recipientEmail = "seleniumjavatestautomation@gmail.com";

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });

        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Test email from QA Automation");

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hello \n\n This is test email from Java \n\n Regards,\nQA Team");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            //String filePath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
            System.out.println("Attachment path is - " + reportPath);
            attachmentPart.attachFile(new File(reportPath));

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent successfully");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}



