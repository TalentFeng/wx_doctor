package com.lqf.wxdoctor.dao;

import org.apache.ibatis.annotations.Param;

public interface UserInfo {
    boolean save(@Param("userInfo") UserInfo userInfo);
}
