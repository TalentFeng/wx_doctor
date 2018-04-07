package com.lqf.wxdoctor.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map handlerControllerException(HttpServletRequest request, Throwable ex)
    {
        logger.info(ex.toString());
        Map map = new HashMap<String, String>() {
            {
                put("err", ex.toString());
            }
        };
        return map;
    }
}
