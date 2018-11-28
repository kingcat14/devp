package net.aicoder.speedcloud.icode.business.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplSetVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 组件的值对象
*/
@ApiModel(value = "展现组件的值对象")
@Setter @Getter
public class ComponentVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**所属产品*/
    @ApiModelProperty(value = "所属产品")
    private String product;
    private ProductVO productVO;


    @ApiModelProperty(value = "组件编号", notes = "")
    private Integer number;


    @ApiModelProperty(value = "组件名称", notes = "")
    private String name;


    @ApiModelProperty(value = "组件代码", notes = "")
    private String code;


    @ApiModelProperty(value = "基础包", notes = "")
    private String basePackage;


    /**代码模板*/
    @ApiModelProperty(value = "代码模板", notes = "")
    private String tplSet;
    private TplSetVO tplSetVO;


    /**描述*/
    @ApiModelProperty(value = "描述", notes = "")
    private String description;


    /**类型*/
    @ApiModelProperty(value = "类型", notes = "IOS、ANDROID、WEB、应用、服务、公共组件")
    private String type;
    private ComponentTypeVO typeVO;


    @ApiModelProperty(value = "可运行组件")
    private Boolean runnable;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}