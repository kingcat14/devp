package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 代码库详细信息
 * @author icode
 */
@ApiModel(value = "修改代码库详细信息使用的DTO")
public class CodeBaseInfoEditDto {


	/**代码库*/
	@ApiModelProperty(value = "代码库", required = false, notes = " ")
	private Long codeRepertory;


	/**开发语言*/
	@ApiModelProperty(value = "开发语言", required = false)
	private String language;


	/**语言级别*/
	@ApiModelProperty(value = "语言级别", required = false)
	private String languageLevel;



	public Long getCodeRepository(){
        return codeRepertory;
    }
    public void setCodeRepository(Long codeRepertory) {
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
