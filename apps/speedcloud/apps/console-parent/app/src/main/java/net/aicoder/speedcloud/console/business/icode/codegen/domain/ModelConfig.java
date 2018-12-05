package net.aicoder.speedcloud.console.business.icode.codegen.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 模型配置，可以是ENTITY, VO
 * Created by gonghongrui on 18/4/1.
 */
@Slf4j
@Getter @Setter
public abstract class ModelConfig {

    private String id;
	private ComponentConfig system;
    private DomainConfig module;
    private String code;
    private String name;
    private String description;

    public abstract String getModelType();

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
