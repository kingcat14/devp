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
 * 组件类型
 * @author icode
 */
@Entity()
@Table(name = "project_component_type")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ComponentType extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_IDX = "idx";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CATEGORY = "category";
	public static final String PROPERTY_ICON = "icon";


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
    * 排序
    * 
    */
    @Column(name = "idx", nullable = true, updatable = true)
	private Integer idx;

    /**
    * 代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 种类
    * 
    */
    @Column(name = "category", nullable = false, updatable = true)
	@Size(max = 255, message = "种类超长，最多255个字符")
	private String category;

    /**
    * 默认图标
    * 
    */
    @Column(name = "icon", nullable = true, updatable = true)
	@Size(max = 255, message = "默认图标超长，最多255个字符")
	private String icon;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Integer getIdx(){
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
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

	public String getCategory(){
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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

