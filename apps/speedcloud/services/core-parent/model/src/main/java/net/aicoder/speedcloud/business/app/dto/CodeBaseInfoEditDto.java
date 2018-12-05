package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 代码基本信息
 * @author icode
 */
@ApiModel(value = "修改代码基本信息使用的DTO")
@Setter @Getter
public class CodeBaseInfoEditDto {


	/**代码库*/
	@ApiModelProperty(value = "代码库", required = false, notes = " ")
	private String codeRepository;


	/**开发语言*/
	@ApiModelProperty(value = "开发语言", required = false)
	private String language;


	/**语言级别*/
	@ApiModelProperty(value = "语言级别", required = false)
	private String languageLevel;



	public String getCodeRepository(){
        return codeRepository;
    }
    public void setCodeRepository(String codeRepository) {
        this.codeRepository = codeRepository;
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
