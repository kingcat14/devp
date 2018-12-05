package net.aicoder.speedcloud.console.business.icode.codegen.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter @Setter
public class DomainConfig {

	/**定级模块的代码*/
	private String topCode;

	/**英文名称*/
    private String code;

    /**中文展现名称*/
    private String name;

    private DomainConfig parent;

    private ComponentConfig system;

	@Override
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}
