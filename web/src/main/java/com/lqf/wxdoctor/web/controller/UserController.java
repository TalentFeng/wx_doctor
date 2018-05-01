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
        User user = (User) session.getAttribute("user");
        userInfo.setUid(user.getId());
        userInfoDao.save(userInfo);
        return true;
    }

    @RequestMapping("/bind")
    public boolean bind(@RequestBody Map map) throws IOException {
        int blh = Integer.valueOf(map.get("blh").toString());
        String name = (String) map.get("name");
        List<Map> cases = userDao.getCases(blh);
        if (cases.size() > 0 && cases.get(0).get("姓名").toString().equalsIgnoreCase(name)) {
            User user = (User) session.getAttribute("user");
            user.setBlh(blh);
            user.setName(name);
            return userDao.save(user);
        }
        return false;
    }

    @RequestMapping("/case")
    public List getCases(@RequestBody Map map) throws IOException {
        User user = userDao.load(Integer.valueOf(map.get("id").toString()));
        if (this.getUser().getIsAdmin() == 1) {
            List<Map> caseList = userDao.getCases(user.getBlh());
            return caseList;
        }
        return null;
    }

    @RequestMapping("/upload")
    public boolean upload(@RequestBody MultipartFile file) throws IOException {
        userService.handleXlsxFile(file.getInputStream());
        return  false;
    }
}
