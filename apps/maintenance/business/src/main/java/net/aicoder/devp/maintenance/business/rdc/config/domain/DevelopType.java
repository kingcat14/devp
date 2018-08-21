package net.aicoder.devp.maintenance.business.rdc.config.domain;

import com.yunkang.saas.common.framework.eo.GenericBaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;


/**
 * 开发模式
 * @author icode
 */
@Entity
@Table(appliesTo = "develop_type", comment = "[开发模式]")
//@DynamicUpdate
//@DynamicInsert
public class DevelopType extends GenericBaseEntity<String>{

	public static final String PROPERTY_NAME = "name";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 名称
    * 
    */
    @Column(name = "name")
	@Size(max = 255, message = "名称超长，最多255个字符")
	private String name;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

