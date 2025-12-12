package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Repository;

import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByuserEmail(String email);

    User findByUserEmail(String email);
}
