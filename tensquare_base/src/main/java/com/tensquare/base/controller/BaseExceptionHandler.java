package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 公共异常处理类
 * @ControllerAdvice + @ResponseBody ==RestControllerAdvice
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    //ExceptionHandler:异常处理
    @ExceptionHandler(value=Exception.class)
    //@ResponseBody  //需要将结果返回json给前端
    public Result error(Exception e){
        return  new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
