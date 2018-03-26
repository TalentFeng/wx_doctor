package com.lqf.wxdoctor.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqf.wxdoctor.common.CryptUtil;
import com.lqf.wxdoctor.common.OkHttpUtil;
import com.lqf.wxdoctor.dao.UserDao;
import com.lqf.wxdoctor.domain.User;
import com.lqf.wxdoctor.domain.WxMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;

@RestController
public class WxController {
    @Value("${weixin.base.token}")
    String token;

    @Value("${weixin.base.appid}")
    String appId;

    @Value("${weixin.base.app-secret}")
    String appSecret;

    @Value("${weixin.server.login}")
    String wxLogin;

    @Autowired
    UserDao userDao;

    @GetMapping(value = "/wechat")
    public String index(@RequestParam String echostr, @RequestParam String nonce,
    @RequestParam String signature, @RequestParam String timestamp) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String list[] = new String[]{
                token, nonce, timestamp
        };
        Arrays.sort(list);
        String s = String.join("", list);
        String c = CryptUtil.sha1(s);
        if (c.equalsIgnoreCase(signature)) {
            return echostr;
        }
        return "";
    }

    @PostMapping(value = "/wechat", produces = MediaType.TEXT_XML_VALUE)
    public @ResponseBody
    WxMessageRequest reply(HttpServletRequest request, @RequestBody WxMessageRequest wxMessageRequest)
    {
        WxMessageRequest a = new WxMessageRequest();
        a.setFromUserName(wxMessageRequest.getToUserName());
        a.setToUserName(wxMessageRequest.getFromUserName());
        a.setCreateTime(Date.from(Instant.now()).getTime());
        a.setMsgType("text");
        a.setContent("test");
        return  a;
    }

    @GetMapping(value = "/wechat/login")
    @Transactional
    public String login(@RequestParam String code, HttpSession session) throws IOException {
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
        User user = userDao.get(openId);
        User user2 = userDao.get("cccc");
        User user3 = userDao.get("dddd");
        if (user == null) {
            user = new User();
            user.setBlh(0l);
            user.setName("游客_" + new Date().getTime());
            user.setOpenid(openId);
            userDao.save(user);
        }
        if (user.getBlh() == 0) {
            return "/pages/login/login";
        }
        if (user.getIsAdmin() == 1) {
            return "/pages/admin/admin";
        }
        return "/pages/counter/counter";
    }

    @PostMapping(value = "/wechat/login")
    public String login(@RequestParam String blh, @RequestParam String name, HttpSession session) throws IOException {

        return "";
    }
}
