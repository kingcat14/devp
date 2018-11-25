package net.aicoder.speedcloud.business.env.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 应用环境
 * @author icode
 */
@ApiModel(value = "新增应用环境使用的DTO")
public class AppEnvConfigAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**环境名称*/
	@ApiModelProperty(value = "环境名称", required = false)
	private String name;

    /**环境级别*/
	@ApiModelProperty(value = "环境级别", required = false)
	private String level;

    /**所属项目（产品）*/
	@ApiModelProperty(value = "所属项目（产品）", required = false)
	private String project;

    /**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;


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

	public String getLevel(){
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	public String getProject(){
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
