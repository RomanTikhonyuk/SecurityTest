package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/admin/users")
public class AdminController {

    private final UserService service;

    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping()
    public String showAllUsers(Model model) {
        List<User> users = service.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/new")
    public String showFormAddUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/admin/users";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/update")
    public String updateUserForm(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", service.findOne(id));
        return "edit_user";
    }
    @Secured("ROLE_ADMIN")
    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        service.update(user.getId(), user);
        return "redirect:/admin/users";
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping()
    public String deleteUser(@ModelAttribute("user") User user) {
        service.delete(user.getId());
        return "redirect:/admin/users";
    }

}
