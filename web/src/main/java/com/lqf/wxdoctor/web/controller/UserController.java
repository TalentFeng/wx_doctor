package com.lqf.wxdoctor.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqf.wxdoctor.domain.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/save")
    public boolean save(@RequestBody UserInfo userInfo, HttpSession session) throws IOException {
        Object uid = session.getAttribute("uid");

        return true;
    }
}
