package com.lqf.wxdoctor.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lqf.wxdoctor.dao.AccountDao;
import com.lqf.wxdoctor.model.Account;
import org.apache.commons.logging.Log;
import org.apache.ibatis.logging.log4j2.Log4j2AbstractLoggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Configuration
public class HomeController {
    @Autowired
    private AccountDao accountDao;

    @GetMapping("/")
    public List<Account> index() {
        PageHelper.startPage(1, 2).doSelectPage(() -> accountDao.find(1, "dd"));
        List<Account> accounts = accountDao.find(1, "d");
        long total = ((Page) accounts).getTotal();
        return accounts;
    }

}
