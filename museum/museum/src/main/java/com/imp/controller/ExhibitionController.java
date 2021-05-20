package com.imp.controller;

import com.github.pagehelper.PageInfo;
import com.imp.model.ExhibitionModel;
import com.imp.service.ExhibitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 展品信息接口
 */
@RestController
@RequestMapping("exhibition")
public class ExhibitionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExhibitionController.class);


    @Autowired
    private ExhibitionService exhibitionService;

    /**
     * 跳转页面
     */
    @RequestMapping("page")
    public ModelAndView page(ModelAndView mv) {
        mv.setViewName("exhibition");
        return mv;
    }

    /**
     * 插入数据
     * @param model
     */
    @RequestMapping("insert")
    public ExhibitionModel insert(@RequestBody ExhibitionModel model) {
        if (this.exhibitionService.insert(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按博物馆Id查询
     * @param musId
     */
    @RequestMapping("selectByMusId")
    public PageInfo<ExhibitionModel> selectByMusId(Long musId) {
        ExhibitionModel model = new ExhibitionModel();
        model.setMusId(musId);
        return new PageInfo(this.exhibitionService.selectByModel(model));
    }

    /**
     * 按主键更新
     * @param model
     */
    @RequestMapping("update")
    public ExhibitionModel update(@RequestBody ExhibitionModel model) {
        if (this.exhibitionService.updateById(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按主键删除
     * @param model
     */
    @RequestMapping("delete")
    public ExhibitionModel delete(@RequestBody ExhibitionModel model) {
        if (this.exhibitionService.deleteById(model)) {
            return model;
        }
        return null;
    }
}