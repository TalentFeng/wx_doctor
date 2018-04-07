package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.dao.QuestionDao;
import com.lqf.wxdoctor.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionDao questionDao;

    @RequestMapping("add")
    public boolean add(@RequestBody Question question, HttpSession session) {
        Long uid = Long.parseLong(session.getAttribute("uid").toString());
        question.setCreatedUser(uid);
        return questionDao.add(question);
    }

    @RequestMapping("list")
    public List list(@RequestBody Map map, HttpSession session) {
        Long uid = Long.parseLong(session.getAttribute("uid").toString());
        int start = Integer.parseInt(map.get("start").toString());
        return questionDao.list(uid, start);
    }
}
