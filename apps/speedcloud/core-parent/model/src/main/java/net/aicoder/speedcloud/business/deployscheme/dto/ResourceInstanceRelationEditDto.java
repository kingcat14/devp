package net.aicoder.speedcloud.business.deployscheme.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 方案资源关联实例
 * @author icode
 */
@ApiModel(value = "修改方案资源关联实例使用的DTO")
public class ResourceInstanceRelationEditDto {


	/**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;


	/**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "[类型]")
	private String type;


	/**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]true:生效，false:失效")
	private Boolean status;


	/**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "[备注]")
	private String notes;


	/**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;


	/**产品编号*/
	@ApiModelProperty(value = "产品编号", required = false, notes = "[产品编号]")
	private Long prdRid;


	/**部署方案编号*/
	@ApiModelProperty(value = "部署方案编号", required = false, notes = "[部署方案编号]")
	private Long scheme;


	/**关联资源编号*/
	@ApiModelProperty(value = "关联资源编号", required = false, notes = "[关联资源编号]")
	private Long resource;


	/**关联实例编号*/
	@ApiModelProperty(value = "关联实例编号", required = false, notes = "[关联IT资产编号]")
	private Long asset;


	/**关联实例类别代码*/
	@ApiModelProperty(value = "关联实例类别代码", required = false, notes = "[关联IT资产元素类型]")
	private String assetCategoryCode;


	/**关联实例类型代码*/
	@ApiModelProperty(value = "关联实例类型代码", required = false, notes = "[关联IT资产类型代码]")
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


	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
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
