package net.aicoder.speedcloud.icode.business.project.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 组件
 * @author icode
 */
@Entity()
@Table(name = "project_component")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class Component extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_PRODUCT = "product";
	public static final String PROPERTY_NUMBER = "number";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_BASE_PACKAGE = "basePackage";
	public static final String PROPERTY_TPL_SET = "tplSet";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_RUNNABLE = "runnable";


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
    * 所属产品
    * 
    */
    @Column(name = "product", nullable = true, updatable = true)
	@Size(max = 255, message = "所属产品超长，最多255个字符")
	private String product;

    /**
    * 组件编号
    * 
    */
    @Column(name = "number", nullable = true, updatable = true)
	private Integer number;

    /**
    * 组件名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "组件名称超长，最多255个字符")
	private String name;

    /**
    * 组件代码
    * 
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "组件代码超长，最多255个字符")
	private String code;

    /**
    * 基础包
    * 
    */
    @Column(name = "base_package", nullable = true, updatable = true)
	@Size(max = 255, message = "基础包超长，最多255个字符")
	private String basePackage;

    /**
    * 代码模板
    * 
    */
    @Column(name = "tpl_set", nullable = true, updatable = true)
	@Size(max = 255, message = "代码模板超长，最多255个字符")
	private String tplSet;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 类型
    * IOS、ANDROID、WEB、应用、服务、公共组件
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 可运行组件
    * 
    */
    @Column(name = "runnable", nullable = false, updatable = true)
	private Boolean runnable;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getProduct(){
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getNumber(){
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

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

	public String getBasePackage(){
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getTplSet(){
		return tplSet;
	}
	public void setTplSet(String tplSet) {
		this.tplSet = tplSet;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Boolean getRunnable(){
		return runnable;
	}
	public void setRunnable(Boolean runnable) {
		this.runnable = runnable;
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

