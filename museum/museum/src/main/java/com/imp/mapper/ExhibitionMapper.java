package com.imp.mapper;

import com.imp.model.ExhibitionModel;
import java.util.List;

/**
 * 展品信息表 mapper
 */
public interface ExhibitionMapper {
    /**
     * 按主键删除
     */
    int deleteById(Long exhId);
    /**
     * 按主键删除
     */
    int deleteByIds(List<Long> ids);
    /**
     * 插入数据
     */
    int insert(ExhibitionModel model);
    /**
     * 插入多条数据
     */
    int insertList(List<ExhibitionModel> models);
    /**
     * 插入数据,字段为空时不插入
     */
    int insertSelective(ExhibitionModel model);
    /**
     * 按主键选择
     */
    ExhibitionModel selectById(Long exhId);
    /**
     * 按模型选择
     */
    List<ExhibitionModel> selectByModel(ExhibitionModel model);
    /**
     * 按主键更新,字段为空时不更新
     */
    int updateByIdSelective(ExhibitionModel model);
    int updateByPrimaryKeyWithBLOBs(ExhibitionModel model);
    /**
     * 按主键更新
     */
    int updateById(ExhibitionModel model);
}