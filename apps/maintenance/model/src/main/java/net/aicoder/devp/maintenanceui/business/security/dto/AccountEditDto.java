package net.aicoder.devp.maintenanceui.business.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 账号
 * @author icode
 */
@ApiModel(value = "修改账号使用的DTO")
public class AccountEditDto {

    /**
	 * 昵称
	 * 
     */
	@NotNull(message = "昵称不能为空")
	@ApiModelProperty(value = "昵称", required = true)
	@Size(max = 255, message = "昵称超长，最多255个字符")
	private String nickName;

    /**
	 * 姓名
	 * 
     */
	@NotNull(message = "姓名不能为空")
	@ApiModelProperty(value = "姓名", required = true)
	@Size(max = 255, message = "姓名超长，最多255个字符")
	private String name;

    /**
	 * 账号
	 * 
     */
	@ApiModelProperty(value = "账号", required = false)
	@Size(max = 255, message = "账号超长，最多255个字符")
	private String accountName;

    /**
	 * 手机号
	 * 
     */
	@ApiModelProperty(value = "手机号", required = false)
	@Size(max = 255, message = "手机号超长，最多255个字符")
	private String mobile;

    /**
	 * 邮箱
	 * 
     */
	@ApiModelProperty(value = "邮箱", required = false)
	@Size(max = 255, message = "邮箱超长，最多255个字符")
	private String email;

    /**
	 * 已启用
	 * 
     */
	@NotNull(message = "已启用不能为空")
	@ApiModelProperty(value = "已启用", required = true)
	@Size(max = 255, message = "已启用超长，最多255个字符")
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
