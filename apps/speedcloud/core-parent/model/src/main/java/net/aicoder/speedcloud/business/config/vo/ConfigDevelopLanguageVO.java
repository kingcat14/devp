package net.aicoder.speedcloud.business.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 开发语言的值对象
*/
@ApiModel(value = "展现开发语言的值对象")
public class ConfigDevelopLanguageVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
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