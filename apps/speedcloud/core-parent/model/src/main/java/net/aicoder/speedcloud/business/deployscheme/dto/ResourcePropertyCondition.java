package net.aicoder.speedcloud.business.deployscheme.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询资源属性使用的DTO")
public class ResourcePropertyCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
    @ApiModelProperty(value = "所属资源")
    private Long resource;
	@ApiModelProperty(value = "属性名称")
	private String name;
	@ApiModelProperty(value = "属性代码")
	private String code;
	@ApiModelProperty(value = "属性值")
	private String value;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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
