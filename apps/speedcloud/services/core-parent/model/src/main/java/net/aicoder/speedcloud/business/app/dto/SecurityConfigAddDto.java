package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用私密配置
 * @author icode
 */
@ApiModel(value = "新增应用私密配置使用的DTO")
public class SecurityConfigAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**应用*/
	@ApiModelProperty(value = "应用", required = false)
	private Long app;

    /**配置名*/
	@ApiModelProperty(value = "配置名", required = false)
	private String itemName;

    /**配置值*/
	@ApiModelProperty(value = "配置值", required = false)
	private String itemValue;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getApp(){
        return app;
    }
    public void setApp(Long app) {
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
