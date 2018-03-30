package com.lqf.wxdoctor.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Configuration
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "";
    }

}
