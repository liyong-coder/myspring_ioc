package com.spring.bean.service;

import com.spring.annotation.IocAutoWired;
import com.spring.annotation.IocService;
import com.spring.bean.dao.IocDao;
import com.spring.bean.entity.IocEntity;

/**
 * @author Liyong
 * @version 1.0
 * @date 2020/1/14 3:05 PM
 */
@IocService
public class IocStudyService {

    @IocAutoWired
    private IocDao iocDao;


    public String getIocEntity(String studyContent,String studylevel){
        IocEntity iocEntity = new IocEntity();
        iocEntity.setStudyContent(studyContent);
        iocEntity.setStudyLevel(studylevel);

        String result = iocDao.getIocEntity(iocEntity);

        return result;

    }



}
