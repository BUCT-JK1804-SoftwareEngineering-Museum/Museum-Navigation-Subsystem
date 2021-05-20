package com.imp.mapper;

import com.imp.model.MuseumModel;
import java.util.List;

/**
 * 博物馆表 mapper
 */
public interface MuseumMapper {
    /**
     * 按主键删除
     */
    int deleteById(Long musId);
    /**
     * 按主键删除
     */
    int deleteByIds(List<Long> ids);
    /**
     * 插入数据
     */
    int insert(MuseumModel model);
    /**
     * 插入多条数据
     */
    int insertList(List<MuseumModel> models);
    /**
     * 插入数据,字段为空时不插入
     */
    int insertSelective(MuseumModel model);
    /**
     * 按主键选择
     */
    MuseumModel selectById(Long musId);
    /**
     * 按模型选择
     */
    List<MuseumModel> selectByModel(MuseumModel model);
    /**
     * 按主键更新,字段为空时不更新
     */
    int updateByIdSelective(MuseumModel model);
    int updateByPrimaryKeyWithBLOBs(MuseumModel model);
    /**
     * 按主键更新
     */
    int updateById(MuseumModel model);
}