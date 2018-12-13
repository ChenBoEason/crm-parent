package com.pzhu.crm.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pzhu.crm.dataobj.BaseResult;
import com.pzhu.crm.dataobj.Pagination;
import com.pzhu.crm.dto.CustomerDTO;
import com.pzhu.crm.dubbo.CustomerFacade;
import com.pzhu.crm.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * dubbo服务接口实现类
 *
 * @author Eason@bianque
 * @date 2018/12/01
 **/
@Service
@Component
public class CustomerFacadeImpl implements CustomerFacade {

    @Autowired
    private ICustomerService customerService;

    @Override
    public BaseResult<List<CustomerDTO>> query(String condition, Pagination pagination) {

        return customerService.query(condition, pagination);
    }

    @Override
    public BaseResult<List<CustomerDTO>> queryById(String decidedzoneId) {
        return customerService.queryById(decidedzoneId);
    }

    @Override
    public BaseResult<List<CustomerDTO>> query(CustomerDTO customer, Pagination pagination) {
        return customerService.query(customer, pagination);
    }

    @Override
    public BaseResult associateDecidedzone(String decidedzoneId, List<String> customerIds) {
        return customerService.associateDecidedzone(decidedzoneId, customerIds);
    }

    @Override
    public BaseResult cancleAssociation(List<String> decidedzoneIds) {
        return customerService.cancleAssociation(decidedzoneIds);
    }
}
