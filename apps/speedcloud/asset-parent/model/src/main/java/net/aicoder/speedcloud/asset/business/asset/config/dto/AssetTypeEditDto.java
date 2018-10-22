package net.aicoder.speedcloud.asset.business.asset.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资产分类
 * @author icode
 */
@ApiModel(value = "修改资产分类使用的DTO")
public class AssetTypeEditDto {


	/**编号*/
	@ApiModelProperty(value = "编号", required = false)
	private String num;


	/**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;


	/**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;


	/**年限(月)*/
	@ApiModelProperty(value = "年限(月)", required = false)
	private Integer useMonth;


	/**展现顺序*/
	@ApiModelProperty(value = "展现顺序", required = false)
	private String viewIndex;


	/**上级代码*/
	@ApiModelProperty(value = "上级代码", required = false, notes = "最上级的时候，显示的是大类的code")
	private String parentCode;


	/**说明*/
	@ApiModelProperty(value = "说明", required = false)
	private String description;


	/**所属大类*/
	@ApiModelProperty(value = "所属大类", required = false)
	private String assetCategoryCode;



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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
