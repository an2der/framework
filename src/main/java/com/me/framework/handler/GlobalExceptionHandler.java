package com.me.framework.handler;

import com.me.framework.common.BusinessException;
import com.me.framework.common.BusinessResponse;
import com.me.framework.common.BusinessStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 全局异常处理
 * @author yg
 * @date 2022/6/10 19:32
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BusinessException.class)
    public BusinessResponse businessExceptionHandler(BusinessException e){
        return new BusinessResponse(e);
    }

    @ExceptionHandler(Exception.class)
    public BusinessResponse exceptionHandler(Exception e){
        log.error(e.getMessage(),e);
        return new BusinessResponse(BusinessStatus.ERROR);
    }
}
