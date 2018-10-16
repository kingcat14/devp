package net.aicoder.speedcloud.business.deployscheme.dto;

import com.yunkang.saas.common.framework.eo.SaaSCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@ApiModel(value = "查询方案资源间关系使用的DTO")
public class ResourceRelationCondition extends SaaSCondition{

	@ApiModelProperty(value = "租户编号", notes = "[租户编号]")
	private Long tid;
	@ApiModelProperty(value = "租户编号最大值")
	private Long tidMax;
	@ApiModelProperty(value = "租户编号最小值")
	private Long tidMin;
    @ApiModelProperty(value = "主资源", notes = "[元素类型]-SYS_DPY_CMP_REF // 部署组件关联元素")
    private Long resource;
    @ApiModelProperty(value = "关联资源", notes = "[关联产品编号]")
    private Long refResource;
    @ApiModelProperty(value = "对应关系类型", notes = "[类型]-关联类型：部署到、连接、调用")
    private Long type;
	@ApiModelProperty(value = "对应关系代码", notes = "[对应关系代码]")
	private String code;
	@ApiModelProperty(value = "对应关系名称", notes = "[对应关系名称]")
	private String name;
	@ApiModelProperty(value = "对应关系别名", notes = "[对应关系别名]")
	private String alias;
	@ApiModelProperty(value = "对应关系描述", notes = "[对应关系描述]")
	private String description;
    @ApiModelProperty(value = "部署方案编号", notes = "[部署方案编号]")
    private Long scheme;
	@ApiModelProperty(value = "顺序号", notes = "[顺序号]")
	private Integer seq;
	@ApiModelProperty(value = "顺序号最大值")
	private Integer seqMax;
	@ApiModelProperty(value = "顺序号最小值")
	private Integer seqMin;
	@ApiModelProperty(value = "对应关系方向", notes = "[方向]-(保留)")
	private String direction;


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


    public Long getResource(){
        return resource;
    }
    public void setResource(Long resource) {
        this.resource = resource;
    }


    public Long getRefResource(){
        return refResource;
    }
    public void setRefResource(Long refResource) {
        this.refResource = refResource;
    }


    public Long getType(){
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
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


    public Long getScheme(){
        return scheme;
    }
    public void setScheme(Long scheme) {
        this.scheme = scheme;
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


	public String getDirection(){
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}




	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
