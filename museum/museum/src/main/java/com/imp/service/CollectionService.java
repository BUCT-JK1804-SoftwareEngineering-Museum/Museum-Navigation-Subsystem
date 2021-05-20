package com.imp.service;

import com.imp.mapper.CollectionMapper;
import com.imp.model.CollectionModel;
import com.imp.util.ListUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 藏品表 Service
 */
@Service
public class CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    /**
     * 按主键删除
     */
    public boolean deleteById(Long colId) {
        return colId != null && this.collectionMapper.deleteById(colId) >= 0;
    }

    /**
     * 按主键删除
     */
    public boolean deleteByIds(List<Long> ids) {
        return ids != null && (ids.isEmpty() || this.collectionMapper.deleteByIds(ids) > 0);
    }

    /**
     * 插入数据
     */
    public boolean insert(CollectionModel model) {
        return model != null && this.collectionMapper.insert(model) == 1;
    }

    /**
     * 插入多条数据
     */
    public boolean insertList(List<CollectionModel> models) {
        if (models == null) {
            return false;
        }
        if (models.isEmpty()) {
            return true;
        }
        if (models.size() > 100) {
            for (List<CollectionModel> list : ListUtil.getSubList(models, 100)) {
                this.collectionMapper.insertList(list);
            }
        } else {
            return this.collectionMapper.insertList(models) > 0;
        }
        return true;
    }

    /**
     * 插入数据,字段为空时不插入
     */
    public boolean insertSelective(CollectionModel model) {
        return model != null && this.collectionMapper.insertSelective(model) == 1;
    }

    /**
     * 按主键选择
     */
    public CollectionModel selectById(Long colId) {
        return colId == null ? null : this.collectionMapper.selectById(colId);
    }

    /**
     * 按模型选择
     */
    public List<CollectionModel> selectByModel(CollectionModel model) {
        return model == null ? new ArrayList<>(0) : this.collectionMapper.selectByModel(model);
    }

    /**
     * 按主键更新,字段为空时不更新
     */
    public boolean updateByIdSelective(CollectionModel model) {
        return !(model == null || model.getColId() == null) && this.collectionMapper.updateByIdSelective(model) == 1;
    }



    /**
     * 按主键更新
     */
    public boolean updateById(CollectionModel model) {
        return !(model == null || model.getColId() == null) && this.collectionMapper.updateById(model) == 1;
    }

    /**
     * 按主键删除
     */
    public boolean deleteById(CollectionModel model) {
        return !(model == null || model.getColId() == null) && this.collectionMapper.deleteById(model.getColId()) >= 0;
    }

    /**
     * 插入或更新
     */
    public boolean insertOrUpdate(CollectionModel model) {
        if (model == null) {
            return false;
        }
        if (model.getColId() == null) {
            return this.collectionMapper.insert(model) == 1;
        }
        return this.collectionMapper.updateById(model) == 1;
    }
}