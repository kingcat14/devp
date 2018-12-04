package com.yunkang.saas.platform.monitor.business.supporter.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 运维人员
 * @author icode
 */
@Entity()
@Table(name = "supporter_supporter")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class Supporter extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_MOBILE = "mobile";
	public static final String PROPERTY_EMAIL = "email";


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
    * 姓名
    * 
    */
    @Column(name = "name", nullable = false, updatable = true)
	@Size(max = 255, message = "姓名超长，最多255个字符")
	private String name;

    /**
    * 手机号码
    * 
    */
    @Column(name = "mobile", nullable = false, updatable = true)
	@Size(max = 255, message = "手机号码超长，最多255个字符")
	private String mobile;

    /**
    * 邮箱
    * 
    */
    @Column(name = "email", nullable = false, updatable = true)
	@Size(max = 255, message = "邮箱超长，最多255个字符")
	private String email;

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

	public String getMobile(){
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail(){
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

