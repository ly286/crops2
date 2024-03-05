package com.dly.shiro;

import com.dly.entity.User;
import com.dly.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("进入登录认证");
        // 将 AuthenticationToken 强制转换为 UsernamePasswordToken，以便获取用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 根据用户名从数据库中获取用户信息
        User user = userService.getUserByName(token.getUsername());
        // 如果用户存在，则创建并返回一个 AuthenticationInfo 对象，表示认证成功
        if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        }
        // 如果用户不存在，则返回 null，表示认证失败
        return null;
    }
}
