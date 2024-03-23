package com.example.myspringapp.controllers;

import com.example.myspringapp.dto.UserDto;
import com.example.myspringapp.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userprofile")
    public ResponseEntity<?> userprofile(HttpSession session){return userService.userprofile(session);}

    @GetMapping("/isauth")
    public Boolean isauth(HttpSession session){return userService.isauth(session);}

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        return userService.signup(userDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserDto userDto, HttpSession session){return userService.signin(userDto, session);}

    @PostMapping("/logout")
    public ResponseEntity<?> signout(HttpSession session){return userService.logout(session);}
}
