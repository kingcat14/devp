package net.aicoder.speedcloud.icode.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 领域对象行为参数属性的值对象
*/
@ApiModel(value = "展现领域对象行为参数属性的值对象")
@Setter @Getter
public class EntityActionParameterPropertyVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "属性代码", notes = "")
    private String code;


    @ApiModelProperty(value = "属性名称", notes = "中文名称")
    private String name;


    @ApiModelProperty(value = "类型", notes = "")
    private String type;


    @ApiModelProperty(value = "外覆类型", notes = "List")
    private String wrapperType;


    @ApiModelProperty(value = "排序", notes = "")
    private Integer idx;


    @ApiModelProperty(value = "备注", notes = "")
    private String memo;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}