package com.zf.config;


import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean TODO:第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        // 添加shiro的内置过滤器

        /*

         anon: 无需认证即可登录
         authc: 必须认证
         user： 拥有记住我 功能才能用
         perms： 拥有对某个资源的权限才能访问
         role： 拥有某个角色才能访问

         * */




        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add","anon");
        filterMap.put("/user/update","authc");
        // filterMap.put("/user/*","authc");


       bean.setFilterChainDefinitionMap(filterMap);

       // 如果没有权限 设置登陆请求

       bean.setLoginUrl("/tologin");

        return bean;
    }




    // DefaultWebSecurityManager TODO：第二步
   @Bean
   public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
       DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

       //关联Realm
       securityManager.setRealm(userRealm);
       return securityManager ;
   }



    //创建realm对象需要自定义 TODO：第一步
   @Bean
   public UserRealm userRealm (){
       return new UserRealm();
   }





}
