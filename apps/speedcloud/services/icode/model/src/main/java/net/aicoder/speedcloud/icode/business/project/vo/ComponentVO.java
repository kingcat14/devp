package net.aicoder.speedcloud.icode.business.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplSetVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 系统组件的值对象
*/
@ApiModel(value = "展现系统组件的值对象")
public class ComponentVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**代码模板*/
    @ApiModelProperty(value = "代码模板", notes = "")
    private String tplSet;
    private TplSetVO tplSetVO;


    /**所属产品*/
    @ApiModelProperty(value = "所属产品")
    private String product;
    private ProductVO productVO;


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