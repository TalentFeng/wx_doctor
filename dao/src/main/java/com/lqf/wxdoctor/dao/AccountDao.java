package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.model.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountDao {
  List<Account> find(@Param("id") Integer id, @Param("name")String name);

  boolean createTableNotExist();
}
