package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 代码库详细信息的值对象
*/
@ApiModel(value = "展现代码库详细信息的值对象")
@Getter @Setter
public class CodeBaseInfoVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**代码库*/
    @ApiModelProperty(value = "代码库", notes = " ")
    private Long codeRepository;
    private CodeRepositoryVO codeRepositoryVO;


    /**开发语言*/
    @ApiModelProperty(value = "开发语言")
    private String language;


    /**语言级别*/
    @ApiModelProperty(value = "语言级别")
    private String languageLevel;


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}