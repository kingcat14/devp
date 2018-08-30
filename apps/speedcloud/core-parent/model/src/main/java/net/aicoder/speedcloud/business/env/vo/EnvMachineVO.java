package net.aicoder.speedcloud.business.env.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.env.vo.MachineVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 环境设备关联的值对象
*/
@ApiModel(value = "展现环境设备关联的值对象")
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


    public Long getEvn(){
        return evn;
    }
    public void setEvn(Long evn) {
        this.evn = evn;
    }
    public AppEnvConfigVO getEvnVO(){
        return evnVO;
    }
    public void setEvnVO(AppEnvConfigVO evnVO) {
        this.evnVO = evnVO;
    }

    public Long getMachine(){
        return machine;
    }
    public void setMachine(Long machine) {
        this.machine = machine;
    }
    public MachineVO getMachineVO(){
        return machineVO;
    }
    public void setMachineVO(MachineVO machineVO) {
        this.machineVO = machineVO;
    }


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}