package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.business.config.vo.CodeRepositoryTypeVO;
import net.aicoder.speedcloud.business.config.vo.DevelopTypeVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 代码库的值对象
*/
@ApiModel(value = "展现代码库的值对象")
@Setter @Getter
public class CodeRepositoryVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "名称")
    private String name;


    /**代码库类型*/
    @ApiModelProperty(value = "代码库类型", notes = "git,svn")
    private String type;
    private CodeRepositoryTypeVO typeVO;


    @ApiModelProperty(value = "url")
    private String url;


    /**开发模式*/
    @ApiModelProperty(value = "开发模式")
    private String developType;
    private DevelopTypeVO developTypeVO;


    @ApiModelProperty(value = "用户名")
    private String username;


    /**描述*/
    @ApiModelProperty(value = "描述")
    private String description;


    /**应用*/
    @ApiModelProperty(value = "应用")
    private String app;
    private AppBaseInfoVO appVO;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}