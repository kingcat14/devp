package net.aicoder.devp.security.business.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 账号密码的值对象
*/
@ApiModel(value = "展现账号密码的值对象")
public class AccountPasswordVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "账号Id")
    /**
    * 账号Id
    * 
    */
    private String accountId;

    @ApiModelProperty(value = "账号账号")
    /**
    * 账号账号
    * 
    */
    private String accountName;

    @ApiModelProperty(value = "密码")
    /**
    * 密码
    * 
    */
    private String password;

    @ApiModelProperty(value = "错误次数")
    /**
    * 错误次数
    * 
    */
    private Integer wrongCount;


    public String getAccountId(){
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAccountName(){
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getWrongCount(){
        return wrongCount;
    }
    public void setWrongCount(Integer wrongCount) {
        this.wrongCount = wrongCount;
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