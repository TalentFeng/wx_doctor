package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.common.CryptUtil;
import com.lqf.wxdoctor.dao.CaseDao;
import com.lqf.wxdoctor.domain.Case;
import com.lqf.wxdoctor.domain.User;
import com.lqf.wxdoctor.domain.WxMessageRequest;
import com.lqf.wxdoctor.wxservice.impl.UserServiceImpl;
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
@RequestMapping("/wechat")
public class WxController {
    @Value("${weixin.base.token}")
    String token;

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

    @PostMapping(value = "/", produces = MediaType.TEXT_XML_VALUE)
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

    @GetMapping(value = "/login")
    public Map login(@RequestParam String code, HttpSession session) throws IOException {
        Map hashMap = new HashMap<String, Object>();
        Map<String, String> map = userService.getWxSession(code);
        String openId = map.get("openid");
        User user = userService.login(openId);
        session.setAttribute("openid", openId);
        session.setAttribute("session_key", map.get("session_key"));
        if (user.getBlh() == 0) {
            hashMap.put("tab", "/pages/login/login");
        }
        session.setAttribute("uid", user.getId());
        List<Case> caseList = caseDao.list(user.getId());
        if (caseList.size() > 0) {
//            hashMap.put("case", caseList);
            hashMap.put("name", user.getName());
            hashMap.put("blh", user.getBlh());
            hashMap.put("tab", "/pages/index/index");
        }
        return hashMap;
    }

    @PostMapping(value = "/saveUserInfo")
    public String login(@RequestParam String blh, @RequestParam String name, HttpSession session) throws IOException {
        return "";
    }
}
