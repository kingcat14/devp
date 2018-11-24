package net.aicoder.speedcloud.icode.business.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 领域的值对象
*/
@ApiModel(value = "展现领域的值对象")
public class DomainVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**领域名称*/
    @ApiModelProperty(value = "领域名称")
    private String name;


    /**领域代码*/
    @ApiModelProperty(value = "领域代码")
    private String code;


    /**父领域*/
    @ApiModelProperty(value = "父领域")
    private String parent;


    /**领域代码前缀*/
    @ApiModelProperty(value = "领域代码前缀", notes = "附领域为空时，必须填该字段")
    private String prefix;


    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCode(){
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getParent(){
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getPrefix(){
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


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