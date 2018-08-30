package net.aicoder.speedcloud.business.app.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询代码库详细信息使用的DTO")
public class CodeBaseInfoCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
    @ApiModelProperty(value = "代码库ID", notes = " ")
    private Long codeRepertory;
	@ApiModelProperty(value = "开发语言")
	private String language;
	@ApiModelProperty(value = "语言级别")
	private String languageLevel;


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


    public Long getCodeRepertory(){
        return codeRepertory;
    }
    public void setCodeRepertory(Long codeRepertory) {
        this.codeRepertory = codeRepertory;
    }


	public String getLanguage(){
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}


	public String getLanguageLevel(){
		return languageLevel;
	}
	public void setLanguageLevel(String languageLevel) {
		this.languageLevel = languageLevel;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
