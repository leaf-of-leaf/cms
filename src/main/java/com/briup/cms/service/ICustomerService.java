package com.briup.cms.service;

import com.briup.cms.bean.Customer;
import com.briup.cms.utils.CustomerException;

import java.util.List;

public interface ICustomerService {
    /**
     * 保存或者更新
     * @param customer
     */
    void saveOrUpdateCustomer(Customer customer) throws CustomerException;

    /**
     * 删除
     * @param id
     */
    void deleteCustomer(Integer id) throws CustomerException;

    /**
     * 找到所有顾客
     * @return
     */
    List<Customer> findAllCustomers() throws CustomerException;

    /**
     * 通过id查找顾客
     * @param i
     * @return
     */
    Customer findCustomerById(Integer i) throws CustomerException;
}
