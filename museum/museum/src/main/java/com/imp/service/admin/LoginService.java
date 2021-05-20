package com.imp.service.admin;

import com.imp.model.admin.ResultModel;
import com.imp.model.UserModel;
import com.imp.service.UserService;
import com.imp.util.LoginUtil;
import com.imp.util.PasswordHelper;
import com.imp.util.admin.FileUtile;
import com.imp.util.admin.PathUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * 用户表 Service
 */
@Service
public class LoginService {

    @Autowired
    private UserService userService;
    
    /**
     * 插入数据
     */
    public ResultModel register(UserModel model) {
        ResultModel messageModel = new ResultModel();
        messageModel.setSuccess(false);
        if(model == null){
            messageModel.setMessage("注册数据不能为空！");
            return messageModel;
        }
        if(model.getUserName() == null){
            messageModel.setMessage("注册账号不能为空！");
            return messageModel;
        }
        if(model.getUserPhone() == null){
            messageModel.setMessage("手机号码不能为空！");
            return messageModel;
        }
        if(model.getUserPassword() == null){
            messageModel.setMessage("密码不能为空！");
            return messageModel;
        }

        UserModel user = new UserModel();
        user.setUserName(model.getUserName());
        if(!userService.selectByModel(user).isEmpty()){
            messageModel.setMessage("该账号已存在！");
            return messageModel;
        }

        user = new UserModel();
        user.setUserPhone(model.getUserPhone());
        if(!userService.selectByModel(user).isEmpty()){
            messageModel.setMessage("该手机号已注册！");
            return messageModel;
        }

        model.setUserPassword(PasswordHelper.generatePwdEncrypt(model.getUserPassword()));
        messageModel.setSuccess(userService.insert(model));
        messageModel.setData(model);
        return messageModel;
    }



    /**
     * 重置密码
     */
    public ResultModel<UserModel> restPassword(UserModel model) {
        ResultModel<UserModel> resultModel = new ResultModel<>();
        resultModel.setSuccess(false);
        if(model == null || model.getUserId() == null){
            resultModel.setMessage("userModel不能为空！");
            return  resultModel;
        }
        UserModel user = userService.selectById(model.getUserId());
        if(user == null){
            resultModel.setMessage("该用户不存在！");
            return resultModel;
        }
        user.setUserPassword(PasswordHelper.getDefaultPassword());
        if(!userService.updateById(user)){
            resultModel.setMessage("重置失败！");
            return resultModel;
        }
        resultModel.setSuccess(true);
        resultModel.setData(user);
        return resultModel;
    }

    /**
     * 修改用户密码
     */
    public ResultModel<UserModel> changePassword(Long userId, String oldPwd, String newPwd) {
        ResultModel<UserModel> resultModel = new ResultModel<>();
        resultModel.setSuccess(false);
        if (StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd)) {
            resultModel.setMessage("新密或旧密码不能为空！");
            return resultModel;
        }
//        UserModel userModel = (UserModel) SecurityUtils.getSubject().getPrincipal();
        UserModel userModel = userService.selectById(userId);
        if(userModel == null){
            resultModel.setMessage("userId不存在！");
            return resultModel;
        }

        if (!Objects.equals(userModel.getUserPassword(), PasswordHelper.generatePwdEncrypt(oldPwd))) {
            resultModel.setMessage("旧密码不正确！");
            return resultModel;
        }

        UserModel model = new UserModel();
        model.setUserId(userModel.getUserId());
        model.setUserPassword(PasswordHelper.generatePwdEncrypt(newPwd));
        if(!userService.updateByIdSelective(model)){
            resultModel.setMessage("修改失败！");
            return resultModel;
        }
        resultModel.setSuccess(true);
        resultModel.setData(model);
        return resultModel;
    }


    /**
     * 上传头像
     */
    public ResultModel<UserModel> uploadUserPhoto(MultipartFile photo, Long userId) {
        ResultModel<UserModel> resultModel = new ResultModel<>();
        try {
            if (photo == null){
                resultModel.setMessage("上传照片不能为空！");
                return resultModel;
            }
            UserModel user;
            if(userId == null){
                user = LoginUtil.getLoginUser();
            }else{
                user = userService.selectById(userId);
            }
            if (user == null){
                resultModel.setMessage("userId不存在！");
                return resultModel;
            }

            String fileName = FileUtile.saveFile2Dir(photo, PathUtil.USER_PHOTO.getDiskPath());
            user.setUserPic(PathUtil.USER_PHOTO.getUrlPath() + fileName);
            userService.updateById(user);
            resultModel.setSuccess(true);
            resultModel.setData(user);
            return resultModel;
        } catch (Exception e) {
            resultModel.setMessage("存储照片异常！");
            return resultModel;
        }
    }

}