package com.dly.config;

import com.dly.shiro.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //配置自定义Realm
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

    //配置安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm) {
        //新建一个SecurityManager供ShiroFilterFactoryBean使用
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        //创建加密对象，设置相关属性
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        //设置加密算法
//        matcher.setHashAlgorithmName("MD5");
//        //设置加密次数
//        matcher.setHashIterations(5);
//        //将加密对象放入myRealm
//        myRealm.setCredentialsMatcher(matcher);
        //将myRealm存入SecurityManager对象
        securityManager.setRealm(myRealm);
        //返回SecurityManager
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        //1.初始化一个ShiroFilter工程类
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //2.设置SecurityManager，Shiro使用SecurityManager来管理整个认证和授权流程
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //3.配置URL拦截规则
        Map<String, String> filterMap = new LinkedHashMap<>();

        //4.Shiro内置过滤器
        //anon 任何人都能访问，登录页面
        filterMap.put("/login", "anon");
//        //authc 需要登录才能访问，系统内的全体通知页面
//        filterMap.put("/api/user/notics", "authc");
//        //roles 需要相应的角色才能访问
//        filterMap.put("/api/user/getUser", "roles[admin]");

        //5.让ShiroFilter按这个规则拦截
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //6. 用户没登录被拦截后，调转到登录页了，配置登录页
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }



}
