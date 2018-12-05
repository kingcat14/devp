package net.aicoder.speedcloud.icode.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询领域对象展现属性使用的DTO")
@Getter @Setter
public class EntityViewPropertyCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
    @ApiModelProperty(value = "所属领域对象", notes = "")
    private String entity;
	@ApiModelProperty(value = "代码", notes = "")
	private String code;
	@ApiModelProperty(value = "名称", notes = "")
	private String name;
	@ApiModelProperty(value = "新增必填", notes = "")
	private Boolean addRequired;
	@ApiModelProperty(value = "新增可见", notes = "")
	private Boolean addViewable;
	@ApiModelProperty(value = "新增可填", notes = "")
	private Boolean addable;
	@ApiModelProperty(value = "修改必填", notes = "")
	private Boolean editRequired;
	@ApiModelProperty(value = "修改可见", notes = "")
	private Boolean editViewable;
	@ApiModelProperty(value = "可修改", notes = "")
	private Boolean editable;
	@ApiModelProperty(value = "列表可见", notes = "")
	private Boolean girdColumn;
	@ApiModelProperty(value = "可查询", notes = "")
	private Boolean searchCondition;
	@ApiModelProperty(value = "简单查询条件", notes = "")
	private Boolean simpleSearchCondition;
	@ApiModelProperty(value = "排序", notes = "")
	private Integer idx;
	@ApiModelProperty(value = "排序最大值")
	private Integer idxMax;
	@ApiModelProperty(value = "排序最小值")
	private Integer idxMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
