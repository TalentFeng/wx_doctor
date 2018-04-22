package com.lqf.wxdoctor.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GistDao {
    Map<String, String> list(@Param("uid") int uid, @Param("blh") int blh);
}
