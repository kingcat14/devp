package net.aicoder.speedcloud.business.env.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 环境设备关联
 * @author icode
 */
@ApiModel(value = "修改环境设备关联使用的DTO")
@Setter @Getter
public class EnvMachineEditDto {


	/**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;


	/**环境*/
	@ApiModelProperty(value = "环境", required = false)
	private String evn;


	/**机器*/
	@ApiModelProperty(value = "机器", required = false)
	private String machine;



	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}


	public String getEvn(){
        return evn;
    }
    public void setEvn(String evn) {
        this.evn = evn;
    }


	public String getMachine(){
        return machine;
    }
    public void setMachine(String machine) {
        this.machine = machine;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
