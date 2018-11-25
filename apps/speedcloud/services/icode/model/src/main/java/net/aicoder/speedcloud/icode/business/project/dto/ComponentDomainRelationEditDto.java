package net.aicoder.speedcloud.icode.business.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 组件-领域-关系
 * @author icode
 */
@ApiModel(value = "修改组件-领域-关系使用的DTO")
@Setter @Getter
public class ComponentDomainRelationEditDto {


	/**系统组件*/
	@ApiModelProperty(value = "系统组件", required = false)
	private String component;


	/**领域*/
	@ApiModelProperty(value = "领域", required = false)
	private String domain;


	/**关系类型*/
	@ApiModelProperty(value = "关系类型", required = false, notes = "引用领域业务、实现领域业务")
	private String relationType;



	public String getComponent(){
        return component;
    }
    public void setComponent(String component) {
        this.component = component;
    }


	public String getDomain(){
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }


	public String getRelationType(){
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
