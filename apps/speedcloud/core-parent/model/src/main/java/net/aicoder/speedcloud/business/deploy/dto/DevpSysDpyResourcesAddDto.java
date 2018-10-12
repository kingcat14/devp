package net.aicoder.speedcloud.business.deploy.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 方案资源
 * @author icode
 */
@ApiModel(value = "新增方案资源使用的DTO")
public class DevpSysDpyResourcesAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**资源名称*/
	@ApiModelProperty(value = "资源名称", required = false, notes = "[资源名称]")
	private String name;

    /**资源代码*/
	@ApiModelProperty(value = "资源代码", required = false, notes = "[资源代码]")
	private String code;

    /**资源别名*/
	@ApiModelProperty(value = "资源别名", required = false, notes = "[资源别名]")
	private String alias;

    /**资源类别*/
	@ApiModelProperty(value = "资源类别", required = false)
	private Long category;

    /**资源类型*/
	@ApiModelProperty(value = "资源类型", required = false, notes = "[类型]-运行环境/数据库/消息队列/缓存/外部接口")
	private Long type;

    /**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "[备注]")
	private String notes;

    /**资源描述*/
	@ApiModelProperty(value = "资源描述", required = false, notes = "[资源描述]")
	private String description;

    /**版本*/
	@ApiModelProperty(value = "版本", required = false, notes = "[版本]")
	private String version;

    /**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;

    /**所属环境*/
	@ApiModelProperty(value = "所属环境", required = false)
	private Long evn;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]")
	private String status;

    /**产品编号*/
	@ApiModelProperty(value = "产品编号", required = false, notes = "[产品编号]")
	private Long project;

    /**外部资源*/
	@ApiModelProperty(value = "外部资源", required = false)
	private Boolean outerResource;

    /**所属方案*/
	@ApiModelProperty(value = "所属方案", required = true)
	private Long scheme;


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

	public Long getCategory(){
        return category;
    }
    public void setCategory(Long category) {
        this.category = category;
    }

	public Long getType(){
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion(){
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getEvn(){
        return evn;
    }
    public void setEvn(Long evn) {
        this.evn = evn;
    }

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Long getProject(){
        return project;
    }
    public void setProject(Long project) {
        this.project = project;
    }

	public Boolean getOuterResource(){
		return outerResource;
	}
	public void setOuterResource(Boolean outerResource) {
		this.outerResource = outerResource;
	}

	public Long getScheme(){
        return scheme;
    }
    public void setScheme(Long scheme) {
        this.scheme = scheme;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
