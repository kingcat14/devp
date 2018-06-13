package net.aicoder.devp.deploy.business.ops.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 系统元素扩充信息的值对象
*/
@ApiModel(value = "展现系统元素扩充信息的值对象")
public class DevpOpsElementInfoVO {

    @ApiModelProperty(value = "记录id")
   private Long rid;



	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}