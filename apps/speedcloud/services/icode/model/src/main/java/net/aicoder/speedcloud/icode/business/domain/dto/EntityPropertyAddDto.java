package net.aicoder.speedcloud.icode.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 领域对象属性
 * @author icode
 */
@ApiModel(value = "新增领域对象属性使用的DTO")
@Setter @Getter
public class EntityPropertyAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**所属领域对象*/
	@ApiModelProperty(value = "所属领域对象", required = false, notes = "")
	private String entity;

    /**属性代码*/
	@ApiModelProperty(value = "属性代码", required = false, notes = "")
	private String code;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false, notes = "中文名称")
	private String name;

    /**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "")
	private String type;

    /**引用对象*/
	@ApiModelProperty(value = "引用对象", required = false, notes = "")
	private String relatedEntityId;

    /**引用对象属性代码*/
	@ApiModelProperty(value = "引用对象属性代码", required = false, notes = "")
	private String relatedEntityPropertyId;

    /**排序*/
	@ApiModelProperty(value = "排序", required = false, notes = "")
	private Integer idx;

    /**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "")
	private String memo;

    /**是否主键*/
	@ApiModelProperty(value = "是否主键", required = false, notes = "")
	private Boolean primaryKey;

    /**是否查询条件*/
	@ApiModelProperty(value = "是否查询条件", required = false, notes = "")
	private Boolean search;

    /**可修改*/
	@ApiModelProperty(value = "可修改", required = false, notes = "")
	private Boolean editable;

    /**可为空*/
	@ApiModelProperty(value = "可为空", required = false, notes = "")
	private Boolean nullable;

    /**是否可见*/
	@ApiModelProperty(value = "是否可见", required = false, notes = "")
	private Boolean visible;

    /**持久化*/
	@ApiModelProperty(value = "持久化", required = false, notes = "")
	private Boolean persist;

    /**可排序*/
	@ApiModelProperty(value = "可排序", required = false)
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

	public String getRelatedEntityPropertyId(){
		return relatedEntityPropertyId;
	}
	public void setRelatedEntityPropertyId(String relatedEntityPropertyId) {
		this.relatedEntityPropertyId = relatedEntityPropertyId;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
