package net.aicoder.speedcloud.icode.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
* 领域对象行为参数的值对象
*/
@ApiModel(value = "展现领域对象行为参数的值对象")
@Setter @Getter
public class EntityActionParameterVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "对象代码", notes = "")
    private String code;


    @ApiModelProperty(value = "对象名称", notes = "")
    private String name;


    @ApiModelProperty(value = "备注", notes = "")
    private String memo;


    /**描述*/
    @ApiModelProperty(value = "描述", notes = "")
    private String description;

    /**所属领域*/
    @ApiModelProperty(value = "所属领域, 只有在获取明细信息时才会填充这个值")
    private String domain;
    private DomainVO domainVO;

    /**领域对象行为*/
    @ApiModelProperty(value = "领域对象行为, 只有在获取明细信息时才会填充这个值")
    private String entityAction;
    private EntityActionVO entityActionVO;

    /**参数包含的属性*/
    @ApiModelProperty(value = "领域对象行为, 只有在获取明细信息时才会填充这个值")
    private List<EntityActionParameterPropertyVO> propertyList;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}