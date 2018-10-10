package net.aicoder.speedcloud.business.deploy.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询部署方案使用的DTO")
public class DevpSysDpySchemeCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "租户id最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户id最小值")
	private Long tidMin;
	@ApiModelProperty(value = "方案名称", notes = "[方案名称]")
	private String name;
	@ApiModelProperty(value = "方案代码", notes = "[方案代码]")
	private String code;
	@ApiModelProperty(value = "方案别名", notes = "[方案别名]")
	private String alias;
	@ApiModelProperty(value = "方案描述", notes = "[方案描述]")
	private String description;
	@ApiModelProperty(value = "方案类型", notes = "[类型]-开发,测试,验证,生产")
	private String type;
	@ApiModelProperty(value = "版本", notes = "[版本]")
	private String version;
	@ApiModelProperty(value = "版本标识后缀", notes = "[版本标识后缀]")
	private String verPostfix;
	@ApiModelProperty(value = "已生效", notes = "[状态]")
	private Boolean status;
	@ApiModelProperty(value = "备注", notes = "[备注]")
	private String notes;
    @ApiModelProperty(value = "所属项目（产品）", notes = "[所属项目]")
    private Long project;
    @ApiModelProperty(value = "所属环境", notes = "[所属环境]")
    private Long evn;


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


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public String getAlias(){
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getVersion(){
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}


	public String getVerPostfix(){
		return verPostfix;
	}
	public void setVerPostfix(String verPostfix) {
		this.verPostfix = verPostfix;
	}


	public Boolean getStatus(){
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}


	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}


    public Long getProject(){
        return project;
    }
    public void setProject(Long project) {
        this.project = project;
    }


    public Long getEvn(){
        return evn;
    }
    public void setEvn(Long evn) {
        this.evn = evn;
    }




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
