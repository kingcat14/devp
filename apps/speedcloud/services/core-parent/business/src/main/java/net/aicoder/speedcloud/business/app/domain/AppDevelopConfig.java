package net.aicoder.speedcloud.business.app.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



/**
 * 应用开发配置
 * @author icode
 */
@Entity()
@Table(name = "app_app_develop_config")
//@DynamicUpdate
//@DynamicInsert
//@Where(clause="delete=0")
public class AppDevelopConfig extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_APP = "app";
	public static final String PROPERTY_DEVELOP_DATABASE = "developDatabase";
	public static final String PROPERTY_DEVELOP_DOMAIN_NAME = "developDomainName";
	public static final String PROPERTY_TEST_DATABASE = "testDatabase";
	public static final String PROPERTY_TEST_DOMAIN_NAME = "testDomainName";
	public static final String PROPERTY_PRODUCTION_DATABASE = "productionDatabase";
	public static final String PROPERTY_PRODUCTION_DOMAIN_NAME = "productionDomainName";


    @Id
    @Column(name = "id", length = 32)
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
	@Size(max = 255, message = "应用超长，最多255个字符")
	private String app;

    /**
    * 开发环境DB
    * 
    */
    @Column(name = "develop_database", nullable = true, updatable = true)
	@Size(max = 255, message = "开发环境DB超长，最多255个字符")
	private String developDatabase;

    /**
    * 开发环境域名
    * 
    */
    @Column(name = "develop_domain_name", nullable = true, updatable = true)
	@Size(max = 255, message = "开发环境域名超长，最多255个字符")
	private String developDomainName;

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

	public String getApp(){
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}

	public String getDevelopDatabase(){
		return developDatabase;
	}
	public void setDevelopDatabase(String developDatabase) {
		this.developDatabase = developDatabase;
	}

	public String getDevelopDomainName(){
		return developDomainName;
	}
	public void setDevelopDomainName(String developDomainName) {
		this.developDomainName = developDomainName;
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

