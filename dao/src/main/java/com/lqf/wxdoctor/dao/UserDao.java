package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User get(@Param("openId") String openId, @Param("blh") String blh, @Param("name") String name);
    boolean save(User user);
    boolean update(@Param("uid") Long uid, @Param("oldUid") Long oldUid, @Param("openId") String openId);
}
