package net.aicoder.speedcloud.icode.business.platformmapping.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 产品映射
 * @author icode
 */
@Entity()
@Table(name = "platformmapping_product_mapping")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ProductMapping extends BaseEntity<String>{

	public static final String PROPERTY_PRODUCT = "product";
	public static final String PROPERTY_PLATFORM_PRODUCT_NAME = "platformProductName";
	public static final String PROPERTY_PLATFORM_PRODUCT_ID = "platformProductId";


    @Id
    @Column(name = "id", length = 32)
    private String id;


    /**
    * 产品
    * 
    */
    @Column(name = "product", nullable = false, updatable = true)
	@Size(max = 255, message = "产品超长，最多255个字符")
	private String product;

    /**
    * 平台产品名称
    * 
    */
    @Column(name = "platform_product_name", nullable = false, updatable = true)
	@Size(max = 255, message = "平台产品名称超长，最多255个字符")
	private String platformProductName;

    /**
    * 平台产品ID
    * 
    */
    @Column(name = "platform_product_id", nullable = false, updatable = true)
	@Size(max = 255, message = "平台产品ID超长，最多255个字符")
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

