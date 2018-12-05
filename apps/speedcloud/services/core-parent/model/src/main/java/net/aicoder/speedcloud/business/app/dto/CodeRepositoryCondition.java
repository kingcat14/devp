package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(value = "查询代码库使用的DTO")
@Getter @Setter
public class CodeRepositoryCondition {

    @ApiModelProperty(value = "租户id")
    private Long tid;
	@ApiModelProperty(value = "名称")
	private String name;
    @ApiModelProperty(value = "代码库类型", notes = "git,svn")
    private String type;
	@ApiModelProperty(value = "url")
	private String url;
    @ApiModelProperty(value = "开发模式")
    private Long developType;
	@ApiModelProperty(value = "用户名")
	private String username;
	@ApiModelProperty(value = "密码")
	private String password;
	@ApiModelProperty(value = "描述")
	private String description;
    @ApiModelProperty(value = "应用")
    private String app;





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
