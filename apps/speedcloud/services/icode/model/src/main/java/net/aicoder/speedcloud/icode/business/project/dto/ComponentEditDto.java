package net.aicoder.speedcloud.icode.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 系统组件
 * @author icode
 */
@ApiModel(value = "修改系统组件使用的DTO")
public class ComponentEditDto {


	/**组件名称*/
	@ApiModelProperty(value = "组件名称", required = false, notes = "")
	private String name;


	/**组件代码*/
	@ApiModelProperty(value = "组件代码", required = false, notes = "")
	private String code;


	/**基础包*/
	@ApiModelProperty(value = "基础包", required = false, notes = "")
	private String basePackage;


	/**描述*/
	@ApiModelProperty(value = "描述", required = false, notes = "")
	private String description;


	/**代码模板*/
	@ApiModelProperty(value = "代码模板", required = false, notes = "")
	private String tplSet;


	/**组件编号*/
	@ApiModelProperty(value = "组件编号", required = false, notes = "")
	private Integer number;


	/**分组代码*/
	@ApiModelProperty(value = "分组代码", required = false, notes = "")
	private String groupCode;


	/**所属产品*/
	@ApiModelProperty(value = "所属产品", required = false)
	private String product;



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


	public String getBasePackage(){
		return basePackage;
	}
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getTplSet(){
        return tplSet;
    }
    public void setTplSet(String tplSet) {
        this.tplSet = tplSet;
    }


	public Integer getNumber(){
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}


	public String getGroupCode(){
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}


	public String getProduct(){
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
