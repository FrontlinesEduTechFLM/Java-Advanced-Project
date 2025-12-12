package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service;

import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.DTO.ResetDTO;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.User;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Repository.UserRepo;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service.Utility.OTPGenerator;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service.Utility.passwordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    EmailService emailService;
    public String registerUser(User user) throws NoSuchAlgorithmException {
            if(userRepo.existsByuserEmail(user.getUserEmail())){
                return "User Already Registered";
            }
            String hashPass = passwordEncrypter.hashPasswordWithStaticSecret(user.getUserPassword());
            user.setUserPassword(hashPass);
            userRepo.save(user);
            return "Register Successfully";
    }


    public String signIn(String email, String password) throws NoSuchAlgorithmException {
        if(!userRepo.existsByuserEmail(email)){
            return "Please Register";
        }
        String hashPass = passwordEncrypter.hashPasswordWithStaticSecret(password);
        User user =userRepo.findByUserEmail(email);
        if(hashPass.equals(user.getUserPassword())){
            user.setStatus("Logged In");
            userRepo.save(user);
            return "Login Successfully";
        }else{
            return "Invalid Credentials";
        }
    }

    public String signoutUser(String email) {
        User user =userRepo.findByUserEmail(email);

        if(user.getStatus().equals("Logged In")){
            user.setStatus(("Logged Out"));
            userRepo.save(user);
            return "Logout Successfully";
        }else {
            return "Already Logged Out";
        }
    }

    public String resetPassword(String email) {
        if(!userRepo.existsByuserEmail((email))){
            return "User Not Register";
        }
        User user = userRepo.findByUserEmail(email);
        String otp = OTPGenerator.generateOTP();

        user.setOtp(otp);
        userRepo.save(user);
        emailService.sendOtpEmail(email,otp);
        return "OTP Send Successfully";
    }

    public String verifyOTP(ResetDTO resetDTOUser) throws NoSuchAlgorithmException {
        User existingUser =userRepo.findByUserEmail(resetDTOUser.getEmail());
        if(existingUser != null && existingUser.getOtp().equals(resetDTOUser.getOtp())){
            String newHashPass = passwordEncrypter.hashPasswordWithStaticSecret(resetDTOUser.getNewPass());
            existingUser.setUserPassword(newHashPass);
            userRepo.save(existingUser);
            return "Password Changed";
        }else{
            return "Invalid OTP";
        }

    }

}
