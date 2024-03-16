package com.dly.config;

import com.dly.shiro.JwtFilter;
import com.dly.shiro.MyRealm;
import jakarta.servlet.Filter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //配置自定义Realm
    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }

    //    //配置安全管理器1
//    @Bean
//    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm) {
//        //新建一个SecurityManager供ShiroFilterFactoryBean使用
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
////        //创建加密对象，设置相关属性
////        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
////        //设置加密算法
////        matcher.setHashAlgorithmName("MD5");
////        //设置加密次数
////        matcher.setHashIterations(5);
////        //将加密对象放入myRealm
////        myRealm.setCredentialsMatcher(matcher);
//        //将myRealm存入SecurityManager对象
//        securityManager.setRealm(myRealm);
//        //返回SecurityManager
//        return securityManager;
//    }
    //配置安全管理器2
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm realm) {
        // 创建一个 DefaultWebSecurityManager 实例
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 给安全管理器设置 Realm，用于身份认证和授权
        securityManager.setRealm(realm);

        // 关闭 Shiro 的 session，采用无状态的方式使用 Shiro，适用于基于 JWT 的认证方式
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);  // 关闭 session 存储功能
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        //1.初始化一个ShiroFilter工程类
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //2.设置SecurityManager，Shiro使用SecurityManager来管理整个认证和授权流程
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

//        // 默认认证界面路径---当认证不通过时跳转
//        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        // 配置系统受限资源
        Map<String, String> map = new HashMap<String, String>();
        //map.put("/index.jsp", "authc");
        map.put("/login", "anon");
        map.put("/register", "anon");
//        map.put("/user/**", "authc");
        //map.put("/login.jsp","anon");
        map.put("/**", "jwt");   // 所有请求通过我们自己的过滤器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


//        //3.配置URL拦截规则
//        Map<String, String> filterMap = new LinkedHashMap<>();
//
//        //4.Shiro内置过滤器
//        //anon 任何人都能访问，登录页面
//        filterMap.put("/login", "anon");
//        //authc 需要登录才能访问，系统内的全体通知页面
//        filterMap.put("/api/user/notics", "authc");
//        //roles 需要相应的角色才能访问
//        filterMap.put("/api/user/getUser", "roles[admin]");
//
//        //5.让ShiroFilter按这个规则拦截
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//        //6. 用户没登录被拦截后，调转到登录页了，配置登录页
//        shiroFilterFactoryBean.setLoginUrl("/login");


        return shiroFilterFactoryBean;
    }


}
