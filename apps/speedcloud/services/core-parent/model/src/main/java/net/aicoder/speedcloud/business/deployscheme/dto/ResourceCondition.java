package net.aicoder.speedcloud.business.deployscheme.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询方案资源使用的DTO")
public class ResourceCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户id")
	private Long tid;
	@ApiModelProperty(value = "资源名称", notes = "[资源名称]")
	private String name;
	@ApiModelProperty(value = "资源代码", notes = "[资源代码]")
	private String code;
	@ApiModelProperty(value = "资源别名", notes = "[资源别名]")
	private String alias;
    @ApiModelProperty(value = "资源类别")
    private Long category;
    @ApiModelProperty(value = "资源类型", notes = "[类型]-运行环境/数据库/消息队列/缓存/外部接口")
    private Long type;
	@ApiModelProperty(value = "备注", notes = "[备注]")
	private String notes;
	@ApiModelProperty(value = "资源描述", notes = "[资源描述]")
	private String description;
	@ApiModelProperty(value = "版本", notes = "[版本]")
	private String version;
	@ApiModelProperty(value = "顺序号")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
    @ApiModelProperty(value = "所属环境")
    private Long evn;
	@ApiModelProperty(value = "状态", notes = "[状态]")
	private String status;
    @ApiModelProperty(value = "产品编号", notes = "[产品编号]")
    private String project;
	@ApiModelProperty(value = "外部资源")
	private Boolean outerResource;
    @ApiModelProperty(value = "所属方案")
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

	public Integer getSeqMin(){
		return seqMin;
	}
	public void setSeqMin(Integer seqMin) {
		this.seqMin = seqMin;
	}

	public Integer getSeqMax(){
		return seqMax;
	}
	public void setSeqMax(Integer seqMax) {
		this.seqMax = seqMax;
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


    public String getProject(){
        return project;
    }
    public void setProject(String project) {
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
