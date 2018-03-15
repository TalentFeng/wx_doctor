package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.model.Account;

import java.util.List;

public interface AccountDao {
  List<Account> find(Integer id);
}
