package com.project.demo.dao;

import java.util.List;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.GoodsType;

/**
 * 【请填写功能名称】Mapper接口
 *
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType>
{
    /**
     * 查询【请填写功能名称】
     *
     * @param typeId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    GoodsType selectGoodsTypeById(Long typeId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param goodsType 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    List<GoodsType> selectGoodsTypeList(GoodsType goodsType);

    /**
     * 新增【请填写功能名称】
     *
     * @param goodsType 【请填写功能名称】
     * @return 结果
     */
    int insertGoodsType(GoodsType goodsType);

    /**
     * 修改【请填写功能名称】
     *
     * @param goodsType 【请填写功能名称】
     * @return 结果
     */
    int updateGoodsType(GoodsType goodsType);

    /**
     * 删除【请填写功能名称】
     *
     * @param typeId 【请填写功能名称】ID
     * @return 结果
     */
    int deleteGoodsTypeById(Long typeId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param typeIds 需要删除的数据ID
     * @return 结果
     */
    int deleteGoodsTypeByIds(Long[] typeIds);
}
