package com.imp.controller;


import com.github.pagehelper.PageInfo;
import com.imp.model.ExhibitionModel;
import com.imp.model.VideoModel;
import com.imp.model.admin.ResultListModel;
import com.imp.model.admin.ResultModel;
import com.imp.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 视频表
 */
@RestController
@RequestMapping("video")
public class VideoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoController.class);


    @Autowired
    private VideoService videoService;

    /**
     * 跳转页面
     */
    @RequestMapping("page")
    public ModelAndView page(ModelAndView mv) {
        mv.setViewName("video");
        return mv;
    }



    /**
     * 视频区-上传视频接口
     * @param file 单文件上传
     * @param data json字符串  {"userId":"long 用户Id" ,"musId":"long //博物馆ID","vidInfo":"string //视频介绍"}
     */
    @RequestMapping("uploadVideo")
    public ResultModel<VideoModel> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("data") String data) {
        return this.videoService.uploadFile(file, data);
    }

    /**
     * 按博物馆Id查询
     * @param musId
     */
    @RequestMapping("selectByMusId")
    public PageInfo<VideoModel> selectByMusId(Long musId) {
        VideoModel model = new VideoModel();
        model.setMusId(musId);
        return new PageInfo(this.videoService.selectByModel(model));
    }

    /**
     * 按userId查询
     * @param userId  用户Id
     * @param vidStatus  审核状态 null:查询全部状态； 1：未审核；2：审核未通过；3：审核通过
     */
    @RequestMapping("selectByUserId")
    public PageInfo<VideoModel> selectByUserId(Long userId, Integer vidStatus) {
        VideoModel model = new VideoModel();
        model.setUserId(userId);
        model.setVidStatus(vidStatus);
        return new PageInfo(this.videoService.selectByModel(model));
    }

    /**
     * 插入数据
     * @param model
     */
    @RequestMapping("insert")
    public VideoModel insert(@RequestBody VideoModel model) {
        if (this.videoService.insert(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按模型选择
     */
    @RequestMapping("showAll")
    public PageInfo<VideoModel> showAll() {
        VideoModel model = new VideoModel();
        return new PageInfo(this.videoService.selectByModel(model));
    }

    /**
     * 按主键更新
     * @param model
     */
    @RequestMapping("update")
    public VideoModel update(@RequestBody VideoModel model) {
        if (this.videoService.updateById(model)) {
            return model;
        }
        return null;
    }

    /**
     * 按主键删除
     * @param model
     */
    @RequestMapping("delete")
    public VideoModel delete(@RequestBody VideoModel model) {
        if (this.videoService.deleteById(model)) {
            return model;
        }
        return null;
    }
}