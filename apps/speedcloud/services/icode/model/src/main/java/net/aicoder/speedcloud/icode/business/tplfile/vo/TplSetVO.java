package net.aicoder.speedcloud.icode.business.tplfile.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 公共代码模板集合的值对象
*/
@ApiModel(value = "展现公共代码模板集合的值对象")
public class TplSetVO {

    @ApiModelProperty(value = "记录id")
    private String id;



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}