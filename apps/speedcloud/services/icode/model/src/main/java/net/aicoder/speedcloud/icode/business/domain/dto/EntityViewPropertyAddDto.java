package net.aicoder.speedcloud.icode.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 领域对象展现属性
 * @author icode
 */
@ApiModel(value = "新增领域对象展现属性使用的DTO")
@Setter @Getter
public class EntityViewPropertyAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**所属领域对象*/
	@ApiModelProperty(value = "所属领域对象", required = false, notes = "")
	private String entity;

    /**代码*/
	@ApiModelProperty(value = "代码", required = false, notes = "")
	private String code;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false, notes = "")
	private String name;

    /**新增必填*/
	@ApiModelProperty(value = "新增必填", required = false, notes = "")
	private Boolean addRequired;

    /**新增可见*/
	@ApiModelProperty(value = "新增可见", required = false, notes = "")
	private Boolean addViewable;

    /**新增可填*/
	@ApiModelProperty(value = "新增可填", required = false, notes = "")
	private Boolean addable;

    /**修改必填*/
	@ApiModelProperty(value = "修改必填", required = false, notes = "")
	private Boolean editRequired;

    /**修改可见*/
	@ApiModelProperty(value = "修改可见", required = false, notes = "")
	private Boolean editViewable;

    /**可修改*/
	@ApiModelProperty(value = "可修改", required = false, notes = "")
	private Boolean editable;

    /**列表可见*/
	@ApiModelProperty(value = "列表可见", required = false, notes = "")
	private Boolean girdColumn;

    /**可查询*/
	@ApiModelProperty(value = "可查询", required = false, notes = "")
	private Boolean searchCondition;

    /**简单查询条件*/
	@ApiModelProperty(value = "简单查询条件", required = false, notes = "")
	private Boolean simpleSearchCondition;

    /**排序*/
	@ApiModelProperty(value = "排序", required = false, notes = "")
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
