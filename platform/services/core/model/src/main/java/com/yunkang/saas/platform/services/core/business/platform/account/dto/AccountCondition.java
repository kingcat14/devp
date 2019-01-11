package com.yunkang.saas.platform.services.core.business.platform.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询账号使用的DTO")
@Getter @Setter
public class AccountCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "姓名")
	private String name;
	@ApiModelProperty(value = "账号")
	private String accountName;
	@ApiModelProperty(value = "密码")
	private String accountPassword;
	@ApiModelProperty(value = "手机号")
	private String mobile;
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "已启用")
	private Boolean enable;
	@ApiModelProperty(value = "已过期")
	private Boolean expired;
	@ApiModelProperty(value = "已锁定")
	private Boolean locked;
	@ApiModelProperty(value = "已删除")
	private Boolean deleted;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
