package net.aicoder.speedcloud.icode.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 领域对象展现属性的值对象
*/
@ApiModel(value = "展现领域对象展现属性的值对象")
@Setter @Getter
public class EntityViewPropertyVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**所属领域对象*/
    @ApiModelProperty(value = "所属领域对象", notes = "")
    private String entity;
    private EntityVO entityVO;


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




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}