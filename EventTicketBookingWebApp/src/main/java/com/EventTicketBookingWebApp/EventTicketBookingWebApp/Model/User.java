package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;

    private String userPassword;

    private String userDob;

    @Email
    private String userEmail;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String userMobileNo;

    private String status;

    private String otp;

}
