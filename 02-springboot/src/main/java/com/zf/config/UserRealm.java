package com.zf.config;


import com.zf.pojo.User;
import com.zf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义 UserRealm
//extends AuthorizingRealm


public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;


    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行=>授权Authorization");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermission("user:add");

        // 拿到当前登陆的对象
        Subject subject = SecurityUtils.getSubject();

        User currentUser = (User) subject.getPrincipal(); // 拿到user对象
         // 设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());

        return info;
    }



    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行=>认证Authentication");
        // 用户名，密码 应从数据库取出

        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
         // 连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());

        if(user==null){
            return null; // 抛出异常 unknowAccountException
        }

        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);


        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
