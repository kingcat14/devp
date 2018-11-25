package net.aicoder.speedcloud.icode.business.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 组件-领域-关系的值对象
*/
@ApiModel(value = "展现组件-领域-关系的值对象")
@Setter @Getter
public class ComponentDomainRelationVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**系统组件*/
    @ApiModelProperty(value = "系统组件")
    private String component;
    private ComponentVO componentVO;


    /**领域*/
    @ApiModelProperty(value = "领域")
    private String domain;
    private DomainVO domainVO;


    @ApiModelProperty(value = "关系类型", notes = "引用领域业务、实现领域业务")
    private String relationType;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}