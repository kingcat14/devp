package net.aicoder.speedcloud.icode.business.platformmapping.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 组件映射的值对象
*/
@ApiModel(value = "展现组件映射的值对象")
@Setter @Getter
public class ComponentMappingVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**组件*/
    @ApiModelProperty(value = "组件")
    private String component;
    private ComponentVO componentVO;


    @ApiModelProperty(value = "平台组件")
    private String platformComponentName;


    @ApiModelProperty(value = "平台组件ID")
    private String platformComponentId;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}