package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.dao.AnswerDao;
import com.lqf.wxdoctor.dao.QuestionDao;
import com.lqf.wxdoctor.domain.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/question")
public class QuestionController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionDao questionDao;

    @Autowired
    AnswerDao answerDao;

    @RequestMapping("add")
    public boolean add(@RequestBody Question question) {
        Long uid = (long) getUser().getId();
        question.setCreatedUser(uid);
        return questionDao.add(question);
    }

    @RequestMapping("list")
    public List list(@RequestBody Map map) {
        Long uid = (long) getUser().getId();
        int start = Integer.parseInt(map.get("start").toString());
        return questionDao.list(uid, start);
    }

    @RequestMapping("get")
    public Map get(@RequestBody Map map) {
        int id = Integer.parseInt(map.get("id").toString());
        result.put("data", new HashMap() {
            {
                put("question", questionDao.get(id));
                put("answer", answerDao.list(id, 0));
            }
        });
        return  result;
    }
}
