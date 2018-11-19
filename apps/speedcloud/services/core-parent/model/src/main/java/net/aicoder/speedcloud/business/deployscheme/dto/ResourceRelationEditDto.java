package net.aicoder.speedcloud.business.deployscheme.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 方案资源间关系
 * @author icode
 */
@ApiModel(value = "修改方案资源间关系使用的DTO")
public class ResourceRelationEditDto {


	/**主资源*/
	@ApiModelProperty(value = "主资源", required = false, notes = "[元素类型]-SYS_DPY_CMP_REF // 部署组件关联元素")
	private Long resource;


	/**关联资源*/
	@ApiModelProperty(value = "关联资源", required = false, notes = "[关联产品编号]")
	private Long refResource;


	/**对应关系类型*/
	@ApiModelProperty(value = "对应关系类型", required = false, notes = "[类型]-关联类型：部署到、连接、调用")
	private Long type;


	/**对应关系代码*/
	@ApiModelProperty(value = "对应关系代码", required = false, notes = "[对应关系代码]")
	private String code;


	/**对应关系名称*/
	@ApiModelProperty(value = "对应关系名称", required = false, notes = "[对应关系名称]")
	private String name;


	/**对应关系别名*/
	@ApiModelProperty(value = "对应关系别名", required = false, notes = "[对应关系别名]")
	private String alias;


	/**对应关系描述*/
	@ApiModelProperty(value = "对应关系描述", required = false, notes = "[对应关系描述]")
	private String description;


	/**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;


	/**对应关系方向*/
	@ApiModelProperty(value = "对应关系方向", required = false, notes = "[方向]-(保留)")
	private String direction;



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


	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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
