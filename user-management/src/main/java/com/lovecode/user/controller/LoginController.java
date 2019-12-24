package com.lovecode.user.controller;

import com.lovecode.user.entity.User;
import com.lovecode.user.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    private final RegisterService registerService;

    public LoginController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User registerUser) {
        registerService.saveUserInfo(registerUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public String test() {
        System.out.println("===============================================");
        return "String";
    }
}
