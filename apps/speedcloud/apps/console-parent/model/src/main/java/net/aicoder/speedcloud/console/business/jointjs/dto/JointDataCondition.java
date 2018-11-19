package net.aicoder.speedcloud.console.business.jointjs.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询图形数据使用的DTO")
public class JointDataCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "方案ID")
	private Long scheme;
	@ApiModelProperty(value = "方案ID最大值")
	private Long schemeMax;
	@ApiModelProperty(value = "方案ID最小值")
	private Long schemeMin;
	@ApiModelProperty(value = "json")
	private String viewJson;
	@ApiModelProperty(value = "类型")
	private String type;


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


	public Long getScheme(){
		return scheme;
	}
	public void setScheme(Long scheme) {
		this.scheme = scheme;
	}

	public Long getSchemeMin(){
		return schemeMin;
	}
	public void setSchemeMin(Long schemeMin) {
		this.schemeMin = schemeMin;
	}

	public Long getSchemeMax(){
		return schemeMax;
	}
	public void setSchemeMax(Long schemeMax) {
		this.schemeMax = schemeMax;
	}


	public String getViewJson(){
		return viewJson;
	}
	public void setViewJson(String viewJson) {
		this.viewJson = viewJson;
	}


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
