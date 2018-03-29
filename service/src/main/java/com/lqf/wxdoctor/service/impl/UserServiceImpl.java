package com.lqf.wxdoctor.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqf.wxdoctor.common.OkHttpUtil;
import com.lqf.wxdoctor.dao.UserDao;
import com.lqf.wxdoctor.domain.User;
import com.lqf.wxdoctor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Value("${weixin.base.appid}")
    String appId;

    @Value("${weixin.base.app-secret}")
    String appSecret;

    @Value("${weixin.server.login}")
    String wxLogin;

    public User login(final String code, HttpSession session) throws IOException {
        String json = OkHttpUtil.post(wxLogin, new HashMap<String, Object>() {
            {
                put("js_code", code);
                put("appid", appId);
                put("secret", appSecret);
                put("grant_type", "authorization_code");
            }
        });
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(json, Map.class);
        String openId = map.get("openid").toString();
        session.setAttribute("openid", openId);
        session.setAttribute("session_key", map.get("session_key"));
        User user = userDao.get(code);
        if (user == null) {
            user = new User();
            user.setBlh(0l);
            user.setName("游客_" + new Date().getTime());
            user.setOpenid(code);
            userDao.save(user);
        }
        return user;
    }
}
