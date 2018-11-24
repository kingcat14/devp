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
 * 组件本地路径
 * @author icode
 */
@Entity()
@Table(name = "project_component_local_location")
//@DynamicUpdate
//@DynamicInsert
public class ComponentLocalLocation extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_ACCOUNT_ID = "accountId";
	public static final String PROPERTY_COMPONENT = "component";
	public static final String PROPERTY_LOCATION = "location";


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
    * 用户id
    * 
    */
    @Column(name = "account_id", nullable = false, updatable = false)
	private Long accountId;

    /**
    * 组件
    * 
    */
    @Column(name = "component", nullable = true, updatable = true)
	@Size(max = 255, message = "组件超长，最多255个字符")
	private String component;

    /**
    * 本地路径
    * 
    */
    @Column(name = "location", nullable = true, updatable = true)
	@Size(max = 255, message = "本地路径超长，最多255个字符")
	private String location;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getAccountId(){
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getComponent(){
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}

	public String getLocation(){
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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

