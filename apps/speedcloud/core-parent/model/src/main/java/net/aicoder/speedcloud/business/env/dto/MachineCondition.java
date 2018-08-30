package net.aicoder.speedcloud.business.env.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询服务器使用的DTO")
public class MachineCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
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


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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

	public Integer getPortMin(){
		return portMin;
	}
	public void setPortMin(Integer portMin) {
		this.portMin = portMin;
	}

	public Integer getPortMax(){
		return portMax;
	}
	public void setPortMax(Integer portMax) {
		this.portMax = portMax;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
