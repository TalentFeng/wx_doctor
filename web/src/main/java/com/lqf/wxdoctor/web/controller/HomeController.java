package com.lqf.wxdoctor.web.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Configuration
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

}
