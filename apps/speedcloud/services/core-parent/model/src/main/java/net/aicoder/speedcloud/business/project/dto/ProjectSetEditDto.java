package net.aicoder.speedcloud.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 项目集
 * @author icode
 */
@ApiModel(value = "修改项目集使用的DTO")
public class ProjectSetEditDto {


	/**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;


	/**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;


	/**描述*/
	@ApiModelProperty(value = "描述", required = false)
	private String description;



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
