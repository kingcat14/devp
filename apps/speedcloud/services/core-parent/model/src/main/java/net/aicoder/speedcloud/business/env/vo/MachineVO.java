package net.aicoder.speedcloud.business.env.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 服务器的值对象
*/
@ApiModel(value = "展现服务器的值对象")
@Setter @Getter
public class MachineVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "IP地址")
    private String ipAddress;


    @ApiModelProperty(value = "端口")
    private Integer port;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}