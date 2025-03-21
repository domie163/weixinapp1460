package com.project.demo.dao;

import java.util.List;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.Cart;

/**
 * 购物车：Mapper接口
 *
 */
public interface CartMapper extends BaseMapper<Cart>
{
    /**
     * 查询购物车：
     *
     * @param cartId 购物车：ID
     * @return 购物车：
     */
    Cart selectCartById(Long cartId);

    /**
     * 查询购物车：列表
     *
     * @param cart 购物车：
     * @return 购物车：集合
     */
    List<Cart> selectCartList(Cart cart);

    /**
     * 新增购物车：
     *
     * @param cart 购物车：
     * @return 结果
     */
    int insertCart(Cart cart);

    /**
     * 修改购物车：
     *
     * @param cart 购物车：
     * @return 结果
     */
    int updateCart(Cart cart);

    /**
     * 删除购物车：
     *
     * @param cartId 购物车：ID
     * @return 结果
     */
    int deleteCartById(Long cartId);

    /**
     * 批量删除购物车：
     *
     * @param cartIds 需要删除的数据ID
     * @return 结果
     */
    int deleteCartByIds(Long[] cartIds);
}
