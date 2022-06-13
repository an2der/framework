package com.me.framework.handler;

import com.me.framework.common.UserInfo;
import com.me.framework.resolver.UserinfoMethodArgumentAnnotationResolver;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

/** 当前用户信息注解处理
 * @author yg
 * @date 2022/6/9 13:41
 * @version 1.0
 */
@Component
public class UserinfoMethodArgumentHandler extends UserinfoMethodArgumentAnnotationResolver {

    @Override
    public UserInfo resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return null;
    }
}
