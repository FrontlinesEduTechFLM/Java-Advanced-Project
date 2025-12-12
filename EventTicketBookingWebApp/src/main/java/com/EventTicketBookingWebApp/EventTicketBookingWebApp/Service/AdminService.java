package com.EventTicketBookingWebApp.EventTicketBookingWebApp.Service;

import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Model.Admin;
import com.EventTicketBookingWebApp.EventTicketBookingWebApp.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepo adminRepo;


    public String adminLogin(String name, String password) {
        if(name.equals("admin")&&password.equals("admin")){
            Admin admin = adminRepo.findByAdminNameAndAdminPassword(name,password);
            admin.setStatus("Logged In");
            adminRepo.save(admin);
            return "Admin Logged In Successfully";
        }
        return "Invalid Credential";
    }

    public String adminLogout(String adminName) {
        Admin admin = adminRepo.findByAdminName(adminName);

        if(admin.getStatus().equals("Logged In")){
            admin.setStatus("Logged Out");
            adminRepo.save(admin);
            return "Logout Successfully";
        }else{
            return "Admin is not Logged In";
        }
    }
}
