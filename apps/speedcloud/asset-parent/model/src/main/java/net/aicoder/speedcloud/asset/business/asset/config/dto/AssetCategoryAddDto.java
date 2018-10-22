package net.aicoder.speedcloud.asset.business.asset.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 资产大类
 * @author icode
 */
@ApiModel(value = "新增资产大类使用的DTO")
public class AssetCategoryAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**编号*/
	@ApiModelProperty(value = "编号", required = false)
	private String num;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;

    /**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;

    /**展现顺序*/
	@ApiModelProperty(value = "展现顺序", required = false)
	private String viewIndex;

    /**说明*/
	@ApiModelProperty(value = "说明", required = false)
	private String description;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
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

	public String getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(String viewIndex) {
		this.viewIndex = viewIndex;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
