package com.pzhu.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Eason
 * @date 2018/12/01
 **/
@SpringBootApplication(scanBasePackages = "com.pzhu.crm")
@MapperScan("com.pzhu.crm.dao.mapper")
@ImportResource(locations = {"classpath:context-dal.xml"})
public class CrmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmServerApplication.class, args);
    }
}
