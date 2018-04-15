package com.lqf.wxdoctor.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController extends BaseController {
    @GetMapping("/")
    public String index() {
        return "";
    }

}
