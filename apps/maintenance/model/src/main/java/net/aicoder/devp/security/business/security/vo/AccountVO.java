package net.aicoder.devp.security.business.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 账号的值对象
*/
@ApiModel(value = "展现账号的值对象")
public class AccountVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "昵称")
    /**
    * 昵称
    * 
    */
    private String nickName;

    @ApiModelProperty(value = "姓名")
    /**
    * 姓名
    * 
    */
    private String name;

    @ApiModelProperty(value = "账号")
    /**
    * 账号
    * 
    */
    private String accountName;

    @ApiModelProperty(value = "手机号")
    /**
    * 手机号
    * 
    */
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    /**
    * 邮箱
    * 
    */
    private String email;

    @ApiModelProperty(value = "已启用")
    /**
    * 已启用
    * 
    */
    private String enable;


    public String getNickName(){
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAccountName(){
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
    public String getEnable(){
        return enable;
    }
    public void setEnable(String enable) {
        this.enable = enable;
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