package com.concurrency.currency01.controller;

import com.concurrency.currency01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/insert")
    public String ok() {
        return userService.hanlder();
    }
}
