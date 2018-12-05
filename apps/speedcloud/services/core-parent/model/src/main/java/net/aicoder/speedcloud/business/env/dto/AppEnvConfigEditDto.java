package net.aicoder.speedcloud.business.env.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 产品环境
 * @author icode
 */
@ApiModel(value = "修改产品环境使用的DTO")
@Setter @Getter
public class AppEnvConfigEditDto {


	/**所属产品（项目）*/
	@ApiModelProperty(value = "所属产品（项目）", required = false)
	private String project;


	/**环境名称*/
	@ApiModelProperty(value = "环境名称", required = false)
	private String name;


	/**环境级别*/
	@ApiModelProperty(value = "环境级别", required = false)
	private String level;


	/**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;



	public String getProject(){
        return project;
    }
    public void setProject(String project) {
        this.project = project;
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
