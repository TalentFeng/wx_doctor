package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.domain.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionDao {
    public boolean add(@Param("question") Question question);
    public List<Question> list(@Param("uid") Long uid, @Param("start") int start);
}
