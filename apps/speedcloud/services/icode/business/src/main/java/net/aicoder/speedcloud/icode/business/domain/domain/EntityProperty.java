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
 * 领域对象属性
 * @author icode
 */
@Entity()
@Table(name = "domain_entity_property")
//@DynamicUpdate
//@DynamicInsert
public class EntityProperty extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ENTITY = "entity";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_RELATED_ENTITY_ID = "relatedEntityId";
	public static final String PROPERTY_RELATED_ENTITY_PROPERTY_CODE = "relatedEntityPropertyCode";
	public static final String PROPERTY_IDX = "idx";
	public static final String PROPERTY_MEMO = "memo";
	public static final String PROPERTY_PRIMARY_KEY = "primaryKey";
	public static final String PROPERTY_SEARCH = "search";
	public static final String PROPERTY_EDITABLE = "editable";
	public static final String PROPERTY_NULLABLE = "nullable";
	public static final String PROPERTY_VISIBLE = "visible";
	public static final String PROPERTY_PERSIST = "persist";
	public static final String PROPERTY_SORTABLE = "sortable";


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
    * 所属领域对象
    * 
    */
    @Column(name = "entity", nullable = false, updatable = true)
	@Size(max = 255, message = "所属领域对象超长，最多255个字符")
	private String entity;

    /**
    * 属性代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
    * 名称
    * 中文名称
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

    /**
    * 类型
    * 
    */
    @Column(name = "type", nullable = false, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 引用对象
    * 
    */
    @Column(name = "related_entity_id", nullable = true, updatable = true)
	@Size(max = 255, message = "引用对象超长，最多255个字符")
	private String relatedEntityId;

    /**
    * 引用对象属性代码
    * 
    */
    @Column(name = "related_entity_property_code", nullable = true, updatable = true)
	@Size(max = 255, message = "引用对象属性代码超长，最多255个字符")
	private String relatedEntityPropertyCode;

    /**
    * 排序
    * 
    */
    @Column(name = "idx", nullable = true, updatable = true)
	private Integer idx;

    /**
    * 备注
    * 
    */
    @Column(name = "memo", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String memo;

    /**
    * 是否主键
    * 
    */
    @Column(name = "primary_key", nullable = false, updatable = true)
	private Boolean primaryKey;

    /**
    * 是否查询条件
    * 
    */
    @Column(name = "search", nullable = false, updatable = true)
	private Boolean search;

    /**
    * 可修改
    * 
    */
    @Column(name = "editable", nullable = false, updatable = true)
	private Boolean editable;

    /**
    * 可为空
    * 
    */
    @Column(name = "nullable", nullable = false, updatable = true)
	private Boolean nullable;

    /**
    * 是否可见
    * 
    */
    @Column(name = "visible", nullable = false, updatable = true)
	private Boolean visible;

    /**
    * 持久化
    * 
    */
    @Column(name = "persist", nullable = false, updatable = true)
	private Boolean persist;

    /**
    * 可排序
    * 
    */
    @Column(name = "sortable", nullable = false, updatable = true)
	private Boolean sortable;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getEntity(){
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
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

	public String getRelatedEntityId(){
		return relatedEntityId;
	}
	public void setRelatedEntityId(String relatedEntityId) {
		this.relatedEntityId = relatedEntityId;
	}

	public String getRelatedEntityPropertyCode(){
		return relatedEntityPropertyCode;
	}
	public void setRelatedEntityPropertyCode(String relatedEntityPropertyCode) {
		this.relatedEntityPropertyCode = relatedEntityPropertyCode;
	}

	public Integer getIdx(){
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Boolean getPrimaryKey(){
		return primaryKey;
	}
	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Boolean getSearch(){
		return search;
	}
	public void setSearch(Boolean search) {
		this.search = search;
	}

	public Boolean getEditable(){
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getNullable(){
		return nullable;
	}
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public Boolean getVisible(){
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getPersist(){
		return persist;
	}
	public void setPersist(Boolean persist) {
		this.persist = persist;
	}

	public Boolean getSortable(){
		return sortable;
	}
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
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

