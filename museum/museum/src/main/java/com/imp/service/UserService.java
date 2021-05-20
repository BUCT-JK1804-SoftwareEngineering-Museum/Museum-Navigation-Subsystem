package com.imp.service;

import com.imp.mapper.UserMapper;
import com.imp.model.UserModel;
import com.imp.util.ListUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户表 Service
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 按主键删除
     */
    public boolean deleteById(Long userId) {
        return userId != null && this.userMapper.deleteById(userId) >= 0;
    }

    /**
     * 按主键删除
     */
    public boolean deleteByIds(List<Long> ids) {
        return ids != null && (ids.isEmpty() || this.userMapper.deleteByIds(ids) > 0);
    }

    /**
     * 插入数据
     */
    public boolean insert(UserModel model) {
        return model != null && this.userMapper.insert(model) == 1;
    }

    /**
     * 插入多条数据
     */
    public boolean insertList(List<UserModel> models) {
        if (models == null) {
            return false;
        }
        if (models.isEmpty()) {
            return true;
        }
        if (models.size() > 100) {
            for (List<UserModel> list : ListUtil.getSubList(models, 100)) {
                this.userMapper.insertList(list);
            }
        } else {
            return this.userMapper.insertList(models) > 0;
        }
        return true;
    }

    /**
     * 插入数据,字段为空时不插入
     */
    public boolean insertSelective(UserModel model) {
        return model != null && this.userMapper.insertSelective(model) == 1;
    }

    /**
     * 按主键选择
     */
    public UserModel selectById(Long userId) {
        return userId == null ? null : this.userMapper.selectById(userId);
    }

    /**
     * 按模型选择
     */
    public List<UserModel> selectByModel(UserModel model) {
        if (model == null){
            return Collections.EMPTY_LIST;
        }
        List<UserModel> list = this.userMapper.selectByModel(model);
        for(UserModel user : list){
            if(user.getUserPic() == null){
                user.setUserPic("img/user.png");
            }
        }
        return list;
    }

    /**
     * 按主键更新,字段为空时不更新
     */
    public boolean updateByIdSelective(UserModel model) {
        return !(model == null || model.getUserId() == null) && this.userMapper.updateByIdSelective(model) == 1;
    }

    /**
     * 按主键更新
     */
    public boolean updateById(UserModel model) {
        return !(model == null || model.getUserId() == null) && this.userMapper.updateById(model) == 1;
    }

    /**
     * 按主键删除
     */
    public boolean deleteById(UserModel model) {
        return !(model == null || model.getUserId() == null) && this.userMapper.deleteById(model.getUserId()) >= 0;
    }

    /**
     * 插入或更新
     */
    public boolean insertOrUpdate(UserModel model) {
        if (model == null) {
            return false;
        }
        if (model.getUserId() == null) {
            return this.userMapper.insert(model) == 1;
        }
        return this.userMapper.updateById(model) == 1;
    }
}