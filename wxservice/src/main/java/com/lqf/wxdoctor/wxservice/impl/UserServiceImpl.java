package com.lqf.wxdoctor.wxservice.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqf.wxdoctor.common.OkHttpUtil;
import com.lqf.wxdoctor.dao.UserDao;
import com.lqf.wxdoctor.domain.User;
import com.lqf.wxdoctor.wxservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserDao userDao;

    @Value("${weixin.base.appid}")
    String appId;

    @Value("${weixin.base.app-secret}")
    String appSecret;

    @Value("${weixin.server.login}")
    String wxLogin;

    public User login(String openId) throws IOException {
        User user = userDao.get(openId, null, null);
        if (user == null) {
            user = new User();
            user.setBlh(0l);
            user.setName("游客_" + new Date().getTime());
            user.setOpenid(openId);
            userDao.save(user);
        }
        return user;
    }

    public Map getWxSession(String code) throws IOException {
        HashMap params = new HashMap<String, Object>() {
            {
                put("appid", appId);
                put("secret", appSecret);
                put("grant_type", "authorization_code");
            }
        };
        params.put("js_code", code);
        String json = OkHttpUtil.post(wxLogin, params);
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(json, Map.class);
        Object errCode = map.get("errcode");
        if (StringUtils.isEmpty(errCode)) {
            return map;
        }

        throw new IllegalArgumentException(map.get("errmsg").toString());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = userDao.get(s, null, null);
        return userDetails;
    }
}
