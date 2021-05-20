package com.imp.shiro;


import com.imp.model.UserModel;
import com.imp.service.UserService;
import com.imp.util.PasswordHelper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: No Description
 */
public class CustomRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    @Resource
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("######## Shiro执行授权 ########");
        //能进入这里表示账号已经通过验证了
        UserModel user = (UserModel) principalCollection.getPrimaryPrincipal();
        if (null != user){
            //获取用户的角色与权限
            //Set<String> roleNames = roleService.listRoleNames(user.getUserid());
           // Set<String> perNames = permissionService.listPermissionNames(user.getUserid());
            //授权对象
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //把角色与权限放进去
            //info.setRoles(roleNames);
            //info.setStringPermissions(perNames);
            //管理员权限
            info.addStringPermission("*:*");
            return info;
        }
        logger.debug("****授权失败****用户信息为空!");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.debug("######## Shiro执行认证 ########");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //获取用户输入的账号
        String username = upToken.getUsername();
        System.out.println("####"+username);
        //获取数据库中的密码
        UserModel model = new UserModel();
        model.setUserName(username);
        List<UserModel> list = userService.selectByModel(model);
        UserModel user = null;
        if(!list.isEmpty()){
            user = list.get(0);
        }

        String passwordInDB = "";

        if (null == user){
            return null;
        }else{
            System.out.println("=="+user);
            passwordInDB = user.getUserPassword();
        }
        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        //盐也放进去
        //这样通过applicationContext-shiro.xml里配置的 HashedCredentialsMatcher 进行自动校验
        //我们这里使用ShiroConfiguration
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,passwordInDB, ByteSource.Util.bytes(PasswordHelper.SALT), getName());
        return info;
    }
}
