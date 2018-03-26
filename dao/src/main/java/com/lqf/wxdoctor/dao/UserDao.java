package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User get(@Param("openId") String openId);
    boolean save(User user);
}
