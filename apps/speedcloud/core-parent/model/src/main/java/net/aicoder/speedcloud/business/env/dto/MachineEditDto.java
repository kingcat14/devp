package net.aicoder.speedcloud.business.env.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 服务器
 * @author icode
 */
@ApiModel(value = "修改服务器使用的DTO")
public class MachineEditDto {


	/**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;


	/**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;


	/**IP地址*/
	@ApiModelProperty(value = "IP地址", required = false)
	private String ipAddress;


	/**端口*/
	@ApiModelProperty(value = "端口", required = false)
	private Integer port;



	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getIpAddress(){
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public Integer getPort(){
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
