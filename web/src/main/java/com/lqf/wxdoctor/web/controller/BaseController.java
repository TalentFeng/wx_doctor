package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    HttpSession session;

    public User getUser() {
        return (User) session.getAttribute("user");
    }

    Map result = new HashMap() {
        {
            put("code", 200);
            put("msg", "ok");
            put("data", "");
        }
    };
}
