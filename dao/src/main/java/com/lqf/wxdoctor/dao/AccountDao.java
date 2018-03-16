package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.model.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccountDao {
  List<Account> find(@Param("id") Integer id, @Param("name")String name);

  boolean createTableNotExist();
}
