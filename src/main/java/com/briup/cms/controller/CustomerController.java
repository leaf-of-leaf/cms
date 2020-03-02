package com.briup.cms.controller;

import com.briup.cms.bean.Customer;
import com.briup.cms.service.ICustomerService;
import com.briup.cms.utils.CustomerException;
import com.briup.cms.utils.Message;
import com.briup.cms.utils.MessageUtil;
import com.briup.cms.utils.StatusCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kj
 * @Date 2020/3/1 15:37
 * @Version 1.0
 */
@RestController
@Api(description = "顾客信息操作")
public class CustomerController {

    @Autowired(required = false)
    private ICustomerService customerService;

    @PostMapping("/saveCustomer")
    @ApiOperation("保存顾客")
    public Message<String> saveCustomer(Customer customer){
        try{
            customerService.saveOrUpdateCustomer(customer);
            return MessageUtil.success("成功保存");
        }catch (CustomerException e){
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE,"保存失败:" + e.getMessage());
        }
    }

    @PostMapping("/updateCustomer")
    @ApiOperation("更新顾客")
    public Message<String> updateCustomer(Customer customer){
        try{
            customerService.saveOrUpdateCustomer(customer);
            return MessageUtil.success("成功更新");
        }catch (CustomerException e){
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE,"更新失败:" + e.getMessage());
        }
    }

    @GetMapping("/deleteCustomer")
    @ApiOperation("删除顾客")
    public Message<String> deleteCustomer(Integer i){
        try{
            customerService.deleteCustomer(i);
            return MessageUtil.success("删除成功");
        }catch (CustomerException e){
            return MessageUtil.error(StatusCodeUtil.ERRO_CODE,"删除失败:" + e.getMessage());
        }
    }

    @GetMapping("/findAllCustomers")
    @ApiOperation("查询所有顾客")
    public Message<Object> findAllCustomers(){
        List<Customer> customers = null;
        return (customers = customerService.findAllCustomers()).size() == 0 ?
                MessageUtil.success("查询成功，数据为空") :
                MessageUtil.success(customers);
    }
    @GetMapping("/findCustomerById")
    @ApiOperation("通过id查询顾客")
    public Message<Object> findCustomerById(Integer i){
        Customer customer = null;
        if (i == null) return MessageUtil.error(StatusCodeUtil.ERRO_CODE,"id 为空");
        return (customer = customerService.findCustomerById(i)) == null?
                MessageUtil.success("查询成功，数据为空"):
                MessageUtil.success(customer);
    }

    @GetMapping("/login")
    @ApiOperation("HelloWorld接口")
    public Message<Object> login(Customer customer){
        return customerService.judge(customer) ? MessageUtil.success("成功登陆") : MessageUtil.error(500,"输入错误");
    }
}
