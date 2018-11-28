package net.aicoder.speedcloud.icode.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 组件
 * @author icode
 */
@ApiModel(value = "修改组件使用的DTO")
@Setter @Getter
public class ComponentEditDto {


	/**所属产品*/
	@ApiModelProperty(value = "所属产品", required = false)
	private String product;


	/**组件编号*/
	@ApiModelProperty(value = "组件编号", required = false, notes = "")
	private Integer number;


	/**组件名称*/
	@ApiModelProperty(value = "组件名称", required = false, notes = "")
	private String name;


	/**组件代码*/
	@ApiModelProperty(value = "组件代码", required = false, notes = "")
	private String code;


	/**基础包*/
	@ApiModelProperty(value = "基础包", required = false, notes = "")
	private String basePackage;


	/**代码模板*/
	@ApiModelProperty(value = "代码模板", required = false, notes = "")
	private String tplSet;


	/**描述*/
	@ApiModelProperty(value = "描述", required = false, notes = "")
	private String description;


	/**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "IOS、ANDROID、WEB、应用、服务、公共组件")
	private String type;


	/**可运行组件*/
	@ApiModelProperty(value = "可运行组件", required = false)
	private Boolean runnable;



	public String getProduct(){
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }


	public Integer getNumber(){
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}


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


	public String getTplSet(){
        return tplSet;
    }
    public void setTplSet(String tplSet) {
        this.tplSet = tplSet;
    }


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


	public Boolean getRunnable(){
		return runnable;
	}
	public void setRunnable(Boolean runnable) {
		this.runnable = runnable;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
