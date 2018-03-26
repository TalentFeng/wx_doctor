package com.lqf.wxdoctor.dao;

import com.lqf.wxdoctor.domain.Case;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface CaseDao {
    List<Case> list(@Param("uid") long uid);
}
