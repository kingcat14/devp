package net.aicoder.speedcloud.business.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 代码库
 * @author icode
 */
@ApiModel(value = "修改代码库使用的DTO")
public class CodeRepertoryEditDto {


	/**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "git,svn")
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
