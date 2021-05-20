package com.imp.service;

import com.imp.mapper.ExhibitionMapper;
import com.imp.model.ExhibitionModel;
import com.imp.util.ListUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 展品信息表 Service
 */
@Service
public class ExhibitionService {

    @Autowired
    private ExhibitionMapper exhibitionMapper;

    /**
     * 按主键删除
     */
    public boolean deleteById(Long exhId) {
        return exhId != null && this.exhibitionMapper.deleteById(exhId) >= 0;
    }

    /**
     * 按主键删除
     */
    public boolean deleteByIds(List<Long> ids) {
        return ids != null && (ids.isEmpty() || this.exhibitionMapper.deleteByIds(ids) > 0);
    }

    /**
     * 插入数据
     */
    public boolean insert(ExhibitionModel model) {
        return model != null && this.exhibitionMapper.insert(model) == 1;
    }

    /**
     * 插入多条数据
     */
    public boolean insertList(List<ExhibitionModel> models) {
        if (models == null) {
            return false;
        }
        if (models.isEmpty()) {
            return true;
        }
        if (models.size() > 100) {
            for (List<ExhibitionModel> list : ListUtil.getSubList(models, 100)) {
                this.exhibitionMapper.insertList(list);
            }
        } else {
            return this.exhibitionMapper.insertList(models) > 0;
        }
        return true;
    }

    /**
     * 插入数据,字段为空时不插入
     */
    public boolean insertSelective(ExhibitionModel model) {
        return model != null && this.exhibitionMapper.insertSelective(model) == 1;
    }

    /**
     * 按主键选择
     */
    public ExhibitionModel selectById(Long exhId) {
        return exhId == null ? null : this.exhibitionMapper.selectById(exhId);
    }

    /**
     * 按模型选择
     */
    public List<ExhibitionModel> selectByModel(ExhibitionModel model) {
        return model == null ? new ArrayList<>(0) : this.exhibitionMapper.selectByModel(model);
    }

    /**
     * 按主键更新,字段为空时不更新
     */
    public boolean updateByIdSelective(ExhibitionModel model) {
        return !(model == null || model.getExhId() == null) && this.exhibitionMapper.updateByIdSelective(model) == 1;
    }


    /**
     * 按主键更新
     */
    public boolean updateById(ExhibitionModel model) {
        return !(model == null || model.getExhId() == null) && this.exhibitionMapper.updateById(model) == 1;
    }

    /**
     * 按主键删除
     */
    public boolean deleteById(ExhibitionModel model) {
        return !(model == null || model.getExhId() == null) && this.exhibitionMapper.deleteById(model.getExhId()) >= 0;
    }

    /**
     * 插入或更新
     */
    public boolean insertOrUpdate(ExhibitionModel model) {
        if (model == null) {
            return false;
        }
        if (model.getExhId() == null) {
            return this.exhibitionMapper.insert(model) == 1;
        }
        return this.exhibitionMapper.updateById(model) == 1;
    }
}