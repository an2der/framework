package com.me.framework.config;

import com.me.framework.handler.ResponseReturnValueHandler;
import com.me.framework.resolver.UserinfoMethodArgumentAnnotationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
/** 核心配置类
 * @author yg
 * @date 2022/6/13 11:37
 * @version 1.0
 */
@Configuration
public class CoreConfig implements WebMvcConfigurer {

    @Autowired
    private UserinfoMethodArgumentAnnotationResolver userinfoMethodArgumentAnnotationResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userinfoMethodArgumentAnnotationResolver);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(new ResponseReturnValueHandler());
    }
}
