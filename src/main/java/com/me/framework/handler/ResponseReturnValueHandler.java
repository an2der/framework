package com.me.framework.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.framework.annotation.BusinessController;
import com.me.framework.common.BusinessResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/** 统一返回数据模型
 * @author yg
 * @date 2022/6/13 14:54
 * @version 1.0
 */
public class ResponseReturnValueHandler implements HandlerMethodReturnValueHandler, AsyncHandlerMethodReturnValueHandler {

    @Override
    public boolean isAsyncReturnValue(Object o, MethodParameter methodParameter) {
        return supportsReturnType(methodParameter);
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.getDeclaringClass().isAnnotationPresent(BusinessController.class);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        modelAndViewContainer.setRequestHandled(true);//表示此函数可以处理请求，不必交给别的代码处理
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        if(methodParameter.getMethod().getReturnType().isAssignableFrom(void.class)) {
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }else{
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(mapper.writeValueAsString(new BusinessResponse(o)));
            response.getWriter().flush();
            response.getWriter().close();
        }

    }

}
