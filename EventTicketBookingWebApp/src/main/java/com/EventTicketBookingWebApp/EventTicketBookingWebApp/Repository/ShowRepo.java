package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Repository;

import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepo extends JpaRepository<Shows, Long> {
    Shows findByShowName(String showName);
    boolean existsByShowName(String showName);
}
