package com.pzhu.crm.service;

import com.pzhu.crm.dataobj.BaseResult;
import com.pzhu.crm.dataobj.Pagination;
import com.pzhu.crm.dto.CustomerDTO;

import java.util.List;

/**
 * @author Eason@bianque
 * @date 2018/12/01
 **/
public interface ICustomerService {
    /**
     *  查询客户是否关联，通过条件区分
     */
    BaseResult<List<CustomerDTO>> query(String condition, Pagination pagination);

    /**
     *  查询已关联用户
     */
    BaseResult<List<CustomerDTO>> queryById(String decidedzoneId);

    /**
     * 多条件查询
     */
    BaseResult<List<CustomerDTO>> query(CustomerDTO customer, Pagination pagination);

    /**
     * 关联定区
     * @param decidedzoneId
     * @param customerIds
     * @return
     */
    BaseResult associateDecidedzone(String decidedzoneId, List<String> customerIds);

    /**
     * 取消定区关联
     */
    BaseResult cancleAssociation(List<String> decidedzoneIds);
}
