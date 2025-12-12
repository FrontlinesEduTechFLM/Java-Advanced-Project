package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Controller;

import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@Tag(name="Admin Controller", description = "Admin can login and logout.")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("login")
    @Operation(
            summary = "Admin Login",
            description = "Admin can login by providing the admin name and the password."
    )
    public String adminLogin(@RequestParam String adminName, @RequestParam String adminPassword){
        return adminService.adminLogin(adminName, adminPassword);
    }

    @PostMapping("logout")
    @Operation(
            summary = "Admin Logout",
            description = "Admin can logout by providing only the admin name."
    )
    public String adminLogout(@RequestParam String adminName){
        return adminService.adminLogout(adminName);
    }

}
