package com.imp.controller.admin;

import com.imp.model.admin.ResultModel;
import com.imp.model.UserModel;
import com.imp.service.UserService;
import com.imp.service.admin.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录接口
 */
@RestController
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    @RequestMapping("login")
    public ModelAndView toLogin(ModelAndView mv){
        mv.setViewName("login");
        return mv;
    }

    /**
     * Web登录接口
     * @param username
     * @param password
     * @param mv
     * @return
     */
    @RequestMapping("webLogin")
    public ModelAndView webLogin(String username,String password,ModelAndView mv){
        //添加用户认证信息
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        //默认登陆成功后跳转的是刚才访问的页面
        try {
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()){
                mv.setViewName("redirect:/index");
                return mv;
            }else{
                usernamePasswordToken.clear();
                mv.setViewName("redirect:/login");
                return mv;
            }
        }catch (UnknownAccountException e){
            mv.addObject("msg","用户名不存在");
            mv.setViewName("login");
            return mv;
        }catch (IncorrectCredentialsException e){
            mv.addObject("msg","密码错误");
            mv.setViewName("login");
            return mv;
        }
    }

    /**
     * APP登录接口
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("appLogin")
    public ResultModel<UserModel> appLogin(String username, String password){
        ResultModel<UserModel> resultModel = new ResultModel();
        resultModel.setSuccess(false);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        //默认登陆成功后跳转的是刚才访问的页面
        try {
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()){
                resultModel.setSuccess(true);
                UserModel userModel = (UserModel)SecurityUtils.getSubject().getPrincipal();
                resultModel.setData(userModel);
            }else{
                usernamePasswordToken.clear();
            }
        }catch (UnknownAccountException e){
            resultModel.setMessage("用户名不存在");
            return resultModel;
        }catch (IncorrectCredentialsException e){
            resultModel.setMessage("密码错误");
            return resultModel;
        }
        return resultModel;
    }





    @GetMapping({"index", "/"})
    public ModelAndView toIndex(ModelAndView mv){
//        Subject subject = SecurityUtils.getSubject();
//        SysUserModel user = (SysUserModel) subject.getPrincipal();
//        mv.addObject("user", user);
        mv.setViewName("index");
        return mv;
    }


//    @GetMapping("appLoginPage")
//    public ModelAndView appLoginPage(ModelAndView mv){
//        mv.setViewName("h5/phoneIndex");
//        return mv;
//    }
//    @GetMapping("appIndex")
//    public ModelAndView appIndex(ModelAndView mv){
//        mv.setViewName("h5/phoneIndex");
//        return mv;
//    }
//    @GetMapping("appMapPage")
//    public ModelAndView appMapPage(ModelAndView mv){
//        mv.setViewName("h5/appMapPage");
//        return mv;
//    }

    @RequestMapping("logout")
    public ModelAndView doLogOut(ModelAndView mv){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        mv.addObject("msg", "安全退出!");
        mv.setViewName("login");
        return mv;
    }

    /**
     * 注册接口
     * @param model
     */
    @RequestMapping("register")
    public ResultModel<UserModel> register(@RequestBody UserModel model) {
        return this.loginService.register(model);
    }



    /**
     * 密码重置接口
     * @param model
     */
    @RequestMapping("restPassword")
    public ResultModel<UserModel> restPassword(@RequestBody UserModel model) {
        return loginService.restPassword(model);
    }

    /**
     * 修改密码接口
     * @param userId  用户ID
     * @param oldPwd
     * @param newPwd
     */
    @RequestMapping("changePassword")
    public ResultModel<UserModel> changePassword(Long userId, String oldPwd, String newPwd) {
        return loginService.changePassword(userId, oldPwd, newPwd);
    }

    /**
     * 用户头像上传接口
     * @param photo
     * @param userId
     */
    @RequestMapping("uploadUserPhoto")
    public ResultModel<UserModel> uploadUserPhoto(@RequestParam("photo") MultipartFile photo, @RequestParam("userId") Long userId) {
        return this.loginService.uploadUserPhoto(photo , userId);
    }


}
