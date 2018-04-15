package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.domain.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerDao {
    boolean add(@Param("answer") Answer answer);
    List<Answer> list(@Param("questionId") int questionId, @Param("start") int start);
}
