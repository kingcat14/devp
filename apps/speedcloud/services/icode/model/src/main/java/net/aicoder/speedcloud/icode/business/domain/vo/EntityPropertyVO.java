package net.aicoder.speedcloud.icode.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 领域对象属性的值对象
*/
@ApiModel(value = "展现领域对象属性的值对象")
@Setter @Getter
public class EntityPropertyVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**所属领域对象*/
    @ApiModelProperty(value = "所属领域对象", notes = "")
    private String entity;
    private EntityVO entityVO;


    @ApiModelProperty(value = "属性代码", notes = "")
    private String code;


    @ApiModelProperty(value = "名称", notes = "中文名称")
    private String name;


    @ApiModelProperty(value = "类型", notes = "")
    private String type;


    @ApiModelProperty(value = "引用对象", notes = "")
    private String relatedEntityId;


    @ApiModelProperty(value = "引用对象属性代码", notes = "")
    private String relatedEntityPropertyCode;


    @ApiModelProperty(value = "排序", notes = "")
    private Integer idx;


    @ApiModelProperty(value = "备注", notes = "")
    private String memo;


    @ApiModelProperty(value = "是否主键", notes = "")
    private Boolean primaryKey;


    @ApiModelProperty(value = "是否查询条件", notes = "")
    private Boolean search;


    @ApiModelProperty(value = "可修改", notes = "")
    private Boolean editable;


    @ApiModelProperty(value = "可为空", notes = "")
    private Boolean nullable;


    @ApiModelProperty(value = "是否可见", notes = "")
    private Boolean visible;


    @ApiModelProperty(value = "持久化", notes = "")
    private Boolean persist;


    @ApiModelProperty(value = "可排序")
    private Boolean sortable;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}