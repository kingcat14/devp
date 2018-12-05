package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用开发配置的值对象
*/
@ApiModel(value = "展现应用开发配置的值对象")
@Setter @Getter
public class AppDevelopConfigVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**应用*/
    @ApiModelProperty(value = "应用")
    private String app;
    private AppBaseInfoVO appVO;


    @ApiModelProperty(value = "开发环境DB")
    private String developDatabase;


    @ApiModelProperty(value = "开发环境域名")
    private String developDomainName;


    @ApiModelProperty(value = "测试环境DB")
    private String testDatabase;


    @ApiModelProperty(value = "测试环境域名")
    private String testDomainName;


    @ApiModelProperty(value = "生产环境DB")
    private String productionDatabase;


    @ApiModelProperty(value = "生产环境域名")
    private String productionDomainName;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}