package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.app.vo.CodeRepertoryVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 代码库详细信息的值对象
*/
@ApiModel(value = "展现代码库详细信息的值对象")
public class CodeBaseInfoVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**代码库ID*/
    @ApiModelProperty(value = "代码库ID", notes = " ")
    private Long codeRepertory;
    private CodeRepertoryVO codeRepertoryVO;


    /**开发语言*/
    @ApiModelProperty(value = "开发语言")
    private String language;


    /**语言级别*/
    @ApiModelProperty(value = "语言级别")
    private String languageLevel;


    public Long getCodeRepertory(){
        return codeRepertory;
    }
    public void setCodeRepertory(Long codeRepertory) {
        this.codeRepertory = codeRepertory;
    }
    public CodeRepertoryVO getCodeRepertoryVO(){
        return codeRepertoryVO;
    }
    public void setCodeRepertoryVO(CodeRepertoryVO codeRepertoryVO) {
        this.codeRepertoryVO = codeRepertoryVO;
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