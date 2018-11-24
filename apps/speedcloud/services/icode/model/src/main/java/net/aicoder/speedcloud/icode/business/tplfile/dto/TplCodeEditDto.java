package net.aicoder.speedcloud.icode.business.tplfile.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 公共代码模板
 * @author icode
 */
@ApiModel(value = "修改公共代码模板使用的DTO")
public class TplCodeEditDto {


	/**模板代码*/
	@ApiModelProperty(value = "模板代码", required = false, notes = "")
	private String code;


	/**模板名称*/
	@ApiModelProperty(value = "模板名称", required = false, notes = "")
	private String name;


	/**模板类型*/
	@ApiModelProperty(value = "模板类型", required = false, notes = "")
	private String type;


	/**模板内容*/
	@ApiModelProperty(value = "模板内容", required = false, notes = "")
	private String content;


	/**文件名称*/
	@ApiModelProperty(value = "文件名称", required = false, notes = "")
	private String fileName;


	/**文件路径*/
	@ApiModelProperty(value = "文件路径", required = false, notes = "")
	private String filePath;


	/**模板集合*/
	@ApiModelProperty(value = "模板集合", required = false, notes = "")
	private String tplSet;


	/**接受的模型类型*/
	@ApiModelProperty(value = "接受的模型类型", required = false, notes = "")
	private String acceptModelType;


	/**是否可覆盖*/
	@ApiModelProperty(value = "是否可覆盖", required = false, notes = "")
	private Integer overridable;



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


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFilePath(){
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getTplSet(){
		return tplSet;
	}
	public void setTplSet(String tplSet) {
		this.tplSet = tplSet;
	}


	public String getAcceptModelType(){
		return acceptModelType;
	}
	public void setAcceptModelType(String acceptModelType) {
		this.acceptModelType = acceptModelType;
	}


	public Integer getOverridable(){
		return overridable;
	}
	public void setOverridable(Integer overridable) {
		this.overridable = overridable;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
