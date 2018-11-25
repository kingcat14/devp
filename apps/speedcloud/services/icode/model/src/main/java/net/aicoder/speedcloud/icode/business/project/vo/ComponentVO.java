package net.aicoder.speedcloud.icode.business.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplSetVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 组件的值对象
*/
@ApiModel(value = "展现组件的值对象")
@Setter @Getter
public class ComponentVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**组件名称*/
    @ApiModelProperty(value = "组件名称", notes = "")
    private String name;


    /**组件代码*/
    @ApiModelProperty(value = "组件代码", notes = "")
    private String code;


    /**基础包*/
    @ApiModelProperty(value = "基础包", notes = "")
    private String basePackage;


    /**描述*/
    @ApiModelProperty(value = "描述", notes = "")
    private String description;


    /**代码模板*/
    @ApiModelProperty(value = "代码模板", notes = "")
    private String tplSet;
    private TplSetVO tplSetVO;


    @ApiModelProperty(value = "组件编号", notes = "")
    private Integer number;


    /**分组代码*/
    @ApiModelProperty(value = "分组代码", notes = "")
    private String groupCode;


    /**所属产品*/
    @ApiModelProperty(value = "所属产品")
    private String product;
    private ProductVO productVO;


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
    public TplSetVO getTplSetVO(){
        return tplSetVO;
    }
    public void setTplSetVO(TplSetVO tplSetVO) {
        this.tplSetVO = tplSetVO;
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
    public ProductVO getProductVO(){
        return productVO;
    }
    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
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