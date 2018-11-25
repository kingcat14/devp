package net.aicoder.speedcloud.business.deployscheme.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询方案资源关联资产使用的DTO")
public class ResourceInstanceRelationCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "类型", notes = "[类型]")
	private String type;
	@ApiModelProperty(value = "状态", notes = "[状态]true:生效，false:失效")
	private Boolean status;
	@ApiModelProperty(value = "备注", notes = "[备注]")
	private String notes;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "产品编号", notes = "[产品编号]")
	private String project;
    @ApiModelProperty(value = "部署方案编号", notes = "[部署方案编号]")
    private Long scheme;
    @ApiModelProperty(value = "关联资源编号", notes = "[关联资源编号]")
    private Long resource;
	@ApiModelProperty(value = "关联实例编号", notes = "[关联IT资产编号]")
	private Long asset;
	@ApiModelProperty(value = "关联实例编号最大值")
	private Long assetMax;
	@ApiModelProperty(value = "关联实例编号最小值")
	private Long assetMin;
	@ApiModelProperty(value = "关联实例类别代码", notes = "[关联IT资产元素类型]")
	private String assetCategoryCode;
	@ApiModelProperty(value = "关联实例类型代码", notes = "[关联IT资产类型代码]")
	private String assetTypeCode;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}



	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public Boolean getStatus(){
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}


	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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


	public String getProject(){
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}


    public Long getScheme(){
        return scheme;
    }
    public void setScheme(Long scheme) {
        this.scheme = scheme;
    }


    public Long getResource(){
        return resource;
    }
    public void setResource(Long resource) {
        this.resource = resource;
    }


	public Long getAsset(){
		return asset;
	}
	public void setAsset(Long asset) {
		this.asset = asset;
	}

	public Long getAssetMin(){
		return assetMin;
	}
	public void setAssetMin(Long assetMin) {
		this.assetMin = assetMin;
	}

	public Long getAssetMax(){
		return assetMax;
	}
	public void setAssetMax(Long assetMax) {
		this.assetMax = assetMax;
	}


	public String getAssetCategoryCode(){
		return assetCategoryCode;
	}
	public void setAssetCategoryCode(String assetCategoryCode) {
		this.assetCategoryCode = assetCategoryCode;
	}


	public String getAssetTypeCode(){
		return assetTypeCode;
	}
	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
