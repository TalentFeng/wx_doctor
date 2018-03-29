package com.lqf.wxdoctor.dao;

import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {
    boolean save(@Param("userInfo") UserInfoDao userInfo);
}
