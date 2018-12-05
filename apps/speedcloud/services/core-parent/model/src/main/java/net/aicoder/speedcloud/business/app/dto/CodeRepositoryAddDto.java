package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 代码库
 * @author icode
 */
@ApiModel(value = "新增代码库使用的DTO")
@Setter @Getter
public class CodeRepositoryAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;

    /**代码库类型*/
	@ApiModelProperty(value = "代码库类型", required = false, notes = "git,svn")
	private String type;

    /**url*/
	@ApiModelProperty(value = "url", required = false)
	private String url;

    /**开发模式*/
	@ApiModelProperty(value = "开发模式", required = false)
	private Long developType;

    /**用户名*/
	@ApiModelProperty(value = "用户名", required = false)
	private String username;

    /**密码*/
	@ApiModelProperty(value = "密码", required = false)
	private String password;

    /**描述*/
	@ApiModelProperty(value = "描述", required = false)
	private String description;

    /**应用*/
	@ApiModelProperty(value = "应用", required = false)
	private String app;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public Long getDevelopType(){
        return developType;
    }
    public void setDevelopType(Long developType) {
        this.developType = developType;
    }

	public String getUsername(){
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getApp(){
        return app;
    }
    public void setApp(String app) {
        this.app = app;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
