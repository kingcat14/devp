package net.aicoder.speedcloud.icode.business.tplfile.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 组件代码模板的值对象
*/
@ApiModel(value = "展现组件代码模板的值对象")
public class ProjectTplCodeVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**所属系统组件*/
    @ApiModelProperty(value = "所属系统组件")
    private String component;
    private ComponentVO componentVO;


    /**关联的代码模板*/
    @ApiModelProperty(value = "关联的代码模板")
    private String tplCode;
    private TplCodeVO tplCodeVO;


    @ApiModelProperty(value = "自动刷新", notes = "自动同步公共代码模板的更新")
    private Boolean autoUpdate;


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

    public String getTplCode(){
        return tplCode;
    }
    public void setTplCode(String tplCode) {
        this.tplCode = tplCode;
    }
    public TplCodeVO getTplCodeVO(){
        return tplCodeVO;
    }
    public void setTplCodeVO(TplCodeVO tplCodeVO) {
        this.tplCodeVO = tplCodeVO;
    }

    public Boolean getAutoUpdate(){
        return autoUpdate;
    }
    public void setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
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