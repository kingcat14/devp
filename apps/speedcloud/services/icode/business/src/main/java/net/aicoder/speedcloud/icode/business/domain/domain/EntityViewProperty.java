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
 * 领域对象展现属性
 * @author icode
 */
@Entity()
@Table(name = "domain_entity_view_property")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class EntityViewProperty extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ENTITY = "entity";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ADD_REQUIRED = "addRequired";
	public static final String PROPERTY_ADD_VIEWABLE = "addViewable";
	public static final String PROPERTY_ADDABLE = "addable";
	public static final String PROPERTY_EDIT_REQUIRED = "editRequired";
	public static final String PROPERTY_EDIT_VIEWABLE = "editViewable";
	public static final String PROPERTY_EDITABLE = "editable";
	public static final String PROPERTY_GIRD_COLUMN = "girdColumn";
	public static final String PROPERTY_SEARCH_CONDITION = "searchCondition";
	public static final String PROPERTY_SIMPLE_SEARCH_CONDITION = "simpleSearchCondition";
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
    * 所属领域对象
    * 
    */
    @Column(name = "entity", nullable = false, updatable = true)
	@Size(max = 255, message = "所属领域对象超长，最多255个字符")
	private String entity;

    /**
    * 代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
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
    * 新增必填
    * 
    */
    @Column(name = "add_required", nullable = false, updatable = true)
	private Boolean addRequired;

    /**
    * 新增可见
    * 
    */
    @Column(name = "add_viewable", nullable = false, updatable = true)
	private Boolean addViewable;

    /**
    * 新增可填
    * 
    */
    @Column(name = "addable", nullable = false, updatable = true)
	private Boolean addable;

    /**
    * 修改必填
    * 
    */
    @Column(name = "edit_required", nullable = false, updatable = true)
	private Boolean editRequired;

    /**
    * 修改可见
    * 
    */
    @Column(name = "edit_viewable", nullable = false, updatable = true)
	private Boolean editViewable;

    /**
    * 可修改
    * 
    */
    @Column(name = "editable", nullable = false, updatable = true)
	private Boolean editable;

    /**
    * 列表可见
    * 
    */
    @Column(name = "gird_column", nullable = false, updatable = true)
	private Boolean girdColumn;

    /**
    * 可查询
    * 
    */
    @Column(name = "search_condition", nullable = false, updatable = true)
	private Boolean searchCondition;

    /**
    * 简单查询条件
    * 
    */
    @Column(name = "simple_search_condition", nullable = false, updatable = true)
	private Boolean simpleSearchCondition;

    /**
    * 排序
    * 
    */
    @Column(name = "idx", nullable = true, updatable = true)
	private Integer idx;

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

	public Boolean getAddRequired(){
		return addRequired;
	}
	public void setAddRequired(Boolean addRequired) {
		this.addRequired = addRequired;
	}

	public Boolean getAddViewable(){
		return addViewable;
	}
	public void setAddViewable(Boolean addViewable) {
		this.addViewable = addViewable;
	}

	public Boolean getAddable(){
		return addable;
	}
	public void setAddable(Boolean addable) {
		this.addable = addable;
	}

	public Boolean getEditRequired(){
		return editRequired;
	}
	public void setEditRequired(Boolean editRequired) {
		this.editRequired = editRequired;
	}

	public Boolean getEditViewable(){
		return editViewable;
	}
	public void setEditViewable(Boolean editViewable) {
		this.editViewable = editViewable;
	}

	public Boolean getEditable(){
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getGirdColumn(){
		return girdColumn;
	}
	public void setGirdColumn(Boolean girdColumn) {
		this.girdColumn = girdColumn;
	}

	public Boolean getSearchCondition(){
		return searchCondition;
	}
	public void setSearchCondition(Boolean searchCondition) {
		this.searchCondition = searchCondition;
	}

	public Boolean getSimpleSearchCondition(){
		return simpleSearchCondition;
	}
	public void setSimpleSearchCondition(Boolean simpleSearchCondition) {
		this.simpleSearchCondition = simpleSearchCondition;
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

