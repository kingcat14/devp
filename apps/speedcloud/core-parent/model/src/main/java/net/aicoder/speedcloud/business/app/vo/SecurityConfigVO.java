package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用私密配置的值对象
*/
@ApiModel(value = "展现应用私密配置的值对象")
public class SecurityConfigVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**应用*/
    @ApiModelProperty(value = "应用")
    private Long app;
    private AppBaseInfoVO appVO;


    /**配置名*/
    @ApiModelProperty(value = "配置名")
    private String itemName;


    /**配置值*/
    @ApiModelProperty(value = "配置值")
    private String itemValue;


    public Long getApp(){
        return app;
    }
    public void setApp(Long app) {
        this.app = app;
    }
    public AppBaseInfoVO getAppVO(){
        return appVO;
    }
    public void setAppVO(AppBaseInfoVO appVO) {
        this.appVO = appVO;
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