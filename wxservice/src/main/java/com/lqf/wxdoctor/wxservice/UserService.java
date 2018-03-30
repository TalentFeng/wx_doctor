package com.lqf.wxdoctor.wxservice;

import com.lqf.wxdoctor.domain.User;

import java.io.IOException;
import java.util.Map;

public interface UserService {
    User login(String openID) throws IOException;
    Map getWxSession(String code) throws IOException;
}
