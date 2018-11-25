package net.aicoder.speedcloud.icode.business.project.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 产品
 * @author icode
 */
@Entity()
@Table(name = "project_product")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class Product extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_PRODUCT_NAME = "productName";
	public static final String PROPERTY_PRODUCT_CODE = "productCode";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_DISABLED = "disabled";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 名称
    * 
    */
    @Column(name = "product_name", nullable = false, updatable = true)
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String productName;

    /**
    * 代码
    * 
    */
    @Column(name = "product_code", nullable = false, updatable = true)
	@Size(max = 255, message = "代码超长，最多255个字符")
	private String productCode;

    /**
    * 描述
    * 
    */
    @Column(name = "description", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String description;

    /**
    * 已失效
    * 
    */
    @Column(name = "disabled", nullable = false, updatable = true)
	private Boolean disabled;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode(){
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDisabled(){
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
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

