package net.aicoder.speedcloud.business.deployscheme.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资源属性
 * @author icode
 */
@ApiModel(value = "新增资源属性使用的DTO")
public class ResourcePropertyAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**所属资源*/
	@ApiModelProperty(value = "所属资源", required = false)
	private Long resource;

    /**属性名称*/
	@ApiModelProperty(value = "属性名称", required = false)
	private String name;

    /**属性代码*/
	@ApiModelProperty(value = "属性代码", required = false)
	private String code;

    /**属性值*/
	@ApiModelProperty(value = "属性值", required = false)
	private String value;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getResource(){
        return resource;
    }
    public void setResource(Long resource) {
        this.resource = resource;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
