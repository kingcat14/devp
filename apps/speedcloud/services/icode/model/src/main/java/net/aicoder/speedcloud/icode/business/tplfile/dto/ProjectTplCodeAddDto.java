package net.aicoder.speedcloud.icode.business.tplfile.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 组件代码模板
 * @author icode
 */
@ApiModel(value = "新增组件代码模板使用的DTO")
public class ProjectTplCodeAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**模板代码*/
	@ApiModelProperty(value = "模板代码", required = false, notes = "")
	private String code;

    /**模板名称*/
	@ApiModelProperty(value = "模板名称", required = false, notes = "")
	private String name;

    /**生成文件名*/
	@ApiModelProperty(value = "生成文件名", required = false, notes = "")
	private String fileName;

    /**生成文件路径*/
	@ApiModelProperty(value = "生成文件路径", required = false, notes = "相对路径")
	private String filePath;

    /**模板内容*/
	@ApiModelProperty(value = "模板内容", required = false, notes = "")
	private String content;

    /**模板类型*/
	@ApiModelProperty(value = "模板类型", required = false, notes = "")
	private String type;

    /**接受的模型类型*/
	@ApiModelProperty(value = "接受的模型类型", required = false, notes = "")
	private String acceptModelType;

    /**是否可覆盖*/
	@ApiModelProperty(value = "是否可覆盖", required = false, notes = "")
	private Integer overridable;

    /**所属系统组件*/
	@ApiModelProperty(value = "所属系统组件", required = false)
	private String component;

    /**关联的代码模板*/
	@ApiModelProperty(value = "关联的代码模板", required = false)
	private String tplCode;

    /**自动刷新*/
	@ApiModelProperty(value = "自动刷新", required = false, notes = "自动同步公共代码模板的更新")
	private Boolean autoUpdate;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
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

	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

	public String getComponent(){
        return component;
    }
    public void setComponent(String component) {
        this.component = component;
    }

	public String getTplCode(){
        return tplCode;
    }
    public void setTplCode(String tplCode) {
        this.tplCode = tplCode;
    }

	public Boolean getAutoUpdate(){
		return autoUpdate;
	}
	public void setAutoUpdate(Boolean autoUpdate) {
		this.autoUpdate = autoUpdate;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
