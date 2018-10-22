package net.aicoder.speedcloud.asset.business.asset.config.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询资产分类使用的DTO")
public class AssetTypeCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "编号")
	private String num;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "代码")
	private String code;
	@ApiModelProperty(value = "年限(月)")
	private Integer useMonth;
	@ApiModelProperty(value = "年限(月)最大值")
	private Integer useMonthMax;
	@ApiModelProperty(value = "年限(月)最小值")
	private Integer useMonthMin;
	@ApiModelProperty(value = "展现顺序")
	private String viewIndex;
	@ApiModelProperty(value = "上级代码", notes = "最上级的时候，显示的是大类的code")
	private String parentCode;
	@ApiModelProperty(value = "说明")
	private String description;
	@ApiModelProperty(value = "所属大类")
	private String assetCategoryCode;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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

	public Integer getUseMonthMin(){
		return useMonthMin;
	}
	public void setUseMonthMin(Integer useMonthMin) {
		this.useMonthMin = useMonthMin;
	}

	public Integer getUseMonthMax(){
		return useMonthMax;
	}
	public void setUseMonthMax(Integer useMonthMax) {
		this.useMonthMax = useMonthMax;
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
