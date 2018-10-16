package net.aicoder.speedcloud.console.business.jointjs.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 图形数据的值对象
*/
@ApiModel(value = "展现图形数据的值对象")
public class JointDataVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "方案ID")
    private Long scheme;


    /**json*/
    @ApiModelProperty(value = "json")
    private String viewJson;


    /**类型*/
    @ApiModelProperty(value = "类型")
    private String type;


    public Long getScheme(){
        return scheme;
    }
    public void setScheme(Long scheme) {
        this.scheme = scheme;
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


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}