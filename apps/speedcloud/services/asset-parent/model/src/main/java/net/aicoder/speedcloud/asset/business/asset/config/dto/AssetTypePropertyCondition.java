package net.aicoder.speedcloud.asset.business.asset.config.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询资产分类属性使用的DTO")
public class AssetTypePropertyCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
    @ApiModelProperty(value = "资产分类")
    private Long assetType;
	@ApiModelProperty(value = "属性名称")
	private String name;
	@ApiModelProperty(value = "属性类型")
	private String type;
	@ApiModelProperty(value = "属性代码")
	private String code;
	@ApiModelProperty(value = "必填")
	private Boolean required;
	@ApiModelProperty(value = "备选值", notes = "分号分隔的值例如: a:b")
	private String optionValues;
	@ApiModelProperty(value = "顺序")
	private Integer seq;
	@ApiModelProperty(value = "顺序最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序最小值")
	private Integer seqMin;


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


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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

	public Integer getSeqMin(){
		return seqMin;
	}
	public void setSeqMin(Integer seqMin) {
		this.seqMin = seqMin;
	}

	public Integer getSeqMax(){
		return seqMax;
	}
	public void setSeqMax(Integer seqMax) {
		this.seqMax = seqMax;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
