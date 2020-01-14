package com.spring.bean.controller;

import com.spring.annotation.IocService;
import com.spring.bean.service.IocStudyService;
import com.spring.core.ClassUtil;

/**
 * @author Liyong
 * @version 1.0
 * @date 2020/1/14 3:04 PM
 */

public class IocController {

    public static void main(String[] args) {
        ClassUtil classUtil = new ClassUtil();

        IocStudyService iocStudyService = (IocStudyService) classUtil.getBean("iocStudyService");

        System.out.println(iocStudyService.getIocEntity("ioc","基本理解"));
    }
}
