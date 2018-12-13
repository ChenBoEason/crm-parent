package com.pzhu.crm.dao.mapper;

import com.pzhu.crm.dao.entity.CustomerDO;
import com.pzhu.crm.dao.entity.CustomerDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    int countByExample(CustomerDOExample example);

    int deleteByExample(CustomerDOExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomerDO record);

    int insertSelective(CustomerDO record);

    List<CustomerDO> selectByExample(CustomerDOExample example);

    CustomerDO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomerDO record, @Param("example") CustomerDOExample example);

    int updateByExample(@Param("record") CustomerDO record, @Param("example") CustomerDOExample example);

    int updateByPrimaryKeySelective(CustomerDO record);

    int updateByPrimaryKey(CustomerDO record);

    void updateDecidedzone(String decidedzoneId);
}