package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用私密配置的值对象
*/
@ApiModel(value = "展现应用私密配置的值对象")
@Setter @Getter
public class SecurityConfigVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**应用*/
    @ApiModelProperty(value = "应用")
    private String app;
    private AppBaseInfoVO appVO;


    @ApiModelProperty(value = "配置名")
    private String itemName;


    @ApiModelProperty(value = "配置值")
    private String itemValue;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}