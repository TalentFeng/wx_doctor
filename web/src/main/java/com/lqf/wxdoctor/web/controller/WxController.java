package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.common.CryptUtil;
import com.lqf.wxdoctor.dao.CaseDao;
import com.lqf.wxdoctor.domain.Case;
import com.lqf.wxdoctor.domain.User;
import com.lqf.wxdoctor.domain.WxMessageRequest;
import com.lqf.wxdoctor.service.impl.UserServiceImpl;
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
@Transactional
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
    UserServiceImpl userService;

    @Autowired
    CaseDao caseDao;

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
    public HashMap login(@RequestParam String code, HttpSession session) throws IOException {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        User user =userService.login(code, session);
        if (user.getBlh() == 0) {
            hashMap.put("tab", "/pages/login/login");
        }
        List<Case> caseList = caseDao.list(user.getId());
        if (caseList.size() > 0) {
            hashMap.put("case", caseList);
            hashMap.put("userInfo", user);
            hashMap.put("tab", "/pages/index/index");
        }
        if (user.getIsAdmin() == 1) {
            hashMap.put("tab", "/pages/admin/admin");
        }
        return hashMap;
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String blh, @RequestParam String name, HttpSession session) throws IOException {

        return "";
    }
}
