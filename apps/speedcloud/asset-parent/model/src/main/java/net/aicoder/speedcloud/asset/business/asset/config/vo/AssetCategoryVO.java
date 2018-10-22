package net.aicoder.speedcloud.asset.business.asset.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 资产大类的值对象
*/
@ApiModel(value = "展现资产大类的值对象")
public class AssetCategoryVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "租户id")
    private Long tid;


    /**编号*/
    @ApiModelProperty(value = "编号")
    private String num;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**代码*/
    @ApiModelProperty(value = "代码")
    private String code;


    /**展现顺序*/
    @ApiModelProperty(value = "展现顺序")
    private String viewIndex;


    /**说明*/
    @ApiModelProperty(value = "说明")
    private String description;


    public Long getTid(){
        return tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getNum(){
        return num;
    }
    public void setNum(String num) {
        this.num = num;
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

    public String getViewIndex(){
        return viewIndex;
    }
    public void setViewIndex(String viewIndex) {
        this.viewIndex = viewIndex;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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