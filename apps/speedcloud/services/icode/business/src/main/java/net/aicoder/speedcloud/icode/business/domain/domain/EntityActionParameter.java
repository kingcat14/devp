package net.aicoder.speedcloud.icode.business.domain.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 领域对象行为参数
 * @author icode
 */
@Entity()
@Table(name = "domain_entity_action_parameter")
//@DynamicUpdate
//@DynamicInsert
public class EntityActionParameter extends BaseEntity<String>{

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_MEMO = "memo";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_ENTITY_ACTION = "entityAction";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 对象代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "对象代码超长，最多255个字符")
	private String code;

    /**
    * 对象名称
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "对象名称超长，最多255个字符")
	private String name;

    /**
    * 备注
    * 
    */
    @Column(name = "memo", nullable = false, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String memo;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "描述超长，最多255个字符")
	private String description;

    /**
    * 领域对象行为
    * 
    */
    @Column(name = "entity_action", nullable = false, updatable = true)
	@Size(max = 255, message = "领域对象行为超长，最多255个字符")
	private String entityAction;

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

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntityAction(){
		return entityAction;
	}
	public void setEntityAction(String entityAction) {
		this.entityAction = entityAction;
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

