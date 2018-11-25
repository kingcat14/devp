package net.aicoder.speedcloud.icode.business.tplfile.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 公共代码模板集合的值对象
*/
@ApiModel(value = "展现公共代码模板集合的值对象")
@Setter @Getter
public class TplSetVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "集合代码", notes = "")
    private String code;


    @ApiModelProperty(value = "集合名称", notes = "")
    private String name;


    @ApiModelProperty(value = "parent_id", notes = "")
    private String parentId;


    @ApiModelProperty(value = "集合类型", notes = "")
    private String type;


    @ApiModelProperty(value = "集合描述", notes = "")
    private String description;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}