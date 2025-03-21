package com.project.demo.dao;

import java.util.List;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.Order;

/**
 * 订单：Mapper接口
 *
 */
public interface OrderMapper extends BaseMapper<Order>
{
    /**
     * 查询订单：
     *
     * @param orderId 订单：ID
     * @return 订单：
     */
    Order selectOrderById(Long orderId);

    /**
     * 查询订单：列表
     *
     * @param order 订单：
     * @return 订单：集合
     */
    List<Order> selectOrderList(Order order);

    /**
     * 新增订单：
     *
     * @param order 订单：
     * @return 结果
     */
    int insertOrder(Order order);

    /**
     * 修改订单：
     *
     * @param order 订单：
     * @return 结果
     */
    int updateOrder(Order order);

    /**
     * 删除订单：
     *
     * @param orderId 订单：ID
     * @return 结果
     */
    int deleteOrderById(Long orderId);

    /**
     * 批量删除订单：
     *
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    int deleteOrderByIds(Long[] orderIds);
}
