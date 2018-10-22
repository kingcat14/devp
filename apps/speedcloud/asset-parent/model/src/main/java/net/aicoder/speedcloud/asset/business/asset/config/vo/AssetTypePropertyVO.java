package net.aicoder.speedcloud.asset.business.asset.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetTypeVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 资产分类属性的值对象
*/
@ApiModel(value = "展现资产分类属性的值对象")
public class AssetTypePropertyVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**资产分类*/
    @ApiModelProperty(value = "资产分类")
    private Long assetType;
    private AssetTypeVO assetTypeVO;


    /**属性名称*/
    @ApiModelProperty(value = "属性名称")
    private String name;


    /**属性类型*/
    @ApiModelProperty(value = "属性类型")
    private String type;


    @ApiModelProperty(value = "必填")
    private Boolean required;


    /**备选值*/
    @ApiModelProperty(value = "备选值", notes = "分号分隔的值例如: a:b")
    private String optionValues;


    @ApiModelProperty(value = "顺序")
    private Integer seq;


    public Long getAssetType(){
        return assetType;
    }
    public void setAssetType(Long assetType) {
        this.assetType = assetType;
    }
    public AssetTypeVO getAssetTypeVO(){
        return assetTypeVO;
    }
    public void setAssetTypeVO(AssetTypeVO assetTypeVO) {
        this.assetTypeVO = assetTypeVO;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRequired(){
        return required;
    }
    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getOptionValues(){
        return optionValues;
    }
    public void setOptionValues(String optionValues) {
        this.optionValues = optionValues;
    }

    public Integer getSeq(){
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
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