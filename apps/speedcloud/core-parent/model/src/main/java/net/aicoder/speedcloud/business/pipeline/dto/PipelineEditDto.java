package net.aicoder.speedcloud.business.pipeline.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 流水线
 * @author icode
 */
@ApiModel(value = "修改流水线使用的DTO")
public class PipelineEditDto {


	/**流水线名称*/
	@ApiModelProperty(value = "流水线名称", required = false, notes = "流水线名称")
	private String name;


	/**类型*/
	@ApiModelProperty(value = "类型", required = false)
    private String type;


	/**代码库*/
	@ApiModelProperty(value = "代码库", required = false, notes = "")
	private Long codeRepository;



	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


	public Long getCodeRepository(){
        return codeRepository;
    }
    public void setCodeRepository(Long codeRepository) {
        this.codeRepository = codeRepository;
    }


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
