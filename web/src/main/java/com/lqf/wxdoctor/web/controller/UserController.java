package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.dao.CaseDao;
import com.lqf.wxdoctor.dao.UserDao;
import com.lqf.wxdoctor.dao.UserInfoDao;
import com.lqf.wxdoctor.domain.Case;
import com.lqf.wxdoctor.domain.User;
import com.lqf.wxdoctor.domain.UserInfo;
import com.lqf.wxdoctor.wxservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CaseDao caseDao;

    @Autowired
    UserService userService;

    @RequestMapping("/save")
    public boolean save(@RequestBody UserInfo userInfo) throws IOException {
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

    @RequestMapping("/case")
    public List getCases(@RequestBody Map map) throws IOException {
        List<Case> caseList = caseDao.list(Long.parseLong(map.get("id").toString()));
        return caseList;
    }

    @RequestMapping("/upload")
    public boolean upload(@RequestBody MultipartFile file) throws IOException {
        userService.handleXlsxFile(file.getInputStream());
        return  false;
    }
}
