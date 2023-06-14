package com.example.demo.controllers;

import com.example.demo.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    Service service ;
}
