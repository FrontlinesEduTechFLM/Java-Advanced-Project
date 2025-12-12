package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service;

import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.DTO.BookingDTO;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.Shows;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.Admin;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.User;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Repository.AdminRepo;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Repository.ShowRepo;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Repository.UserRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepo showRepo;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    EmailService emailService;

    public String addShow(Shows show) {
        Admin admin = adminRepo.findByAdminName("admin");
        if(admin.getStatus().equals("Logged In")){
            showRepo.save(show);
            return "Show Added Successfully";
        }else{
            return "Admin Login Required!";
        }
    }

    public String removeShow(String showName) {
        Admin admin = adminRepo.findByAdminName("admin");
        if(admin.getStatus().equals("Logged In")){
            if(!showRepo.existsByShowName(showName)){
                return "Show Not Found";
            }
            Shows show = showRepo.findByShowName(showName);
            showRepo.delete(show);
            return "Show deleted Successfully";
        }else{
            return "Admin Login Required!";
        }
    }

    public List<Shows> getAllShows() {
        return showRepo.findAll();
    }

    public String ticketBooking(@NotNull BookingDTO bookingDTO) {
        User user = userRepo.findByUserEmail(bookingDTO.getUserEmail());
        if(user.getStatus().equals("Logged In")){
            if(showRepo.existsByShowName(bookingDTO.getShowName())){
                Shows shows = showRepo.findByShowName(bookingDTO.getShowName());

                if(bookingDTO.getClassType().equals("platinum")){
                    if(shows.getPlatinumSeats()>=bookingDTO.getTickets()){
                        shows.setPlatinumSeats(shows.getPlatinumSeats()-bookingDTO.getTickets());
                        showRepo.save(shows);
                        Integer totalPrice = bookingDTO.getTickets()* shows.getPlatinumPrice();
                        emailService.sendBookingMail(bookingDTO.getUserEmail(),bookingDTO.getShowName(),shows.getVenue(),bookingDTO.getClassType(),bookingDTO.getTickets(), shows.getPlatinumPrice(), totalPrice);
                        return "Ticket is booked successfully";
                    }else{
                        return "Not enough seats are available";
                    }
                }else if(bookingDTO.getClassType().equals("gold")){
                    if(shows.getGoldSeats()>=bookingDTO.getTickets()){
                        shows.setGoldSeats(shows.getGoldSeats()-bookingDTO.getTickets());
                        showRepo.save(shows);
                        Integer totalPrice = bookingDTO.getTickets()* shows.getGoldPrice();
                        emailService.sendBookingMail(bookingDTO.getUserEmail(),bookingDTO.getShowName(),shows.getVenue(),bookingDTO.getClassType(),bookingDTO.getTickets(), shows.getGoldPrice(), totalPrice);
                        return "Ticket is booked successfully";
                    }else{
                        return "Not enough seats are available";
                    }
                }
                else if(bookingDTO.getClassType().equals("silver")){
                    if(shows.getSilverSeats()>=bookingDTO.getTickets()){
                        shows.setSilverSeats(shows.getSilverSeats()-bookingDTO.getTickets());
                        showRepo.save(shows);
                        Integer totalPrice = bookingDTO.getTickets()* shows.getSilverPrice();
                        emailService.sendBookingMail(bookingDTO.getUserEmail(),bookingDTO.getShowName(),shows.getVenue(),bookingDTO.getClassType(),bookingDTO.getTickets(), shows.getSilverPrice(), totalPrice);
                        return "Ticket is booked successfully";
                    }else{
                        return "Not enough seats are available";
                    }
                }
                else{
                    return bookingDTO.getClassType()+" type does not exists!";
                }
            }else{
                return "Show does not exists!";
            }

        }else {
            return "User Login Required!";
        }
    }
}
