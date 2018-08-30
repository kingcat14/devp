package net.aicoder.speedcloud.business.env.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 环境设备关联
 * @author icode
 */
@ApiModel(value = "新增环境设备关联使用的DTO")
public class EnvMachineAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**环境*/
	@ApiModelProperty(value = "环境", required = false)
	private Long evn;

    /**机器*/
	@ApiModelProperty(value = "机器", required = false)
	private Long machine;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getEvn(){
        return evn;
    }
    public void setEvn(Long evn) {
        this.evn = evn;
    }

	public Long getMachine(){
        return machine;
    }
    public void setMachine(Long machine) {
        this.machine = machine;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
