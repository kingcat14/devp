package net.aicoder.speedcloud.business.env.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询服务器使用的DTO")
@Getter @Setter
public class MachineCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "IP地址")
	private String ipAddress;
	@ApiModelProperty(value = "端口")
	private Integer port;
	@ApiModelProperty(value = "端口最大值")
	private Integer portMax;
	@ApiModelProperty(value = "端口最小值")
	private Integer portMin;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
