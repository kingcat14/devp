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
 * 领域对象行为
 * @author icode
 */
@Entity()
@Table(name = "domain_entity_action")
//@DynamicUpdate
//@DynamicInsert
public class EntityAction extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_MEMO = "memo";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_REQUEST = "request";
	public static final String PROPERTY_RESPONSE = "response";
	public static final String PROPERTY_ENTITY = "entity";
	public static final String PROPERTY_TYPE = "type";


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
    * 代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String code;

    /**
    * 行为名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "行为名称超长，最多255个字符")
	private String name;

    /**
    * 备注
    * 行为的概括性描述
    */
    @Column(name = "memo", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String memo;

    /**
    * 行为描述
    * 行为的操作步骤
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 行为输入对象
    * 
    */
    @Column(name = "request", nullable = true, updatable = true)
	@Size(max = 255, message = "行为输入对象超长，最多255个字符")
	private String request;

    /**
    * 行为响应对象
    * 
    */
    @Column(name = "response", nullable = true, updatable = true)
	@Size(max = 255, message = "行为响应对象超长，最多255个字符")
	private String response;

    /**
    * 所属领域对象
    * 
    */
    @Column(name = "entity", nullable = true, updatable = true)
	@Size(max = 255, message = "所属领域对象超长，最多255个字符")
	private String entity;

    /**
    * 行为类型
    * 备用字段,将来用于标识 增、删、改、查、业务 等行为
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "行为类型超长，最多255个字符")
	private String type;

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

	public String getRequest(){
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse(){
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	public String getEntity(){
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

