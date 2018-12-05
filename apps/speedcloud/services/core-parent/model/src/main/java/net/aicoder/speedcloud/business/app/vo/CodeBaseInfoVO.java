package net.aicoder.speedcloud.business.app.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 代码基本信息的值对象
*/
@ApiModel(value = "展现代码基本信息的值对象")
@Setter @Getter
public class CodeBaseInfoVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**代码库*/
    @ApiModelProperty(value = "代码库", notes = " ")
    private String codeRepository;
    private CodeRepositoryVO codeRepositoryVO;


    @ApiModelProperty(value = "开发语言")
    private String language;


    @ApiModelProperty(value = "语言级别")
    private String languageLevel;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}