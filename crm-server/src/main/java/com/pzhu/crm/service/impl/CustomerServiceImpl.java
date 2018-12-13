package com.pzhu.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.pzhu.crm.constants.CrmConstant;
import com.pzhu.crm.dao.entity.CustomerDO;
import com.pzhu.crm.dao.entity.CustomerDOExample;
import com.pzhu.crm.dao.mapper.CustomerMapper;
import com.pzhu.crm.dataobj.BaseResult;
import com.pzhu.crm.dataobj.Pagination;
import com.pzhu.crm.dto.CustomerDTO;
import com.pzhu.crm.service.ICustomerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Eason@bianque
 * @date 2018/12/01
 **/
@Service
public class CustomerServiceImpl implements ICustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public BaseResult<List<CustomerDTO>> query(String condition, Pagination pagination) {
        BaseResult<List<CustomerDTO>> result = new BaseResult<>();
        CustomerDOExample example = new CustomerDOExample();
        CustomerDOExample.Criteria criteria = example.createCriteria();

        criteria.andStateEqualTo(CrmConstant.STATE_ALIVE);
        /*查询未关联客户*/
        if (StringUtils.equals(condition, CrmConstant.CRM_CUSTOMER_UNASSOCIATED)) {
            criteria.andDecidedzoneIdIsNull();
        }

        if (StringUtils.equals(condition, CrmConstant.CRM_CUSTOMER_ASSOCIATED)) {
            criteria.andDecidedzoneIdIsNotNull();
        }
        /* 按条件 分页查询 */
        PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
        List<CustomerDO> customerDOS = customerMapper.selectByExample(example);

        result.setData(customerDOToDTO(customerDOS));

        return result;
    }

    @Override
    public BaseResult<List<CustomerDTO>> queryById(String decidedzoneId) {
        BaseResult<List<CustomerDTO>> result = new BaseResult<>();

        if(StringUtils.isBlank(decidedzoneId)){
            result.setSuccess(false);
            result.setMsg("定区id为空");
            return result;
        }

        CustomerDOExample example = new CustomerDOExample();
        CustomerDOExample.Criteria criteria = example.createCriteria();

        criteria.andDecidedzoneIdEqualTo(decidedzoneId);
        List<CustomerDO> customerDOS = customerMapper.selectByExample(example);

        result.setData(customerDOToDTO(customerDOS));

        return result;
    }

    @Override
    public BaseResult<List<CustomerDTO>> query(CustomerDTO customer, Pagination pagination) {
        BaseResult<List<CustomerDTO>> result = new BaseResult<>();

        if (customer == null) {
            result.setSuccess(false);
            result.setMsg("参数不合法");
            return result;
        }

        CustomerDOExample example = new CustomerDOExample();
        CustomerDOExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(customer.getName())){
            criteria.andNameEqualTo(customer.getName());
        }
        if(StringUtils.isNotBlank(customer.getStation())){
            criteria.andStationEqualTo(customer.getStation());
        }
        if(StringUtils.isNotBlank(customer.getTel())){
            criteria.andTelEqualTo(customer.getTel());
        }
        if(StringUtils.isNotBlank(customer.getAddress())){
            criteria.andAddressEqualTo(customer.getAddress());
        }

        PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
        List<CustomerDO> customerDOS = customerMapper.selectByExample(example);

        result.setData(customerDOToDTO(customerDOS));

        return result;
    }

    @Override
    public BaseResult associateDecidedzone(String decidedzoneId, List<String> customerIds) {
        BaseResult result = new BaseResult();

        if (customerIds == null || decidedzoneId == null || customerIds.size() == 0) {
            result.setSuccess(false);
            result.setMsg("参数不合法");
            return result;
        }
        /*  先将包含decidedzoneId的置为null */
        customerMapper.updateDecidedzone(decidedzoneId);

        for (String key : customerIds){

            CustomerDO customerDO = new CustomerDO();
            customerDO.setId(key);
            customerDO.setDecidedzoneId(decidedzoneId);
            customerDO.setGmtModified(new Date());
            customerDO.setModifier("dubbo消费者");
            customerMapper.updateByPrimaryKeySelective(customerDO);
        }
        result.setMsg("关联客户成功");
        logger.info("关联定区成功，定区id:{}  关联客户:{}", decidedzoneId, customerIds);
        return result;
    }

    @Override
    public BaseResult cancleAssociation(List<String> decidedzoneIds) {
        BaseResult result = new BaseResult();
        try {
            for (String decidedzoneId : decidedzoneIds){
                customerMapper.updateDecidedzone(decidedzoneId);
            }
            return result;
        }catch (Exception e){
            logger.error("取消定区关联失败，定区id:{}", decidedzoneIds, e);
            result.setMsg("取消定区关联失败");
            return result;
        }
    }

    private List<CustomerDTO> customerDOToDTO(List<CustomerDO> customerDOS) {
        List<CustomerDTO> datas = new ArrayList<>();
        for (CustomerDO customer : customerDOS) {
            datas.add(customerDOToDTO(customer));
        }
        return datas;
    }

    private CustomerDTO customerDOToDTO(CustomerDO customer) {

        return new CustomerDTO(customer.getId(), customer.getName(), customer.getStation(),
                customer.getTel(), customer.getAddress(), customer.getDecidedzoneId());
    }
}
