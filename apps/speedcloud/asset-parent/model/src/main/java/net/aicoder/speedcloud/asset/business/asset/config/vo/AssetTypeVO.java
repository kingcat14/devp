package net.aicoder.speedcloud.asset.business.asset.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 资产分类的值对象
*/
@ApiModel(value = "展现资产分类的值对象")
public class AssetTypeVO {

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


    @ApiModelProperty(value = "年限(月)")
    private Integer useMonth;


    /**展现顺序*/
    @ApiModelProperty(value = "展现顺序")
    private String viewIndex;


    /**上级代码*/
    @ApiModelProperty(value = "上级代码", notes = "最上级的时候，显示的是大类的code")
    private String parentCode;


    /**说明*/
    @ApiModelProperty(value = "说明")
    private String description;


    /**所属大类*/
    @ApiModelProperty(value = "所属大类")
    private String assetCategoryCode;


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

    public Integer getUseMonth(){
        return useMonth;
    }
    public void setUseMonth(Integer useMonth) {
        this.useMonth = useMonth;
    }

    public String getViewIndex(){
        return viewIndex;
    }
    public void setViewIndex(String viewIndex) {
        this.viewIndex = viewIndex;
    }

    public String getParentCode(){
        return parentCode;
    }
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssetCategoryCode(){
        return assetCategoryCode;
    }
    public void setAssetCategoryCode(String assetCategoryCode) {
        this.assetCategoryCode = assetCategoryCode;
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