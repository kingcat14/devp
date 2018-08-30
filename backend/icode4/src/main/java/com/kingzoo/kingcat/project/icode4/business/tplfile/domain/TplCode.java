package com.kingzoo.kingcat.project.icode4.business.tplfile.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 文件模板
 * @author icode
 */
@Entity
@Table
public class TplCode {

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_ACCEPT_MODEL_TYPE_ID = "acceptModelTypeId";
	public static final String PROPERTY_FILE_PATH = "filePath";
	public static final String PROPERTY_FILE_NAME = "fileName";
	public static final String PROPERTY_TPL_SET_ID = "tplSetId";
	public static final String PROPERTY_OVERRIDABLE = "overridable";
	public static final String PROPERTY_CONTENT = "content";


    @Id
    private String id;


    /**
    * 模板名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "模板名称不能为空")
	@Size(max = 255, message = "模板名称超长，最多255个字符")
	private String name;

    /**
    * 模板代码
    * 
    */
    @Column(name = "code")
	@NotNull(message = "模板代码不能为空")
	@Size(max = 255, message = "模板代码超长，最多255个字符")
	private String code;

    /**
    * 模板类型
    * CODE,PROJECT
    */
    @Column(name = "type")
	@NotNull(message = "模板类型不能为空")
	@Size(max = 255, message = "模板类型超长，最多255个字符")
	private String type;

    /**
    * 接收模型类型
    * 
    */
    @Column(name = "accept_model_typeId")
	@NotNull(message = "接收模型类型不能为空")
	private String acceptModelTypeId;
	@Transient
	private String acceptModelTypeName;

    /**
    * 文件路径
    * 
    */
    @Column(name = "file_path")
	@NotNull(message = "文件路径不能为空")
	@Size(max = 255, message = "文件路径超长，最多255个字符")
	private String filePath;

    /**
    * 文件名
    * 
    */
    @Column(name = "file_name")
	@NotNull(message = "文件名不能为空")
	@Size(max = 255, message = "文件名超长，最多255个字符")
	private String fileName;

    /**
    * 所属集合
    * 
    */
    @Column(name = "tpl_setId")
	private String tplSetId;
	@Transient
	private String tplSetName;

    /**
    * 可覆盖
    * 
    */
    @Column(name = "overridable")
	@NotNull(message = "可覆盖不能为空")
	private Boolean overridable;

    /**
    * 文件内容
    * 
    */
    @Column(name = "content")
	private String content;

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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getAcceptModelTypeId(){
		return acceptModelTypeId;
	}
	public void setAcceptModelTypeId(String acceptModelTypeId) {
		this.acceptModelTypeId = acceptModelTypeId;
	}
	public String getAcceptModelTypeName(){
		return acceptModelTypeName;
	}
	public void setAcceptModelTypeName(String acceptModelTypeName) {
		this.acceptModelTypeName = acceptModelTypeName;
	}

	public String getFilePath(){
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName(){
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTplSetId(){
		return tplSetId;
	}
	public void setTplSetId(String tplSetId) {
		this.tplSetId = tplSetId;
	}
	public String getTplSetName(){
		return tplSetName;
	}
	public void setTplSetName(String tplSetName) {
		this.tplSetName = tplSetName;
	}

	public Boolean getOverridable(){
		return overridable;
	}
	public void setOverridable(Boolean overridable) {
		this.overridable = overridable;
	}

	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}