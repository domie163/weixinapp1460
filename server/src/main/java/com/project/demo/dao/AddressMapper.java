package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.Address;

import java.util.List;

/**
 * 收货地址：Mapper接口
 *
 */
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 查询收货地址：
     *
     * @param addressId 收货地址：ID
     * @return 收货地址：
     */
    Address selectAddressById(Integer addressId);

    /**
     * 查询收货地址：列表
     *
     * @param address 收货地址：
     * @return 收货地址：集合
     */
    List<Address> selectAddressList(Address address);

    /**
     * 新增收货地址：
     *
     * @param address 收货地址：
     * @return 结果
     */
    int insertAddress(Address address);

    /**
     * 修改收货地址：
     *
     * @param address 收货地址：
     * @return 结果
     */
    int updateAddress(Address address);

    /**
     * 删除收货地址：
     *
     * @param addressId 收货地址：ID
     * @return 结果
     */
    int deleteAddressById(Integer addressId);

    /**
     * 批量删除收货地址：
     *
     * @param addressIds 需要删除的数据ID
     * @return 结果
     */
    int deleteAddressByIds(Integer[] addressIds);
}
