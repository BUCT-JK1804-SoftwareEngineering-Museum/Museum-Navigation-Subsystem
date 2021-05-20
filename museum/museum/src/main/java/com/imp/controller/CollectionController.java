package com.imp.controller;

import com.github.pagehelper.PageInfo;
import com.imp.model.CollectionModel;
import com.imp.service.CollectionService;
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
 * 藏品接口
 */
@RestController
@RequestMapping("collection")
public class CollectionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionController.class);


    @Autowired
    private CollectionService collectionService;

    /**
     * 跳转页面
     */
    @RequestMapping("page")
    public ModelAndView page(ModelAndView mv) {
        mv.setViewName("collection");
        return mv;
    }

    /**
     * 插入数据
     * @param model
     */
    @RequestMapping("insert")
    public CollectionModel insert(@RequestBody CollectionModel model) {
        if (this.collectionService.insert(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按博物馆Id查询
     * @param musId
     */
    @RequestMapping("selectByMusId")
    public PageInfo<CollectionModel> selectByMusId(Long musId){
        CollectionModel model = new CollectionModel();
        model.setMusId(musId);
        return new PageInfo(this.collectionService.selectByModel(model));
    }

    /**
     * 按主键更新
     * @param model
     */
    @RequestMapping("update")
    public CollectionModel update(@RequestBody CollectionModel model) {
        if (this.collectionService.updateById(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按主键删除
     * @param model
     */
    @RequestMapping("delete")
    public CollectionModel delete(@RequestBody CollectionModel model) {
        if (this.collectionService.deleteById(model)) {
            return model;
        }
        return null;
    }
}