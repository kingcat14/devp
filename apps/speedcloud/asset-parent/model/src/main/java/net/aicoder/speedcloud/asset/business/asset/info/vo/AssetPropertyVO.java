package net.aicoder.speedcloud.asset.business.asset.info.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetCmdbVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 资产属性的值对象
*/
@ApiModel(value = "展现资产属性的值对象")
public class AssetPropertyVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**属性名称*/
    @ApiModelProperty(value = "属性名称")
    private String name;


    /**属性值*/
    @ApiModelProperty(value = "属性值")
    private String value;


    /**所属资产*/
    @ApiModelProperty(value = "所属资产")
    private String asset;
    private AssetCmdbVO assetVO;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getValue(){
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getAsset(){
        return asset;
    }
    public void setAsset(String asset) {
        this.asset = asset;
    }
    public AssetCmdbVO getAssetVO(){
        return assetVO;
    }
    public void setAssetVO(AssetCmdbVO assetVO) {
        this.assetVO = assetVO;
    }


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}