# selfStudy-Shiro

1. 倒入依赖

2. 配置文件

3. helloworld

   ```java
   //获取当前用户
   Subject currentUser = SecurityUtils.getSubject();
   //通过当前用户拿到session
   Session session = currentUser.getSession();
   
    //判断当前用户是否被认证
           if (!currentUser.isAuthenticated()
   //当前用户的认证 who are they
               currentUser.getPrincipal()
   // 什么角色
               currentUser.hasRole("schwartz")
   //当前用户的权限（不同参数不同效果）
               currentUser.isPermitted("lightsaber:wield")
   
   //注销
                       currentUser.logout();
   
   ```



## springboot集成



