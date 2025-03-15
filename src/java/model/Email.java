/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author PC
 */
public class Email {

    private final String eFrom = "ndthan04@gmail.com";
    private final String ePass = "heph vltu jyhp pwxq";

    public void sendEmail(String subject, String messgage, String to) {
        // Properties
        Properties props = new Properties();

        // Su dung server nao de gui mail- smtp host
        props.put("mail.smtp.host", "smtp.gmail.com");

        // TLS 587 SSL 465
        props.put("mail.smtp.port", "587");

        // dang nhap
        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.starttls.enable", "true");

        // Thêm các thuộc tính timeout
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");

        // dang nhap tai khoan
        Authenticator au = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(eFrom, ePass);
            }
        };

        // phien lam viec
        Session session = Session.getInstance(props, au);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML, charset=UTF-8");
            msg.setFrom(new InternetAddress(eFrom));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // tieu de
            msg.setSubject(subject, "UTF-8");
            // Noi dung
            msg.setContent(messgage, "text/html; charset=UTF-8");
            // Gui email
            Transport.send(msg);
            System.out.println("Email sent successfully to: " + to);
        } catch (Exception e) {
            System.out.println("Send email failed to: " + to);
            e.printStackTrace();
        }
    }

    public String subjectOrder(String fullName) {
        return "Hi, " + fullName + ", thanks for your order from Furni Store!";
    }

    public String messageOrder(LocalDateTime date, double totalMoney, String address) {
        return "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "  <style>\n"
                + "    body {\n"
                + "      font-family: Arial, sans-serif;\n"
                + "      background-color: #f4f4f4;\n"
                + "      margin: 0;\n"
                + "      padding: 0;\n"
                + "    }\n"
                + "\n"
                + "    .email-container {\n"
                + "      max-width: 600px;\n"
                + "      margin: 20px auto;\n"
                + "      background-color: #fff;\n"
                + "      border-radius: 8px;\n"
                + "      overflow: hidden;\n"
                + "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                + "    }\n"
                + "\n"
                + "    .header {\n"
                + "      background-color: #3498db;\n"
                + "      color: #fff;\n"
                + "      padding: 20px;\n"
                + "      text-align: center;\n"
                + "    }\n"
                + "\n"
                + "    .content {\n"
                + "      padding: 20px;\n"
                + "    }\n"
                + "\n"
                + "    .discount-code {\n"
                + "      background-color: #e74c3c;\n"
                + "      color: #fff;\n"
                + "      padding: 10px;\n"
                + "      text-align: center;\n"
                + "      margin-bottom: 20px;\n"
                + "      font-size: 20px;\n"
                + "      border-radius: 4px;\n"
                + "    }\n"
                + "\n"
                + "    .footer {\n"
                + "      background-color: #3498db;\n"
                + "      color: #fff;\n"
                + "      padding: 10px;\n"
                + "      text-align: center;\n"
                + "    }\n"
                + "  </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "  <div class=\"email-container\">\n"
                + "    <div class=\"header\">\n"
                + "     <h1>Furni store</h1>\n"
                + "      <h2>Your order is on its way!</h2>\n"
                + "    </div>\n"
                + "    <div class=\"content\">\n"
                + "      <h3>Order summary:</h3>\n"
                + "      <p>Order Date: " + date + "</p>\n"
                + "      <p>Order Total: " + totalMoney + "$</p>\n"
                + "      <p>Shipping address: " + address + "</p>\n"
                + "      <p>If you have any questions, contact us here or call us on 012345678.</p>\n"
                + "      <p>We are here to help!</p>\n"
                + "    </div>\n"
                + "    <div class=\"footer\">\n"
                + "      <p>Thank you for placing your order!</p>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</body>\n"
                + "</html>";
    }

//    public static void main(String[] args) {
//        String to = "ndthang1704@gmail.com";
//        String subject = "Đăng ký nhận thư thành công";
//        String message = "Cảm ơn bạn đã đăng ký nhận thư";
//        Email email = new Email();
//        email.sendEmail(subject, message, to);
//    }
}
