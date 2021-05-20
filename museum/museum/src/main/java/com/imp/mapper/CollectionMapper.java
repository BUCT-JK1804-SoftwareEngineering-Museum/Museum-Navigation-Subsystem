package com.imp.mapper;

import com.imp.model.CollectionModel;
import java.util.List;

/**
 * 藏品表 mapper
 */
public interface CollectionMapper {
    /**
     * 按主键删除
     */
    int deleteById(Long colId);
    /**
     * 按主键删除
     */
    int deleteByIds(List<Long> ids);
    /**
     * 插入数据
     */
    int insert(CollectionModel model);
    /**
     * 插入多条数据
     */
    int insertList(List<CollectionModel> models);
    /**
     * 插入数据,字段为空时不插入
     */
    int insertSelective(CollectionModel model);
    /**
     * 按主键选择
     */
    CollectionModel selectById(Long colId);
    /**
     * 按模型选择
     */
    List<CollectionModel> selectByModel(CollectionModel model);
    /**
     * 按主键更新,字段为空时不更新
     */
    int updateByIdSelective(CollectionModel model);
    int updateByPrimaryKeyWithBLOBs(CollectionModel model);
    /**
     * 按主键更新
     */
    int updateById(CollectionModel model);
}