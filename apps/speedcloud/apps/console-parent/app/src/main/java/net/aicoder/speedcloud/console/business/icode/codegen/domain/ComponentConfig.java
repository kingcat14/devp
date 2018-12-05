package net.aicoder.speedcloud.console.business.icode.codegen.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Getter
@Setter
public class ComponentConfig {

    private String name;

    private String code;

    private String basePackage;

    private String ui;

    private String groupCode;

    private String productCode;

    private Integer number;

    private String localPath;

    private List<DomainConfig> domainConfigList;


	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
}