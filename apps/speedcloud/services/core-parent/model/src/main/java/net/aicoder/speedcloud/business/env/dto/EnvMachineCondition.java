package net.aicoder.speedcloud.business.env.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询环境设备关联使用的DTO")
public class EnvMachineCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
    @ApiModelProperty(value = "环境")
    private Long evn;
    @ApiModelProperty(value = "机器")
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
