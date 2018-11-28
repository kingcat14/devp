package net.aicoder.speedcloud.icode.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 组件本地路径
 * @author icode
 */
@ApiModel(value = "修改组件本地路径使用的DTO")
@Setter @Getter
public class ComponentLocalLocationEditDto {


	/**用户id*/
	@ApiModelProperty(value = "用户id", required = false)
	private Long accountId;


	/**组件*/
	@ApiModelProperty(value = "组件", required = false)
	private String component;


	/**本地路径*/
	@ApiModelProperty(value = "本地路径", required = false)
	private String location;



	public Long getAccountId(){
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	public String getComponent(){
        return component;
    }
    public void setComponent(String component) {
        this.component = component;
    }


	public String getLocation(){
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
