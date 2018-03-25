package com.lqf.wxdoctor.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqf.wxdoctor.common.OkHttpUtil;
import com.lqf.wxdoctor.dao.UserDao;
import com.lqf.wxdoctor.domain.User;
import com.lqf.wxdoctor.domain.WxMessageRequest;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.MessageDigest;
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
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        String s = String.join("", list);
        sha1.update(s.getBytes("UTF-8"));
        byte a[] = sha1.digest();
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < a.length; i++) {
            String shaHex = Integer.toHexString(a[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        String c = hexString.toString();
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
    public boolean login(@RequestParam String code, HttpSession session) throws IOException {
        Response response = OkHttpUtil.post(wxLogin, new HashMap<String, Object>() {
            {
                put("js_code", code);
                put("appid", appId);
                put("secret", appSecret);
                put("grant_type", "authorization_code");
            }
        });
        String json = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(json, Map.class);
        String openId = map.get("openid").toString();
        session.setAttribute("openid", openId);
        session.setAttribute("session_key", map.get("session_key"));
        User user = userDao.get(openId);
        if (user == null) {
            user = new User();
            user.setBlh(0l);
            user.setName("游客_" + new Date().getTime());
            user.setOpenid(openId);
            userDao.save(user);
        }
        if (user.getBlh() == 0) {
            return false;
        }
        return true;
    }

    @PostMapping(value = "/wechat/login")
    public String login(@RequestParam String blh, @RequestParam String name, HttpSession session) throws IOException {

        return "";
    }
}
