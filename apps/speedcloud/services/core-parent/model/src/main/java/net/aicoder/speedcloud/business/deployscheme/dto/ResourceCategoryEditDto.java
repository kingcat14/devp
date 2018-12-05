package net.aicoder.speedcloud.business.deployscheme.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 部署资源类别
 * @author icode
 */
@ApiModel(value = "修改部署资源类别使用的DTO")
@Setter @Getter
public class ResourceCategoryEditDto {


	/**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;


	/**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;


	/**图标*/
	@ApiModelProperty(value = "图标", required = false)
	private String icon;


	/**排序*/
	@ApiModelProperty(value = "排序", required = false)
	private Integer idx;



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


	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}


	public Integer getIdx(){
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
