package net.aicoder.speedcloud.icode.business.tplfile.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 公共代码模板
 * @author icode
 */
@Entity()
@Table(name = "tplfile_tpl_code")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class TplCode extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_CONTENT = "content";
	public static final String PROPERTY_FILE_NAME = "fileName";
	public static final String PROPERTY_FILE_PATH = "filePath";
	public static final String PROPERTY_TPL_SET = "tplSet";
	public static final String PROPERTY_ACCEPT_MODEL_TYPE = "acceptModelType";
	public static final String PROPERTY_OVERRIDABLE = "overridable";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 模板代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "模板代码超长，最多255个字符")
	private String code;

    /**
    * 模板名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "模板名称超长，最多255个字符")
	private String name;

    /**
    * 模板类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "模板类型超长，最多255个字符")
	private String type;

    /**
    * 模板内容
    * 
    */
    @Column(name = "content", nullable = true, updatable = true)
	private String content;

    /**
    * 文件名称
    * 
    */
    @Column(name = "file_name", nullable = false, updatable = true)
	@Size(max = 255, message = "文件名称超长，最多255个字符")
	private String fileName;

    /**
    * 文件路径
    * 
    */
    @Column(name = "file_path", nullable = false, updatable = true)
	@Size(max = 255, message = "文件路径超长，最多255个字符")
	private String filePath;

    /**
    * 模板集合
    * 
    */
    @Column(name = "tpl_set", nullable = true, updatable = true)
	@Size(max = 255, message = "模板集合超长，最多255个字符")
	private String tplSet;

    /**
    * 接受的模型类型
    * 
    */
    @Column(name = "accept_model_type", nullable = false, updatable = true)
	@Size(max = 255, message = "接受的模型类型超长，最多255个字符")
	private String acceptModelType;

    /**
    * 是否可覆盖
    * 
    */
    @Column(name = "overridable", nullable = false, updatable = true)
	private Boolean overridable;

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

	public Boolean getOverridable(){
		return overridable;
	}
	public void setOverridable(Boolean overridable) {
		this.overridable = overridable;
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

