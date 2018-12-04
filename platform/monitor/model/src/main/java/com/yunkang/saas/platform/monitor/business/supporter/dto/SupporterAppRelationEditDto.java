package com.yunkang.saas.platform.monitor.business.supporter.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 支持应用
 * @author icode
 */
@ApiModel(value = "修改支持应用使用的DTO")
@Setter @Getter
public class SupporterAppRelationEditDto {


	/**运维人员*/
	@ApiModelProperty(value = "运维人员", required = false)
	private String supporter;


	/**支持程序*/
	@ApiModelProperty(value = "支持程序", required = false)
	private String application;


	/**接收通知方式*/
	@ApiModelProperty(value = "接收通知方式", required = false, notes = "逗号分隔的告警方式")
	private String notificationType;



	public String getSupporter(){
        return supporter;
    }
    public void setSupporter(String supporter) {
        this.supporter = supporter;
    }


	public String getApplication(){
        return application;
    }
    public void setApplication(String application) {
        this.application = application;
    }


	public String getNotificationType(){
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
