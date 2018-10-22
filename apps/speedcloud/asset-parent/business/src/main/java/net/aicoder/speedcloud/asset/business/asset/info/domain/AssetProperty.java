package net.aicoder.speedcloud.asset.business.asset.info.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 资产属性
 * @author icode
 */
@Entity()
@Table(name = "asset_asset_property")
//@DynamicUpdate
//@DynamicInsert
public class AssetProperty extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_VALUE = "value";
	public static final String PROPERTY_ASSET = "asset";


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
    * 属性名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "属性名称超长，最多255个字符")
	private String name;

    /**
    * 属性值
    * 
    */
    @Column(name = "value", nullable = true, updatable = true)
	@Size(max = 255, message = "属性值超长，最多255个字符")
	private String value;

    /**
    * 所属资产
    * 
    */
    @Column(name = "asset", nullable = true, updatable = true)
	private Long asset;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getValue(){
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Long getAsset(){
		return asset;
	}
	public void setAsset(Long asset) {
		this.asset = asset;
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

