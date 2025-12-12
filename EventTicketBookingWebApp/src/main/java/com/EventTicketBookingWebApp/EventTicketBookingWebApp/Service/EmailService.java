package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("OTP Verification");

            StringBuilder Body = new StringBuilder();
            Body.append("Login otp: ");
            Body.append(otp);
            helper.setText(Body.toString(),true);
            javaMailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }



    public void sendBookingMail(String userEmail, String showName, String venue, String classType, Integer tickets,Integer ticketPrice, Integer totalPrice) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(userEmail);

            // Create the HTML content for the email
            String subject = "Ticket Booking Details";
            StringBuilder body = new StringBuilder();
            body.append("<html>");
            body.append("<body>");
            body.append("<div style='text-align: center;'>");
            body.append("<h1 style='color: #007BFF;'>Ticket Booking Details</h1>");
            body.append("<p>Your ticket booking details are as follows:</p>");
            body.append("<p>User Email: " + userEmail + "</p>");
            body.append("<p>Show Name: " + showName + "</p>");
            body.append("<p>Venue: " + venue + "</p>");
            body.append("<p>Class Type: " + classType + "</p>");
            body.append("<p>Number of Tickets: " + tickets + "</p>");
            body.append("<p>Price per Ticket: " + ticketPrice + "</p>");
            body.append("<p>Total amount: " + totalPrice + "</p>");
            body.append("</div>");
            body.append("</body>");
            body.append("</html>");


            // Set the email content as HTML
            helper.setText(body.toString(), true);

            // Send the email
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
}

