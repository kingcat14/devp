package net.aicoder.speedcloud.icode.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 组件类型
 * @author icode
 */
@ApiModel(value = "新增组件类型使用的DTO")
@Setter @Getter
public class ComponentTypeAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**排序*/
	@ApiModelProperty(value = "排序", required = false)
	private Integer idx;

    /**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;

    /**种类*/
	@ApiModelProperty(value = "种类", required = false)
	private String category;

    /**默认图标*/
	@ApiModelProperty(value = "默认图标", required = false)
	private String icon;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Integer getIdx(){
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCategory(){
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
