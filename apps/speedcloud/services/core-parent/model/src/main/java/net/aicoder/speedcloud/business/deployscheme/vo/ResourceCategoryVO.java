package net.aicoder.speedcloud.business.deployscheme.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 部署资源类别的值对象
*/
@ApiModel(value = "展现部署资源类别的值对象")
@Setter @Getter
public class ResourceCategoryVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "代码")
    private String code;


    @ApiModelProperty(value = "图标")
    private String icon;


    @ApiModelProperty(value = "排序")
    private Integer idx;




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}