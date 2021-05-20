package com.imp.mapper;

import com.imp.model.VideoModel;
import java.util.List;

/**
 * 讲解表 mapper
 */
public interface VideoMapper {
    /**
     * 按主键删除
     */
    int deleteById(Long vidId);
    /**
     * 按主键删除
     */
    int deleteByIds(List<Long> ids);
    /**
     * 插入数据
     */
    int insert(VideoModel model);
    /**
     * 插入多条数据
     */
    int insertList(List<VideoModel> models);
    /**
     * 插入数据,字段为空时不插入
     */
    int insertSelective(VideoModel model);
    /**
     * 按主键选择
     */
    VideoModel selectById(Long vidId);
    /**
     * 按模型选择
     */
    List<VideoModel> selectByModel(VideoModel model);
    /**
     * 按主键更新,字段为空时不更新
     */
    int updateByIdSelective(VideoModel model);
    /**
     * 按主键更新
     */
    int updateById(VideoModel model);
}