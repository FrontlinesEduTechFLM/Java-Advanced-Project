package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String showName;
    private String showDesc;
    private String venue;
    private String date;
    private String time;
    private Integer platinumPrice;
    private Integer platinumSeats;
    private Integer goldPrice;
    private Integer goldSeats;
    private Integer silverPrice;
    private Integer silverSeats;

}
