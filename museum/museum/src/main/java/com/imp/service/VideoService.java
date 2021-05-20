package com.imp.service;

import com.alibaba.fastjson.JSONObject;
import com.imp.enums.CheckType;
import com.imp.mapper.VideoMapper;
import com.imp.model.MuseumModel;
import com.imp.model.UserModel;
import com.imp.model.VideoModel;
import com.imp.model.admin.ResultModel;
import com.imp.util.ListUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.imp.util.LoginUtil;
import com.imp.util.admin.FileUtile;
import com.imp.util.admin.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 讲解表 Service
 */
@Service
public class VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private MuseumService museumService;


    /**
     * 上传音频视频
     */
    public ResultModel<VideoModel> uploadFile(MultipartFile file, String date) {
        ResultModel<VideoModel> resultModel = new ResultModel<>();
        if (file == null){
            resultModel.setMessage("上传文件不能为空！");
            return resultModel;
        }
        VideoModel videoModel;
        try {
            videoModel = JSONObject.parseObject(date, VideoModel.class);
        }catch (Exception e){
            resultModel.setMessage("json字符串系列换失败！");
            return resultModel;
        }

        //UserModel user = LoginUtil.getLoginUser();
        if (videoModel.getUserId() == null){
            resultModel.setMessage("userId不能为空！");
            return resultModel;
        }

        if (videoModel.getMusId() == null){
            resultModel.setMessage("musId不能为空！");
            return resultModel;
        }

        MuseumModel museumModel = museumService.selectById(videoModel.getMusId());
        if (museumModel == null){
            resultModel.setMessage("musId不存在！");
            return resultModel;
        }



        try {
            PathUtil pathUtil = PathUtil.AUDIO;
            String fileName = file.getOriginalFilename();
            String newFileName = FileUtile.saveFile2Dir(file, pathUtil.getDiskPath());
            videoModel.setVidName(fileName);
            videoModel.setVidAddr(pathUtil.getUrlPath() + newFileName);
            //videoModel.setUserId(user.getUserId());
            videoModel.setVidTime(new Date());
            videoModel.setMusName(museumModel.getMusName());
            videoModel.setVidStatus(CheckType.NOT_CHECK.getValue());
            this.insert(videoModel);
            resultModel.setSuccess(true);
            resultModel.setData(videoModel);
            return resultModel;
        } catch (Exception e) {
            resultModel.setMessage("存储文件异常！");
            return resultModel;
        }
    }



    /**
     * 按主键删除
     */
    public boolean deleteById(Long vidId) {
        return vidId != null && this.videoMapper.deleteById(vidId) >= 0;
    }

    /**
     * 按主键删除
     */
    public boolean deleteByIds(List<Long> ids) {
        return ids != null && (ids.isEmpty() || this.videoMapper.deleteByIds(ids) > 0);
    }

    /**
     * 插入数据
     */
    public boolean insert(VideoModel model) {
        return model != null && this.videoMapper.insert(model) == 1;
    }

    /**
     * 插入多条数据
     */
    public boolean insertList(List<VideoModel> models) {
        if (models == null) {
            return false;
        }
        if (models.isEmpty()) {
            return true;
        }
        if (models.size() > 100) {
            for (List<VideoModel> list : ListUtil.getSubList(models, 100)) {
                this.videoMapper.insertList(list);
            }
        } else {
            return this.videoMapper.insertList(models) > 0;
        }
        return true;
    }

    /**
     * 插入数据,字段为空时不插入
     */
    public boolean insertSelective(VideoModel model) {
        return model != null && this.videoMapper.insertSelective(model) == 1;
    }

    /**
     * 按主键选择
     */
    public VideoModel selectById(Long vidId) {
        return vidId == null ? null : this.videoMapper.selectById(vidId);
    }

    /**
     * 按模型选择
     */
    public List<VideoModel> selectByModel(VideoModel model) {
        return model == null ? new ArrayList<>(0) : this.videoMapper.selectByModel(model);
    }

    /**
     * 按主键更新,字段为空时不更新
     */
    public boolean updateByIdSelective(VideoModel model) {
        return !(model == null || model.getVidId() == null) && this.videoMapper.updateByIdSelective(model) == 1;
    }

    /**
     * 按主键更新
     */
    public boolean updateById(VideoModel model) {
        return !(model == null || model.getVidId() == null) && this.videoMapper.updateById(model) == 1;
    }

    /**
     * 按主键删除
     */
    public boolean deleteById(VideoModel model) {
        return !(model == null || model.getVidId() == null) && this.videoMapper.deleteById(model.getVidId()) >= 0;
    }

    /**
     * 插入或更新
     */
    public boolean insertOrUpdate(VideoModel model) {
        if (model == null) {
            return false;
        }
        if (model.getVidId() == null) {
            return this.videoMapper.insert(model) == 1;
        }
        return this.videoMapper.updateById(model) == 1;
    }
}