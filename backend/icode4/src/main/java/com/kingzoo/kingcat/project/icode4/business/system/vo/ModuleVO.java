package com.kingzoo.kingcat.project.icode4.business.system.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 模块的值对象
*/
public class ModuleVO {


    private String id;


    /**
    * 模块名称
    * 
    */
    private String name;


    /**
    * 模块代码
    * 
    */
    private String code;


    /**
    * 父模块ID
    * 
    */
    private String parentModuleId;
    private String parentModuleName;


    /**
    * 所属系统
    * 
    */
    private String systemId;
    private String systemName;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getParentModuleId(){
        return parentModuleId;
    }
    public void setParentModuleId(String parentModuleId) {
        this.parentModuleId = parentModuleId;
    }
    public String getParentModuleName(){
        return parentModuleName;
    }
    public void setParentModuleName(String parentModuleName) {
        this.parentModuleName = parentModuleName;
    }
    public String getSystemId(){
        return systemId;
    }
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
    public String getSystemName(){
        return systemName;
    }
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}