package net.aicoder.speedcloud.business.deployscheme.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.deployscheme.vo.SchemeVO;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 方案资源关联实例的值对象
*/
@ApiModel(value = "展现方案资源关联实例的值对象")
public class ResourceInstanceRelationVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**类型*/
    @ApiModelProperty(value = "类型", notes = "[类型]")
    private String type;


    @ApiModelProperty(value = "状态", notes = "[状态]true:生效，false:失效")
    private Boolean status;


    /**备注*/
    @ApiModelProperty(value = "备注", notes = "[备注]")
    private String notes;


    @ApiModelProperty(value = "顺序号", notes = "[顺序号]")
    private Integer seq;


    @ApiModelProperty(value = "产品编号", notes = "[产品编号]")
    private Long prdRid;


    /**部署方案编号*/
    @ApiModelProperty(value = "部署方案编号", notes = "[部署方案编号]")
    private Long scheme;
    private SchemeVO schemeVO;


    /**关联资源编号*/
    @ApiModelProperty(value = "关联资源编号", notes = "[关联资源编号]")
    private Long resource;
    private ResourceVO resourceVO;


    @ApiModelProperty(value = "关联实例编号", notes = "[关联IT资产编号]")
    private Long asset;


    /**关联实例类别代码*/
    @ApiModelProperty(value = "关联实例类别代码", notes = "[关联IT资产元素类型]")
    private String assetCategoryCode;


    /**关联实例类型代码*/
    @ApiModelProperty(value = "关联实例类型代码", notes = "[关联IT资产类型代码]")
    private String assetTypeCode;


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
    public SchemeVO getSchemeVO(){
        return schemeVO;
    }
    public void setSchemeVO(SchemeVO schemeVO) {
        this.schemeVO = schemeVO;
    }

    public Long getResource(){
        return resource;
    }
    public void setResource(Long resource) {
        this.resource = resource;
    }
    public ResourceVO getResourceVO(){
        return resourceVO;
    }
    public void setResourceVO(ResourceVO resourceVO) {
        this.resourceVO = resourceVO;
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