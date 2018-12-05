package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用开发配置
 * @author icode
 */
@ApiModel(value = "修改应用开发配置使用的DTO")
@Setter @Getter
public class AppDevelopConfigEditDto {


	/**应用*/
	@ApiModelProperty(value = "应用", required = false)
	private String app;


	/**开发环境DB*/
	@ApiModelProperty(value = "开发环境DB", required = false)
	private String developDatabase;


	/**开发环境域名*/
	@ApiModelProperty(value = "开发环境域名", required = false)
	private String developDomainName;


	/**测试环境DB*/
	@ApiModelProperty(value = "测试环境DB", required = false)
	private String testDatabase;


	/**测试环境域名*/
	@ApiModelProperty(value = "测试环境域名", required = false)
	private String testDomainName;


	/**生产环境DB*/
	@ApiModelProperty(value = "生产环境DB", required = false)
	private String productionDatabase;


	/**生产环境域名*/
	@ApiModelProperty(value = "生产环境域名", required = false)
	private String productionDomainName;



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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
