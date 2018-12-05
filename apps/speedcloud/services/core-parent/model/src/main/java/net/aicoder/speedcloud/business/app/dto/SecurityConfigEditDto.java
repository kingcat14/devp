package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用私密配置
 * @author icode
 */
@ApiModel(value = "修改应用私密配置使用的DTO")
@Setter @Getter
public class SecurityConfigEditDto {


	/**应用*/
	@ApiModelProperty(value = "应用", required = false)
	private String app;


	/**配置名*/
	@ApiModelProperty(value = "配置名", required = false)
	private String itemName;


	/**配置值*/
	@ApiModelProperty(value = "配置值", required = false)
	private String itemValue;



	public String getApp(){
        return app;
    }
    public void setApp(String app) {
        this.app = app;
    }


	public String getItemName(){
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemValue(){
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
