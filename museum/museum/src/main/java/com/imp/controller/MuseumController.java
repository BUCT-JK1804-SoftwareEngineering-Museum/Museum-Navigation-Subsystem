package com.imp.controller;

import com.github.pagehelper.PageInfo;
import com.imp.model.ExhibitionModel;
import com.imp.model.MuseumModel;
import com.imp.service.MuseumService;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 博物馆接口
 */
@RestController
@RequestMapping("museum")
public class MuseumController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MuseumController.class);


    @Autowired
    private MuseumService museumService;

    /**
     * 跳转页面
     */
    @RequestMapping("page")
    public ModelAndView page(ModelAndView mv) {
        mv.setViewName("museum");
        return mv;
    }

    /**
     * 插入数据
     * @param model
     */
    @RequestMapping("insert")
    public MuseumModel insert(@RequestBody MuseumModel model) {
        if (this.museumService.insert(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按名称筛选选择
     * @param name
     */
    @RequestMapping("selectByName")
    public PageInfo<ExhibitionModel> selectByName(String name) {
        MuseumModel model = new MuseumModel();
        model.setMusName(name);
        return new PageInfo(this.museumService.selectByModel(model));
    }
    /**
     * 查询全部
     */
    @RequestMapping("selectAll")
    public PageInfo<ExhibitionModel> selectAll() {
        MuseumModel model = new MuseumModel();
        return new PageInfo(this.museumService.selectByModel(model));
    }

    /**
     * 按主键更新
     * @param model
     */
    @RequestMapping("update")
    public MuseumModel update(@RequestBody MuseumModel model) {
        if (this.museumService.updateById(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按主键删除
     * @param model
     */
    @RequestMapping("delete")
    public MuseumModel delete(@RequestBody MuseumModel model) {
        if (this.museumService.deleteById(model)) {
            return model;
        }
        return null;
    }

//    /**
//     * 初始化经纬度
//     */
//    @RequestMapping("insertLatlng")
//    public MuseumModel insertLatlng() {
//        this.museumService.insertLatlng();
//        return null;
//    }
}