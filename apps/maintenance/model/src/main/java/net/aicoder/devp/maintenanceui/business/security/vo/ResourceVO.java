package net.aicoder.devp.maintenanceui.business.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 资源的值对象
*/
@ApiModel(value = "展现资源的值对象")
public class ResourceVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "资源名")
    /**
    * 资源名
    * 
    */
    private String name;

    @ApiModelProperty(value = "资源链接")
    /**
    * 资源链接
    * 
    */
    private String url;

    @ApiModelProperty(value = "资源类型")
    /**
    * 资源类型
    * 
    */
    private String type;

    @ApiModelProperty(value = "资源代码")
    /**
    * 资源代码
    * 
    */
    private String code;

    @ApiModelProperty(value = "父节点")
    /**
    * 父节点
    * 
    */
    private Long parentId;

    @ApiModelProperty(value = "排序")
    /**
    * 排序
    * 
    */
    private Integer orderIndex;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Long getParentId(){
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public Integer getOrderIndex(){
        return orderIndex;
    }
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}