package com.example.demo.controller;


import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping()
public class UserController {

    private final UserService service;
    private final RoleRepository roleRepository;
    @Autowired
    public UserController(UserService service, RoleRepository roleRepository) {
        this.service = service;
        this.roleRepository = roleRepository;
    }
    //@Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/myuser")
    public String updateUserForm(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", service.findOne(id));
        return "user_info";
    }
    @GetMapping("/new")
    public String showFormAddUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }


    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/users";
    }
}
