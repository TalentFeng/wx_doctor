package com.lqf.wxdoctor.web.controller;


import com.lqf.wxdoctor.dao.AnswerDao;
import com.lqf.wxdoctor.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/answer")
public class AnswerController extends BaseController {
    @Autowired
    AnswerDao answerDao;

    @RequestMapping("add")
    public boolean add(@RequestBody Answer answer, HttpSession session) {
        Long uid = (long) getUser().getId();
        answer.setCreatedUser(uid);
        return answerDao.add(answer);
    }

    @RequestMapping("list")
    public List list(@RequestBody Map map, HttpSession session) {
        int qId = Integer.parseInt(map.get("qId").toString());
        int start = Integer.parseInt(map.get("start").toString());
        return answerDao.list(qId, start);
    }
}
