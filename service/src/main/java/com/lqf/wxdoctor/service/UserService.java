package com.lqf.wxdoctor.service;

import com.lqf.wxdoctor.domain.User;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface UserService {
    User login(final String code, HttpSession session) throws IOException;
}
