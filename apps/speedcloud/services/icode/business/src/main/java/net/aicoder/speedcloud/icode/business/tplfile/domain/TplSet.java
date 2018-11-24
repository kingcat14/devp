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
 * 公共代码模板集合
 * @author icode
 */
@Entity()
@Table(name = "tplfile_tpl_set")
//@DynamicUpdate
//@DynamicInsert
public class TplSet extends BaseEntity<String>{

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_PARENT_ID = "parentId";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_DESCRIPTION = "description";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 集合代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "集合代码超长，最多255个字符")
	private String code;

    /**
    * 集合名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "集合名称超长，最多255个字符")
	private String name;

    /**
    * parent_id
    * 
    */
    @Column(name = "parent_id", nullable = true, updatable = true)
	@Size(max = 255, message = "parent_id超长，最多255个字符")
	private String parentId;

    /**
    * 集合类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "集合类型超长，最多255个字符")
	private String type;

    /**
    * 集合描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "集合描述超长，最多255个字符")
	private String description;

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

	public String getParentId(){
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

