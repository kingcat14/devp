package net.aicoder.speedcloud.business.pipeline.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.yunkang.saas.bootstrap.common.business.simpleconfig.vo.SimpleConfigVO;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 流水线的值对象
*/
@ApiModel(value = "展现流水线的值对象")
public class PipelineVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**流水线名称*/
    @ApiModelProperty(value = "流水线名称", notes = "流水线名称")
    private String name;


    /**类型*/
    @ApiModelProperty(value = "类型")
    private String type;
    private SimpleConfigVO typeVO;


    /**代码库*/
    @ApiModelProperty(value = "代码库", notes = "")
    private Long codeRepository;
    private CodeRepositoryVO codeRepositoryVO;


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
    public SimpleConfigVO getTypeVO(){
        return typeVO;
    }
    public void setTypeVO(SimpleConfigVO typeVO) {
        this.typeVO = typeVO;
    }

    public Long getCodeRepository(){
        return codeRepository;
    }
    public void setCodeRepository(Long codeRepository) {
        this.codeRepository = codeRepository;
    }
    public CodeRepositoryVO getCodeRepositoryVO(){
        return codeRepositoryVO;
    }
    public void setCodeRepositoryVO(CodeRepositoryVO codeRepositoryVO) {
        this.codeRepositoryVO = codeRepositoryVO;
    }


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}