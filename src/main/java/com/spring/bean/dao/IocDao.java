package com.spring.bean.dao;

import com.spring.annotation.IocComponent;
import com.spring.bean.entity.IocEntity;

/**
 * @author Liyong
 * @version 1.0
 * @date 2020/1/14 3:04 PM
 */
@IocComponent
public class IocDao {


    public String getIocEntity(IocEntity iocEntity){
        return "get success";
    }


}
