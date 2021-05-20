package com.imp.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: Shiro配置类
 */
@Configuration
public class ShiroConfiguration {

    @Bean(name = "customRealm")
    public CustomRealm myShiroRealm(HashedCredentialsMatcher matcher){
        CustomRealm myShiroRealm= new CustomRealm();
        //设置密码加密
        myShiroRealm.setCredentialsMatcher(matcher);
        return myShiroRealm;
    }

    //把realm注册到securityManager中
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(HashedCredentialsMatcher matcher){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm(matcher));
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new LinkedHashMap<String, String>();
        //登出
        map.put("/logout","logout");
        //放行连接
        map.put("/webLogin","anon");
        map.put("/appLoginPage","anon");
        map.put("/appLogin","anon");
        map.put("/register","anon");
        map.put("/changePassword","anon");
        map.put("/uploadUserPhoto","anon");
        map.put("/userPhoto/**","anon");
        map.put("/restPassword","anon");
        map.put("/libs/**", "anon");
        map.put("/css/**", "anon");
        map.put("/font-awesome/**", "anon");
        map.put("/fonts/**", "anon");
        map.put("/img/**", "anon");
        map.put("/js/**", "anon");
        map.put("../online/**", "anon");
        map.put("../templates/**", "anon");
        map.put("/file/**", "anon");
        map.put("/collection/**", "anon");
        map.put("/exhibition/**", "anon");
        map.put("/museum/**", "anon");
        map.put("/user/**", "anon");
        map.put("/video/**", "anon");
        map.put("/audio/**", "anon");

        //对所有用户认证
        map.put("/**","authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

}
