package com.kingzoo.kingcat.project.icode4.business.codegen.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 代码生成任务集合
 * @author icode
 */
public class CodeGenRequest {

    /**
    * 模型ID
    * 
    */
	private String modelId;

    /**
    * 模板ID
    * 
    */
	private String tplCodeId;

    /**
    * 目标路径
    * 
    */
	private String projectId;

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getTplCodeId() {
		return tplCodeId;
	}

	public void setTplCodeId(String tplCodeId) {
		this.tplCodeId = tplCodeId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}