package net.aicoder.speedcloud.business.app.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 应用开发配置
 * @author icode
 */
@Entity
@Table(appliesTo = "app_develop_config", comment = "[应用开发配置]")
//@DynamicUpdate
//@DynamicInsert
public class AppDevelopConfig extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_APP = "app";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_TEST_DATABASE = "testDatabase";
	public static final String PROPERTY_TEST_DOMAIN_NAME = "testDomainName";
	public static final String PROPERTY_PRODUCTION_DATABASE = "productionDatabase";
	public static final String PROPERTY_PRODUCTION_DOMAIN_NAME = "productionDomainName";


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
    * 应用
    * 
    */
    @Column(name = "app", nullable = true, updatable = true)
	private Long app;

    /**
    * 代码
    * 
    */
    @Column(name = "code", nullable = true, updatable = true)
	private Long code;

    /**
    * 测试环境DB
    * 
    */
    @Column(name = "test_database", nullable = true, updatable = true)
	@Size(max = 255, message = "测试环境DB超长，最多255个字符")
	private String testDatabase;

    /**
    * 测试环境域名
    * 
    */
    @Column(name = "test_domain_name", nullable = true, updatable = true)
	@Size(max = 255, message = "测试环境域名超长，最多255个字符")
	private String testDomainName;

    /**
    * 生产环境DB
    * 
    */
    @Column(name = "production_database", nullable = true, updatable = true)
	@Size(max = 255, message = "生产环境DB超长，最多255个字符")
	private String productionDatabase;

    /**
    * 生产环境域名
    * 
    */
    @Column(name = "production_domain_name", nullable = true, updatable = true)
	@Size(max = 255, message = "生产环境域名超长，最多255个字符")
	private String productionDomainName;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getApp(){
		return app;
	}
	public void setApp(Long app) {
		this.app = app;
	}

	public Long getCode(){
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}

	public String getTestDatabase(){
		return testDatabase;
	}
	public void setTestDatabase(String testDatabase) {
		this.testDatabase = testDatabase;
	}

	public String getTestDomainName(){
		return testDomainName;
	}
	public void setTestDomainName(String testDomainName) {
		this.testDomainName = testDomainName;
	}

	public String getProductionDatabase(){
		return productionDatabase;
	}
	public void setProductionDatabase(String productionDatabase) {
		this.productionDatabase = productionDatabase;
	}

	public String getProductionDomainName(){
		return productionDomainName;
	}
	public void setProductionDomainName(String productionDomainName) {
		this.productionDomainName = productionDomainName;
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

