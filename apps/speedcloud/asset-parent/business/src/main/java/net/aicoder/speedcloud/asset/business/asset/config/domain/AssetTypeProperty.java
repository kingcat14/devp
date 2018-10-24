package net.aicoder.speedcloud.asset.business.asset.config.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 资产分类属性
 * @author icode
 */
@Entity()
@Table(name = "asset_asset_type_property")
//@DynamicUpdate
//@DynamicInsert
public class AssetTypeProperty extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ASSET_TYPE = "assetType";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_REQUIRED = "required";
	public static final String PROPERTY_OPTION_VALUES = "optionValues";
	public static final String PROPERTY_SEQ = "seq";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 资产分类
    * 
    */
    @Column(name = "asset_type", nullable = false, updatable = true)
	private Long assetType;

    /**
    * 属性名称
    * 
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "属性名称超长，最多255个字符")
	private String name;

    /**
    * 属性类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "属性类型超长，最多255个字符")
	private String type;

    /**
    * 属性代码
    * 
    */
    @Column(name = "code", nullable = false, updatable = false)
	@Size(max = 255, message = "属性代码超长，最多255个字符")
	private String code;

    /**
    * 必填
    * 
    */
    @Column(name = "required", nullable = true, updatable = true)
	private Boolean required;

    /**
    * 备选值
    * 分号分隔的值例如: a:b
    */
    @Column(name = "option_values", nullable = true, updatable = true)
	@Size(max = 255, message = "备选值超长，最多255个字符")
	private String optionValues;

    /**
    * 顺序
    * 
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getAssetType(){
		return assetType;
	}
	public void setAssetType(Long assetType) {
		this.assetType = assetType;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getRequired(){
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getOptionValues(){
		return optionValues;
	}
	public void setOptionValues(String optionValues) {
		this.optionValues = optionValues;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

