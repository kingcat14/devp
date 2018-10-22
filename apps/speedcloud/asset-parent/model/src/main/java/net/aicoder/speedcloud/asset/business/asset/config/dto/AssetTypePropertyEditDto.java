package net.aicoder.speedcloud.asset.business.asset.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资产分类属性
 * @author icode
 */
@ApiModel(value = "修改资产分类属性使用的DTO")
public class AssetTypePropertyEditDto {


	/**资产分类*/
	@ApiModelProperty(value = "资产分类", required = false)
	private Long assetType;


	/**属性名称*/
	@ApiModelProperty(value = "属性名称", required = false)
	private String name;


	/**属性类型*/
	@ApiModelProperty(value = "属性类型", required = false)
	private String type;


	/**必填*/
	@ApiModelProperty(value = "必填", required = false)
	private Boolean required;


	/**备选值*/
	@ApiModelProperty(value = "备选值", required = false, notes = "分号分隔的值例如: a:b")
	private String optionValues;


	/**顺序*/
	@ApiModelProperty(value = "顺序", required = false)
	private Integer seq;



	public Long getAssetType(){
        return assetType;
    }
    public void setAssetType(Long assetType) {
        this.assetType = assetType;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
