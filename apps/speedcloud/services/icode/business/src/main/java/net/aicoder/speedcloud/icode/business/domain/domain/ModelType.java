package net.aicoder.speedcloud.icode.business.domain.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 模型类型
 * @author icode
 */
@Entity()
@Table(name = "domain_model_type")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class ModelType extends BaseEntity<String>{

	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_IDX = "idx";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * code
    * 
    */
    @Column(name = "code", nullable = false, updatable = true)
	@Size(max = 255, message = "code超长，最多255个字符")
	private String code;

    /**
    * name
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "name超长，最多255个字符")
	private String name;

    /**
    * idx
    * 
    */
    @Column(name = "idx", nullable = true, updatable = true)
	private Integer idx;

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdx(){
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
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

