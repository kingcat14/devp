package net.aicoder.speedcloud.business.env.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 服务器的值对象
*/
@ApiModel(value = "展现服务器的值对象")
public class MachineVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**名称*/
    @ApiModelProperty(value = "名称")
    private String name;


    /**IP地址*/
    @ApiModelProperty(value = "IP地址")
    private String ipAddress;


    @ApiModelProperty(value = "端口")
    private Integer port;


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