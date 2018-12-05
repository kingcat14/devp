package net.aicoder.speedcloud.icode.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 领域的值对象
*/
@ApiModel(value = "展现领域的值对象")
@Setter @Getter
public class DomainVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "领域名称")
    private String name;


    @ApiModelProperty(value = "领域代码")
    private String code;

    @ApiModelProperty(value = "顶级领域的代码")
    private String topCode;

    @ApiModelProperty(value = "包含父级代码的代码路径")
    private String codePath;

    @ApiModelProperty(value = "父领域")
    private String parent;


    @ApiModelProperty(value = "领域代码前缀", notes = "附领域为空时，必须填该字段")
    private String prefix;


    /**描述*/
    @ApiModelProperty(value = "描述")
    private String description;


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}