package net.aicoder.speedcloud.business.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 开发语言版本的值对象
*/
@ApiModel(value = "展现开发语言版本的值对象")
public class ConfigDevelopLanguageVersionVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**版本号*/
    @ApiModelProperty(value = "版本号")
    private String name;


    /**开发语言*/
    @ApiModelProperty(value = "开发语言")
    private Long language;
    private ConfigDevelopLanguageVO languageVO;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getLanguage(){
        return language;
    }
    public void setLanguage(Long language) {
        this.language = language;
    }
    public ConfigDevelopLanguageVO getLanguageVO(){
        return languageVO;
    }
    public void setLanguageVO(ConfigDevelopLanguageVO languageVO) {
        this.languageVO = languageVO;
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