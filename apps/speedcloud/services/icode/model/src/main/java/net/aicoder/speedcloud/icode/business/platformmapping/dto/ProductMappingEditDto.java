package net.aicoder.speedcloud.icode.business.platformmapping.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 产品映射
 * @author icode
 */
@ApiModel(value = "修改产品映射使用的DTO")
@Setter @Getter
public class ProductMappingEditDto {


	/**产品*/
	@ApiModelProperty(value = "产品", required = false)
	private String product;


	/**平台产品名称*/
	@ApiModelProperty(value = "平台产品名称", required = false)
	private String platformProductName;


	/**平台产品ID*/
	@ApiModelProperty(value = "平台产品ID", required = false)
	private String platformProductId;



	public String getProduct(){
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }


	public String getPlatformProductName(){
		return platformProductName;
	}
	public void setPlatformProductName(String platformProductName) {
		this.platformProductName = platformProductName;
	}


	public String getPlatformProductId(){
		return platformProductId;
	}
	public void setPlatformProductId(String platformProductId) {
		this.platformProductId = platformProductId;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
