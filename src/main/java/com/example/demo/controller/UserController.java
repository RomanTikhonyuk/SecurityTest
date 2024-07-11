package com.example.demo.controller;


import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/myuser")
    public String updateUserForm(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", service.findOne(id));
        return "user_info";
    }
}
