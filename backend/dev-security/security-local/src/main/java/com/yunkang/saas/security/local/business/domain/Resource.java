package com.yunkang.saas.security.local.business.domain;

import com.yunkang.saas.common.framework.eo.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 * 资源
 * @author icode
 */
@Entity
@Table
public class Resource extends BaseEntity{

	public static final Long TOP_NODE_ID = -1L;

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_URL = "url";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_PARENT_ID = "parentId";
	public static final String PROPERTY_ORDER_INDEX = "orderIndex";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 资源名
    * 
    */
    @Column(name = "name")
	@Size(max = 255, message = "资源名超长，最多255个字符")
	private String name;

    /**
    * 资源链接
    * 
    */
    @Column(name = "url")
	@Size(max = 255, message = "资源链接超长，最多255个字符")
	private String url;

    /**
    * 资源类型
    * 
    */
    @Column(name = "type")
	@Size(max = 255, message = "资源类型超长，最多255个字符")
	private String type;

    /**
    * 资源代码
    * 
    */
    @Column(name = "code")
	@Size(max = 255, message = "资源代码超长，最多255个字符")
	private String code;

    /**
    * 父节点
    * 
    */
    @Column(name = "parent_id")
	private Long parentId;

    /**
    * 排序
    * 
    */
    @Column(name = "order_index")
	private Integer orderIndex;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Long getParentId(){
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getOrderIndex(){
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

