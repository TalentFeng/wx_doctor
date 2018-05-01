package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User get(@Param("openId") String openId, @Param("patientId") String patientId, @Param("name") String name);
    User load(@Param("id") int id);
    List<Map> getCases(@Param("blh") int blh);
    boolean save(User user);
    boolean update(@Param("uid") Long uid, @Param("oldUid") Long oldUid, @Param("openId") String openId);
}
