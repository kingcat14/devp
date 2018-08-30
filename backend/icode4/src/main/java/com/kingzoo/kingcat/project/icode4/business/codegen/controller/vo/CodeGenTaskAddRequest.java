package com.kingzoo.kingcat.project.icode4.business.codegen.controller.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 代码生成任务
 * @author icode
 */
public class CodeGenTaskAddRequest {

	public static final String PROPERTY_MODEL_IDS = "modelIds";
	public static final String PROPERTY_TPL_CODE_IDS = "tplCodeIds";
	public static final String PROPERTY_DEST_PATH = "destPath";

    /**
	 * 模型ID
	 * 
     */
	@NotNull(message = "模型ID不能为空")
	@Size(max = 255, message = "模型ID超长，最多255个字符")
	private String modelIds;

    /**
	 * 模板ID
	 * 
     */
	@NotNull(message = "模板ID不能为空")
	@Size(max = 255, message = "模板ID超长，最多255个字符")
	private String tplCodeIds;

    /**
	 * 目标路径
	 * 
     */
	@NotNull(message = "目标路径不能为空")
	@Size(max = 255, message = "目标路径超长，最多255个字符")
	private String destPath;


	public String getModelIds(){
		return modelIds;
	}
	public void setModelIds(String modelIds) {
		this.modelIds = modelIds;
	}

	public String getTplCodeIds(){
		return tplCodeIds;
	}
	public void setTplCodeIds(String tplCodeIds) {
		this.tplCodeIds = tplCodeIds;
	}

	public String getDestPath(){
		return destPath;
	}
	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
