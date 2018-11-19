package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用开发配置的值对象
*/
@ApiModel(value = "展现应用开发配置的值对象")
public class AppDevelopConfigVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**应用*/
    @ApiModelProperty(value = "应用")
    private Long app;
    private AppBaseInfoVO appVO;


    /**代码*/
    @ApiModelProperty(value = "代码", notes = "")
    private Long code;
    private CodeRepositoryVO codeVO;


    /**测试环境DB*/
    @ApiModelProperty(value = "测试环境DB")
    private String testDatabase;


    /**测试环境域名*/
    @ApiModelProperty(value = "测试环境域名")
    private String testDomainName;


    /**生产环境DB*/
    @ApiModelProperty(value = "生产环境DB")
    private String productionDatabase;


    /**生产环境域名*/
    @ApiModelProperty(value = "生产环境域名")
    private String productionDomainName;


    public Long getApp(){
        return app;
    }
    public void setApp(Long app) {
        this.app = app;
    }
    public AppBaseInfoVO getAppVO(){
        return appVO;
    }
    public void setAppVO(AppBaseInfoVO appVO) {
        this.appVO = appVO;
    }

    public Long getCode(){
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    public CodeRepositoryVO getCodeVO(){
        return codeVO;
    }
    public void setCodeVO(CodeRepositoryVO codeVO) {
        this.codeVO = codeVO;
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