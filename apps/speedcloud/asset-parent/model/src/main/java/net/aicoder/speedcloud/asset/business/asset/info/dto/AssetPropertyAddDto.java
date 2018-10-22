package net.aicoder.speedcloud.asset.business.asset.info.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资产属性
 * @author icode
 */
@ApiModel(value = "新增资产属性使用的DTO")
public class AssetPropertyAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**属性名称*/
	@ApiModelProperty(value = "属性名称", required = false)
	private String name;

    /**属性值*/
	@ApiModelProperty(value = "属性值", required = false)
	private String value;

    /**所属资产*/
	@ApiModelProperty(value = "所属资产", required = false)
	private Long asset;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

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

	public Long getAsset(){
        return asset;
    }
    public void setAsset(Long asset) {
        this.asset = asset;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
