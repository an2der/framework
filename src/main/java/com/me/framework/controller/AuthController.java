package com.me.framework.controller;

import com.me.framework.annotation.BusinessController;
import com.me.framework.bean.pojo.LoginParam;
import com.me.framework.common.BusinessException;
import com.me.framework.util.VerifyCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@BusinessController
@RequestMapping("/api/auth")
public class AuthController {

    private final String VERIFY_CODE = "verifyCode";

    @PostMapping("/login")
    public Object login(@RequestBody LoginParam loginParam, HttpServletRequest request){
        String verifyCode = (String) request.getSession().getAttribute(VERIFY_CODE);
        if(verifyCode == null || !verifyCode.equals(loginParam.getVerifyCode())){
            throw new BusinessException("验证码输入错误");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUsername(), loginParam.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            throw new BusinessException("登录失败");
        }
        return "登录成功";
    }

    @GetMapping("/logout")
    public boolean logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return true;
    }

    @GetMapping("/verifyImage")
    public void fetchVerifyImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String verifyCode = VerifyCodeUtil.createRandom(false,4);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(VERIFY_CODE,verifyCode);
        VerifyCodeUtil.createVCodeImage(response,verifyCode);
    }
}
