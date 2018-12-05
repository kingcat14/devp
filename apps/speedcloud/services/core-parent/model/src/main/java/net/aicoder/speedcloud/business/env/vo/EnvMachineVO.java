package net.aicoder.speedcloud.business.env.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 环境设备关联的值对象
*/
@ApiModel(value = "展现环境设备关联的值对象")
@Setter @Getter
public class EnvMachineVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**环境*/
    @ApiModelProperty(value = "环境")
    private Long evn;
    private AppEnvConfigVO evnVO;


    /**机器*/
    @ApiModelProperty(value = "机器")
    private Long machine;
    private MachineVO machineVO;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}