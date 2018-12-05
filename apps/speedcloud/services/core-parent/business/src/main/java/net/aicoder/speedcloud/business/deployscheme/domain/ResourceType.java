package net.aicoder.speedcloud.business.deployscheme.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 部署资源类型
 * @author icode
 */
@Entity()
@Table(name = "deployscheme_resource_type")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ResourceType extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CATEGORY = "category";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_ICON = "icon";
	public static final String PROPERTY_IDX = "idx";


    @Id
    @Column(name = "id", length = 32)
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 资源类别
    * 
    */
    @Column(name = "category", nullable = false, updatable = true)
	@Size(max = 255, message = "资源类别超长，最多255个字符")
	private String category;

    /**
    * 名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 图标
    * 
    */
    @Column(name = "icon", nullable = true, updatable = true)
	@Size(max = 255, message = "图标超长，最多255个字符")
	private String icon;

    /**
    * 排序
    * 
    */
    @Column(name = "idx", nullable = false, updatable = true)
	private Integer idx;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getCategory(){
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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

	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIdx(){
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
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

