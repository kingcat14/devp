package net.aicoder.speedcloud.business.app.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询应用开发配置使用的DTO")
public class AppDevelopConfigCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
    @ApiModelProperty(value = "应用")
    private Long app;
    @ApiModelProperty(value = "代码", notes = "")
    private Long code;
	@ApiModelProperty(value = "测试环境DB")
	private String testDatabase;
	@ApiModelProperty(value = "测试环境域名")
	private String testDomainName;
	@ApiModelProperty(value = "生产环境DB")
	private String productionDatabase;
	@ApiModelProperty(value = "生产环境域名")
	private String productionDomainName;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
