package net.aicoder.speedcloud.icode.business.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 领域对象行为参数属性
 * @author icode
 */
@ApiModel(value = "新增领域对象行为参数属性使用的DTO")
@Setter @Getter
public class EntityActionParameterPropertyAddDto {

    /**租户id*/
	@ApiModelProperty(value = "租户id", required = false)
	private Long tid;

    /**所属行为参数*/
	@ApiModelProperty(value = "所属行为参数", required = false, notes = "")
	private String actionParameter;

    /**属性代码*/
	@ApiModelProperty(value = "属性代码", required = false, notes = "")
	private String code;

    /**属性名称*/
	@ApiModelProperty(value = "属性名称", required = false, notes = "中文名称")
	private String name;

    /**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "")
	private String type;

    /**外覆类型*/
	@ApiModelProperty(value = "外覆类型", required = false, notes = "List")
	private String wrapperType;

    /**排序*/
	@ApiModelProperty(value = "排序", required = false, notes = "")
	private Integer idx;

    /**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "")
	private String memo;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getActionParameter(){
        return actionParameter;
    }
    public void setActionParameter(String actionParameter) {
        this.actionParameter = actionParameter;
    }

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

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

	public String getWrapperType(){
		return wrapperType;
	}
	public void setWrapperType(String wrapperType) {
		this.wrapperType = wrapperType;
	}

	public Integer getIdx(){
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
