package net.aicoder.devp.business.deploy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 资源实例部署主机节点的值对象
*/
@ApiModel(value = "展现资源实例部署主机节点的值对象")
public class DevpSysDpyResInstHostVO {

    @ApiModelProperty(value = "记录id")
   private Long id;



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