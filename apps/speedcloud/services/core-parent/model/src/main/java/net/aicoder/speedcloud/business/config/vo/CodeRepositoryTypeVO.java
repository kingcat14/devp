package net.aicoder.speedcloud.business.config.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 代码库类型的值对象
*/
@ApiModel(value = "展现代码库类型的值对象")
@Setter @Getter
public class CodeRepositoryTypeVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "代码")
    private String code;


    @ApiModelProperty(value = "名称")
    private String name;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}