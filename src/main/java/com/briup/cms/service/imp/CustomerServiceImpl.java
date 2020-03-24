package com.briup.cms.service.imp;

import com.briup.cms.bean.Customer;
import com.briup.cms.bean.CustomerExample;
import com.briup.cms.dao.CustomerDao;
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

    @Autowired(required = false)
    private CustomerDao customerDao;

    @Override
    public void saveOrUpdateCustomer(Customer customer) throws CustomerException {
        if(customer == null) throw new CustomerException(StatusCodeUtil.ERRO_CODE,"customer为空");
        if(customer.getId() == null){

            CustomerExample customerExample = new CustomerExample();
            customerExample.createCriteria().andUsernameEqualTo(customer.getUsername());
            List<Customer> customers = customerMapper.selectByExample(customerExample);
            if(customers.size() != 0) throw new CustomerException(StatusCodeUtil.ERRO_CODE, "插入数据，对应账号已存在");

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

    @Override
    public Boolean login(Customer customer) {

        Customer customer1 = customerDao.findCustomerByUsernameAndPassword(customer.getUsername(), customer.getPassword());

        return customer1 == null ? false : true;
    }

    @Override
    public void register(Customer customer) {
        if(customer == null || customer.getUsername() == null || customer.getPassword() == null)
            throw new CustomerException(500,"注册失败，信息为空");
        if(customerDao.findCustomerByUsername(customer.getUsername()) == null)
        customerDao.save(customer);
        else throw new CustomerException(500,"注册失败，已存在此账户");
    }
}
