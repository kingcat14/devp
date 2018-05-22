package net.aicoder.devp.maintenanceui.business.security.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;
import com.yunkang.saas.common.framework.eo.BaseEntity;



/**
 * 角色
 * @author icode
 */
@Entity
@Table
public class Role extends BaseEntity{

	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_DESCRIPTION = "description";


    @Id
    @Column(name = "rid")
    private Long id;


    /**
    * 角色名称
    * 
    */
    @Column(name = "name")
	@NotNull(message = "角色名称不能为空")
	@Size(max = 255, message = "角色名称超长，最多255个字符")
	private String name;

    /**
    * 角色描述
    * 
    */
    @Column(name = "description")
	@Size(max = 255, message = "角色描述超长，最多255个字符")
	private String description;

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

