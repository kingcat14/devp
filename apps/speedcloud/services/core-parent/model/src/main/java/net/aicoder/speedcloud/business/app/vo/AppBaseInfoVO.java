package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 应用（系统）的值对象
*/
@ApiModel(value = "展现应用（系统）的值对象")
@Setter @Getter
public class AppBaseInfoVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**所属项目*/
    @ApiModelProperty(value = "所属项目")
    private String project;
    private ProjectVO projectVO;


    /**应用类型*/
    @ApiModelProperty(value = "应用类型")
    private String type;
    private ApplicationTypeVO typeVO;


    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "代码")
    private String code;


    @ApiModelProperty(value = "状态")
    private String status;


    /**描述*/
    @ApiModelProperty(value = "描述")
    private String description;


    @ApiModelProperty(value = "注册时间")
    private String registTime;


    /**代码库信息*/
    private CodeRepositoryVO codeRepositoryVO;

    /**应用开发配置*/
    private AppDevelopConfigVO appDevelopConfigVO;



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}