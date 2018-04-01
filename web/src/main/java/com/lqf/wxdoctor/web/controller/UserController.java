package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.dao.UserInfoDao;
import com.lqf.wxdoctor.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserInfoDao userInfoDao;

    @RequestMapping("/save")
    public boolean save(@RequestBody UserInfo userInfo, HttpSession session) throws IOException {
        String uid = session.getAttribute("uid").toString();

        return true;
    }
}
