package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Controller;


import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.DTO.BookingDTO;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.Shows;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shows")
@Tag(name="Show Controller", description = "Admin can add and remove shows, and Users can book tickets fot shows and events.")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("addShow")
    @Operation(
            summary = "Add Shows/Events",
            description = "Admin can add shows and events by Show details, only if admin is logged in."
    )
    private String addShow(@RequestBody Shows show){
        return showService.addShow(show);
    }

    @GetMapping("allShows")
    @Operation(
            summary = "Shows/Events",
            description = "There is all shows and events details."
    )
    private List<Shows> getAllShows(){
        return showService.getAllShows();
    }

    @PostMapping("removeShow")
    @Operation(
            summary = "Remove Shows/Events",
            description = "Admin can remove shows and events by Show details, only if admin is logged in."
    )
    private String removeShow(@RequestParam String showName){
        return showService.removeShow(showName);
    }


    @PostMapping("book")
    @Operation(
            summary = "Book Tickets for Shows/Events",
            description = "Users can book tickets for shows and events, only if users are logged in."
    )
    private String ticketBooking(@RequestBody BookingDTO bookingDTO){
        return showService.ticketBooking(bookingDTO);
    }
}
