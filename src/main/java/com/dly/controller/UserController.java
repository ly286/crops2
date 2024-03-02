package com.dly.controller;

import com.dly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/hello")
    public String hello(){
        return "${ly.datasource.username}";
    }

    @GetMapping("/index")
    public Object index(){
        return service.getById(1L);
    }
}
