package net.aicoder.speedcloud.business.deployscheme.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 方案资源的值对象
*/
@ApiModel(value = "展现方案资源的值对象")
@Getter
@Setter
public class ResourceVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**资源名称*/
    @ApiModelProperty(value = "资源名称", notes = "[资源名称]")
    private String name;


    /**资源代码*/
    @ApiModelProperty(value = "资源代码", notes = "[资源代码]")
    private String code;


    /**资源别名*/
    @ApiModelProperty(value = "资源别名", notes = "[资源别名]")
    private String alias;


    /**资源类别*/
    @ApiModelProperty(value = "资源类别")
    private String category;
    private ResourcesCategoryVO categoryVO;


    /**资源类型*/
    @ApiModelProperty(value = "资源类型", notes = "[类型]-运行环境/数据库/消息队列/缓存/外部接口")
    private String type;
    private ResourcesTypeVO typeVO;


    /**备注*/
    @ApiModelProperty(value = "备注", notes = "[备注]")
    private String notes;


    /**资源描述*/
    @ApiModelProperty(value = "资源描述", notes = "[资源描述]")
    private String description;


    /**版本*/
    @ApiModelProperty(value = "版本", notes = "[版本]")
    private String version;


    @ApiModelProperty(value = "顺序号")
    private Integer seq;


    /**所属环境*/
    @ApiModelProperty(value = "所属环境")
    private String env;
    private AppEnvConfigVO envVO;


    /**产品编号*/
    @ApiModelProperty(value = "产品编号", notes = "[产品编号]")
    private String project;
    private ProjectVO projectVO;


    @ApiModelProperty(value = "外部资源")
    private Boolean outerResource;


    /**所属方案*/
    @ApiModelProperty(value = "所属方案")
    private Long scheme;
    private SchemeVO schemeVO;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}