package net.aicoder.speedcloud.icode.business.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 组件本地路径的值对象
*/
@ApiModel(value = "展现组件本地路径的值对象")
public class ComponentLocalLocationVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**组件*/
    @ApiModelProperty(value = "组件")
    private String component;
    private ComponentVO componentVO;


    /**本地路径*/
    @ApiModelProperty(value = "本地路径")
    private String location;


    public String getComponent(){
        return component;
    }
    public void setComponent(String component) {
        this.component = component;
    }
    public ComponentVO getComponentVO(){
        return componentVO;
    }
    public void setComponentVO(ComponentVO componentVO) {
        this.componentVO = componentVO;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}