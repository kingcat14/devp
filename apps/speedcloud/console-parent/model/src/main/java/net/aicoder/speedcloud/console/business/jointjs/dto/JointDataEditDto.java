package net.aicoder.speedcloud.console.business.jointjs.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 图形数据
 * @author icode
 */
@ApiModel(value = "修改图形数据使用的DTO")
public class JointDataEditDto {


	/**方案ID*/
	@ApiModelProperty(value = "方案ID", required = false)
	private Long scheme;


	/**json*/
	@ApiModelProperty(value = "json", required = false)
	private String viewJson;


	/**类型*/
	@ApiModelProperty(value = "类型", required = false)
	private String type;



	public Long getScheme(){
		return scheme;
	}
	public void setScheme(Long scheme) {
		this.scheme = scheme;
	}


	public String getViewJson(){
		return viewJson;
	}
	public void setViewJson(String viewJson) {
		this.viewJson = viewJson;
	}


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
