package net.aicoder.speedcloud.business.deployscheme.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 部署资源类别的值对象
*/
@ApiModel(value = "展现部署资源类别的值对象")
public class ResourcesCategoryVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**代码*/
    @ApiModelProperty(value = "代码")
    private String code;


    /**图标*/
    @ApiModelProperty(value = "图标")
    private String icon;


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

    public String getIcon(){
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
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