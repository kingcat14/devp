package net.aicoder.speedcloud.business.app.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 应用私密配置
 * @author icode
 */
@Entity
@Table(appliesTo = "security_config", comment = "[应用私密配置]")
//@DynamicUpdate
//@DynamicInsert
public class SecurityConfig extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_APP = "app";
	public static final String PROPERTY_ITEM_NAME = "itemName";
	public static final String PROPERTY_ITEM_VALUE = "itemValue";


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
    * 应用
    * 
    */
    @Column(name = "app", nullable = true, updatable = true)
	private String app;

    /**
    * 配置名
    * 
    */
    @Column(name = "item_name", nullable = true, updatable = true)
	@Size(max = 255, message = "配置名超长，最多255个字符")
	private String itemName;

    /**
    * 配置值
    * 
    */
    @Column(name = "item_value", nullable = true, updatable = true)
	@Size(max = 255, message = "配置值超长，最多255个字符")
	private String itemValue;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getApp(){
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}

	public String getItemName(){
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemValue(){
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
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

