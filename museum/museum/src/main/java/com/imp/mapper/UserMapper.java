package com.imp.mapper;

import com.imp.model.UserModel;
import java.util.List;

/**
 * 用户表 mapper
 */
public interface UserMapper {
    /**
     * 按主键删除
     */
    int deleteById(Long userId);
    /**
     * 按主键删除
     */
    int deleteByIds(List<Long> ids);
    /**
     * 插入数据
     */
    int insert(UserModel model);
    /**
     * 插入多条数据
     */
    int insertList(List<UserModel> models);
    /**
     * 插入数据,字段为空时不插入
     */
    int insertSelective(UserModel model);
    /**
     * 按主键选择
     */
    UserModel selectById(Long userId);
    /**
     * 按模型选择
     */
    List<UserModel> selectByModel(UserModel model);
    /**
     * 按主键更新,字段为空时不更新
     */
    int updateByIdSelective(UserModel model);
    /**
     * 按主键更新
     */
    int updateById(UserModel model);
}