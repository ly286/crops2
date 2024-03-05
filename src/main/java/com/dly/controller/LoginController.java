package com.dly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping()
@Tag(name = "登录接口")
public class LoginController {

    @PostMapping("login")
    @Operation(summary = "登录接口", description = "登录")
    public String login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            log.info(username + "登录成功");
            return "登录成功";
        } catch (AuthenticationException e) {
            log.info(username + "登录失败");
            return "用户名或密码错误";
        }
    }

    @GetMapping("login")
    public Object getUser() {
        return SecurityUtils.getSubject();
    }
}
