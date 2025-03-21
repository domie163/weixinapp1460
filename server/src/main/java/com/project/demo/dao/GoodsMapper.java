package com.project.demo.dao;

import java.util.List;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.Goods;

/**
 * 商品信息：Mapper接口
 *
 */
public interface GoodsMapper extends BaseMapper<Goods>
{
    /**
     * 查询商品信息：
     *
     * @param goodsId 商品信息：ID
     * @return 商品信息：
     */
    Goods selectGoodsById(Integer goodsId);

    /**
     * 查询商品信息：列表
     *
     * @param goods 商品信息：
     * @return 商品信息：集合
     */
    List<Goods> selectGoodsList(Goods goods);

    /**
     * 新增商品信息：
     *
     * @param goods 商品信息：
     * @return 结果
     */
    int insertGoods(Goods goods);

    /**
     * 修改商品信息：
     *
     * @param goods 商品信息：
     * @return 结果
     */
    int updateGoods(Goods goods);

    /**
     * 删除商品信息：
     *
     * @param goodsId 商品信息：ID
     * @return 结果
     */
    int deleteGoodsById(Integer goodsId);

    /**
     * 批量删除商品信息：
     *
     * @param goodsIds 需要删除的数据ID
     * @return 结果
     */
    int deleteGoodsByIds(Integer[] goodsIds);
}
