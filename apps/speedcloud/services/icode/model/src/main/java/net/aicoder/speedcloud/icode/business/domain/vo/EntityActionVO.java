package net.aicoder.speedcloud.icode.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 领域对象行为的值对象
*/
@ApiModel(value = "展现领域对象行为的值对象")
@Setter @Getter
public class EntityActionVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "代码")
    private String code;


    @ApiModelProperty(value = "行为名称")
    private String name;


    @ApiModelProperty(value = "备注", notes = "行为的概括性描述")
    private String memo;


    /**行为描述*/
    @ApiModelProperty(value = "行为描述", notes = "行为的操作步骤")
    private String description;


    @ApiModelProperty(value = "行为输入对象")
    private String request;
    private EntityActionParameterVO requestParameter;


    @ApiModelProperty(value = "行为响应对象")
    private String response;
    private EntityActionParameterVO responseParameter;

    /**所属领域对象*/
    @ApiModelProperty(value = "所属领域对象")
    private String entity;
    private EntityVO entityVO;


    @ApiModelProperty(value = "行为类型", notes = "备用字段,将来用于标识 增、删、改、查、业务 等行为")
    private String type;


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}