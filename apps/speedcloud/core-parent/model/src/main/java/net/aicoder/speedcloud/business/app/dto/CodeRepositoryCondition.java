package net.aicoder.speedcloud.business.app.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询代码库使用的DTO")
public class CodeRepositoryCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "类型", notes = "git,svn")
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


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
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




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
