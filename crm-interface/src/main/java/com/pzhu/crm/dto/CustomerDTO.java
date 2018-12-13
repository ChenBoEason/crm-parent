package com.pzhu.crm.dto;

import java.io.Serializable;

/**
 * 客户业务bean
 *
 * @author Eason
 * @date 2018/12/01
 **/
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 6493853983192780662L;

    private String id;

    private String name;

    private String station;

    private String tel;

    private String address;

    private String decidedzoneId;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String name, String station, String tel, String address, String decidedzoneId) {
        this.id = id;
        this.name = name;
        this.station = station;
        this.tel = tel;
        this.address = address;
        this.decidedzoneId = decidedzoneId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDecidedzoneId() {
        return decidedzoneId;
    }

    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }


}
