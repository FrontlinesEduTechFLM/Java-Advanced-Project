package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Controller;

import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.DTO.ResetDTO;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.User;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("user")
@Tag(name="User Controller", description = "Users can register, login, logout and resetting the password")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("register")
    @Operation(
            summary = "User Registration",
            description = "Users can register themselves by giving their details and setting password."
    )
    public String registerUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.registerUser(user);
    }

    @PostMapping("login")
    @Operation(
            summary = "User Login",
            description = "Users can login themselves by giving their username and password."
    )
    public String signIn(@RequestParam String userEmail, @RequestParam String userPassword) throws NoSuchAlgorithmException {
        return userService.signIn(userEmail, userPassword);
    }

    @GetMapping("signout")
    @Operation(
            summary = "User Logout",
            description = "Users can logout themselves by giving their user email."
    )
    public String signOut(@RequestParam String userEmail){
        return userService.signoutUser(userEmail);
    }

    @PostMapping("resetPassword")
    @Operation(
            summary = "Reset Password",
            description = "Users can reset their passwords by giving their user Email ID."
    )
    private String resetPassword(@RequestParam String userEmail){
        return userService.resetPassword(userEmail);
    }



    @PostMapping("verifyOTP")
    @Operation(
            summary = "Verify OTP for resetting password",
            description = "Users have to verify OTP for resetting their passwords."
    )
    private String verifyOTP(@RequestBody ResetDTO user) throws NoSuchAlgorithmException{
        return userService.verifyOTP(user);
    }
}
