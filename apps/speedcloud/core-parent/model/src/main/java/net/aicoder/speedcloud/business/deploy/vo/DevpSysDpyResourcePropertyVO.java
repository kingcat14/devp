package net.aicoder.speedcloud.business.deploy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 资源属性的值对象
*/
@ApiModel(value = "展现资源属性的值对象")
public class DevpSysDpyResourcePropertyVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**所属资源*/
    @ApiModelProperty(value = "所属资源")
    private Long resource;
    private DevpSysDpyResourcesVO resourceVO;


    /**属性名称*/
    @ApiModelProperty(value = "属性名称")
    private String name;


    /**属性代码*/
    @ApiModelProperty(value = "属性代码")
    private String code;


    /**属性值*/
    @ApiModelProperty(value = "属性值")
    private String value;


    public Long getResource(){
        return resource;
    }
    public void setResource(Long resource) {
        this.resource = resource;
    }
    public DevpSysDpyResourcesVO getResourceVO(){
        return resourceVO;
    }
    public void setResourceVO(DevpSysDpyResourcesVO resourceVO) {
        this.resourceVO = resourceVO;
    }

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

    public String getValue(){
        return value;
    }
    public void setValue(String value) {
        this.value = value;
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