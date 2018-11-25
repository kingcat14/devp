package net.aicoder.speedcloud.icode.business.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 产品的值对象
*/
@ApiModel(value = "展现产品的值对象")
@Setter @Getter
public class ProductVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "名称", notes = "")
    private String productName;


    @ApiModelProperty(value = "代码", notes = "")
    private String productCode;


    /**描述*/
    @ApiModelProperty(value = "描述", notes = "")
    private String description;


    @ApiModelProperty(value = "已失效")
    private Boolean disabled;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}