package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {
    boolean save(@Param("userInfo") UserInfo userInfo);
}
