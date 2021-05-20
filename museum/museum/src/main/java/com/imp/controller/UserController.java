package com.imp.controller;


import com.imp.model.UserModel;
import com.imp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户表
 */
@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    /**
     * 跳转页面
     */
    @RequestMapping("page")
    public ModelAndView page(ModelAndView mv) {
        mv.setViewName("user");
        return mv;
    }





    /**
     * 插入数据
     * @param model
     */
    @RequestMapping("insert")
    public UserModel insert(@RequestBody UserModel model) {
        if (this.userService.insert(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按模型选择
     */
    @RequestMapping("showAll")
    public List<UserModel> showAll() {
        UserModel model = new UserModel();
        return this.userService.selectByModel(model);
    }

    /**
     * 按主键更新
     * @param model
     */
    @RequestMapping("update")
    public UserModel update(@RequestBody UserModel model) {
        if (this.userService.updateById(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按主键删除
     * @param model
     */
    @RequestMapping("delete")
    public UserModel delete(@RequestBody UserModel model) {
        if (this.userService.deleteById(model)) {
            return model;
        }
        return null;
    }


}