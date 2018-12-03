package com.yunkang.saas.platform.authentication.business.platform.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 账号密码的值对象
*/
@ApiModel(value = "展现账号密码的值对象")
@Setter @Getter
public class AccountPasswordVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**账号*/
    @ApiModelProperty(value = "账号")
    private String account;
    private AccountVO accountVO;


    @ApiModelProperty(value = "姓名")
    private String accountName;


    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "错误次数")
    private Integer wrongCount;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}