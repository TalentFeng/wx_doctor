package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.model.WxMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@RestController
public class WxController {
    @Value("${weixin.base.token}")
    String token;
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
}
