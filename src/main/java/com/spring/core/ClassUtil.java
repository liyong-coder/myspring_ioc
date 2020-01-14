package com.spring.core;

import com.spring.annotation.IocAutoWired;
import com.spring.annotation.IocComponent;
import com.spring.annotation.IocService;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Liyong
 * @version 1.0
 * @date 2020/1/14 3:05 PM
 */

public class ClassUtil {


    // 保存bean的容器
    private ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<>();


    private Set<String> beanClass = new HashSet<>();


    public Object getBean(String name){
        return beanMap.get(name);
    }


    public ClassUtil(){
        loadSetting();
    }


    public void loadSetting(){
        // 加载配置路径
        //String scanPath = loadScanPath();

        // 加载所有class
        loadBeanClass("com.spring.bean");

        // 实例化注解的类
        registerBean();

        // 对实例化对象的属性进行依赖注入
        dependency();
    }

    private String loadScanPath(){
        return "";
    }

    private void loadBeanClass(String scanPath){
        try{
            String filePath = scanPath.replace(".","/");
            URL url = this.getClass().getClassLoader().getResource(filePath);
            File file = new File(url.toURI());
            doloadClass(file,filePath);
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
    }

    private void doloadClass(File file,String filePath){

        // 文件夹
        if(file.isDirectory()){
            File[] fileList = file.listFiles();
            for (File f:fileList){
                doloadClass(f,filePath+"/"+f.getName());
            }
        }
        // 文件
        if(file.getName().indexOf(".class")>0){
            // 文件夹格式转包格式
            String packagePath = filePath.replace("/",".");

            beanClass.add(packagePath.replace(".class",""));
        }

    }


    private void registerBean(){
        try{
            for(String className:beanClass){
                Class clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(IocComponent.class) || clazz.isAnnotationPresent(IocService.class)){
                    Object bean = clazz.newInstance();
                    // bean名称,类名首字母小写
                    String cname = clazz.getSimpleName();

                    String beanName = cname.substring(0,1).toLowerCase()+cname.substring(1);

                    beanMap.put(beanName,bean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();

            System.out.println("实例化bean失败");
        }
    }

    private void dependency(){
        try{
            // 遍历所有的bean

            for(Object bean : beanMap.values()){
                Class clazz = bean.getClass();

                Field[] fields = clazz.getDeclaredFields(); // 所有属性

                for(Field field: fields){

                    // 验证属性是否赋有IocAutowird注解
                    if(field.isAnnotationPresent(IocAutoWired.class)){
                        String className = field.getType().getSimpleName();

                        String beanName = className.substring(0,1).toLowerCase()+className.substring(1);

                        Object dependencyBean = beanMap.get(beanName);

                        field.setAccessible(true);  // 私有化变量

                        field.set(bean,dependencyBean); // 设置属性
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
