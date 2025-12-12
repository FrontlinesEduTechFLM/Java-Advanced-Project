package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service.Utility;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.security.SecureRandom;
import java.util.Random;

public class OTPGenerator {
    private static final String OTP_CHARS ="01234567890";
    private static final int OTP_LENGTH = 6;
    public static String generateOTP() {

        Random random =new SecureRandom();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);

        for(int i=0; i<6; i++){
            int randomIndex = random.nextInt(OTP_CHARS.length());
            otp.append(OTP_CHARS.charAt(randomIndex));
        }
        return otp.toString();
    }
}

