package com.briup.cms.service.imp;

import com.briup.cms.bean.Customer;
import com.briup.cms.bean.CustomerExample;
import com.briup.cms.mapper.CustomerMapper;
import com.briup.cms.service.ICustomerService;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.StatusCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kj
 * @Date 2020/3/1 15:33
 * @Version 1.0
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired(required = false)
    private CustomerMapper customerMapper;

    @Override
    public void saveOrUpdateCustomer(Customer customer) throws CustomerException {
        if(customer == null) throw new CustomerException(StatusCodeUtil.ERRO_CODE,"customer为空");
        if(customer.getId() == null){

            List<Customer> customers = customerMapper.selectByCustomerSelective(customer);
            if(customers.size() != 0) throw new CustomerException(StatusCodeUtil.ERRO_CODE, "插入数据已存在");

            customerMapper.insert(customer);
        }else if(customer.getId() < 0){
            throw new CustomerException(StatusCodeUtil.ERRO_CODE,"id < 0");
        }else customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public void deleteCustomer(Integer id) throws CustomerException {
        if(id == null) throw new CustomerException(StatusCodeUtil.ERRO_CODE,"id为空");

        if(id > 0 && customerMapper.selectByPrimaryKey(id) != null){
            customerMapper.deleteByPrimaryKey(id);
        }else
            throw new CustomerException(StatusCodeUtil.ERRO_CODE,
                    id < 0 ? "id < 0" : "要删除的id不存在");
    }

    @Override
    public List<Customer> findAllCustomers() throws CustomerException {
        return customerMapper.selectByExample(new CustomerExample());
    }

    @Override
    public Customer findCustomerById(Integer i) throws CustomerException {
        return i != null ? customerMapper.selectByPrimaryKey(i) : null;
    }
}
