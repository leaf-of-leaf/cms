package com.briup.cms.dao;

import com.briup.cms.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    Customer findCustomerByUsernameAndPassword(String username, String password);

    Customer findCustomerByUsername(String username);
}
