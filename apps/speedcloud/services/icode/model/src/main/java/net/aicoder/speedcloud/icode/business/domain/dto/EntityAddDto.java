package net.aicoder.speedcloud.icode.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 领域对象
 * @author icode
 */
@ApiModel(value = "新增领域对象使用的DTO")
@Setter @Getter
public class EntityAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**实体代码*/
	@ApiModelProperty(value = "实体代码", required = false, notes = "")
	private String code;

    /**实体名称*/
	@ApiModelProperty(value = "实体名称", required = false, notes = "")
	private String name;

    /**排序*/
	@ApiModelProperty(value = "排序", required = false, notes = "")
	private Integer viewIndex;

    /**描述*/
	@ApiModelProperty(value = "描述", required = false, notes = "")
	private String description;

    /**父对象*/
	@ApiModelProperty(value = "父对象", required = false)
	private String parentEntity;

    /**所属聚合*/
	@ApiModelProperty(value = "所属聚合", required = false)
	private String aggregate;

    /**所属领域*/
	@ApiModelProperty(value = "所属领域", required = false)
	private String domain;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
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

	public Integer getViewIndex(){
		return viewIndex;
	}
	public void setViewIndex(Integer viewIndex) {
		this.viewIndex = viewIndex;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentEntity(){
		return parentEntity;
	}
	public void setParentEntity(String parentEntity) {
		this.parentEntity = parentEntity;
	}

	public String getAggregate(){
        return aggregate;
    }
    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }

	public String getDomain(){
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
