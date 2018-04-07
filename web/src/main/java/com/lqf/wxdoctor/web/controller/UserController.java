package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.dao.UserDao;
import com.lqf.wxdoctor.dao.UserInfoDao;
import com.lqf.wxdoctor.domain.User;
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

    @Autowired
    UserDao userDao;

    @RequestMapping("/save")
    public boolean save(@RequestBody UserInfo userInfo, HttpSession session) throws IOException {
        String uid = session.getAttribute("uid").toString();
        String openId = session.getAttribute("openid").toString();
        User user = userDao.get(null, userInfo.getBlh(), userInfo.getName());
        if (user != null) {
            userDao.update(Long.parseLong(uid), user.getId(), openId);
            userInfo.setUid(Long.parseLong(uid));
            userInfoDao.save(userInfo);
            return true;
        }
        return false;
    }
}
