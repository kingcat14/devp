package com.yunkang.saas.platform.business.resource.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


/**
* 资源的值对象
*/
@ApiModel(value = "展现资源的值对象")
public class ResourceVO {

    public static final String TYPE_FUNCTION = "function";
    public static final String TYPE_MODULE = "module";

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "资源名")
    /**资源名*/
    private String name;

    @ApiModelProperty(value = "资源链接")
    /**资源链接*/
    private String url;


    /**
    * 资源类型
    * 
    */
    @ApiModelProperty(value = "资源类型")
    private String type;

    /** 所属应用 */
    @ApiModelProperty(value = "所属应用")
    private String appCode;
    /**资源代码*/
    @ApiModelProperty(value = "资源代码")
    private Long code;
    /**父节点代码*/
    @ApiModelProperty(value = "父节点代码")
    private Long parentCode;

    /**排序*/
    @ApiModelProperty(value = "排序")
    private Integer orderIndex;

    /**是否隐藏菜单*/
    @ApiModelProperty(value = "隐藏菜单")
    private Boolean hidden;

    private List<ResourceVO> children;

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

    public Long getCode(){
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
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

    public String getAppCode() {
        return appCode;
    }
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Long getParentCode() {
        return parentCode;
    }
    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }

    public Boolean getHidden() {
        return hidden;
    }
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @Transient
    public List<ResourceVO> getChildren() {
        return children;
    }
    @Transient
    public void setChildren(List<ResourceVO> children) {
        this.children = children;
    }
    @Transient
    public void addChild(ResourceVO resource){
        if(CollectionUtils.isEmpty(children)){
            children = new ArrayList<>();
        }
        children.add(resource);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}