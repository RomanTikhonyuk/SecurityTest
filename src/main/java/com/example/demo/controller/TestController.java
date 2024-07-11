package com.example.demo.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to unprotected page";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/test")
    public String prot(){
        return "welcome to protected page";
    }
    @GetMapping("/prot2")
    public String prot2(){
        return "welcome to protected page2";
    }
}
