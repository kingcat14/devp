package net.aicoder.speedcloud.icode.business.platformmapping.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.icode.business.project.vo.ProductVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 产品映射的值对象
*/
@ApiModel(value = "展现产品映射的值对象")
@Setter @Getter
public class ProductMappingVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**产品*/
    @ApiModelProperty(value = "产品")
    private String product;
    private ProductVO productVO;


    @ApiModelProperty(value = "平台产品名称")
    private String platformProductName;


    @ApiModelProperty(value = "平台产品ID")
    private String platformProductId;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}