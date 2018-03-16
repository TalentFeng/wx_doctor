package com.lqf.wxdoctor.web.controller;

import com.lqf.wxdoctor.dao.AccountDao;
import com.lqf.wxdoctor.model.Account;
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
        List<Account> accounts = accountDao.find(1, "d");
        return accounts;
    }

}
