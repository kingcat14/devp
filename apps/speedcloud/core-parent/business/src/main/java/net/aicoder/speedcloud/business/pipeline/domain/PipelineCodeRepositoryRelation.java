package net.aicoder.speedcloud.business.pipeline.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 流水线代码库关联
 * @author icode
 */
@Entity
@Table(appliesTo = "pipeline_code_repository_relation", comment = "[流水线代码库关联]")
//@DynamicUpdate
//@DynamicInsert
public class PipelineCodeRepositoryRelation extends BaseEntity{

	public static final String PROPERTY_CODE_REPOSITORY = "codeRepository";
	public static final String PROPERTY_PIPELINE = "pipeline";
	public static final String PROPERTY_AUTO_START = "autoStart";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 代码库
    * 
    */
    @Column(name = "code_repository", nullable = false, updatable = true)
	private Long codeRepository;

    /**
    * 流水线
    * 
    */
    @Column(name = "pipeline", nullable = false, updatable = true)
	private Long pipeline;

    /**
    * 提交代码触发流水线
    * 
    */
    @Column(name = "auto_start", nullable = false, updatable = true)
	private Boolean autoStart;

	public Long getCodeRepository(){
		return codeRepository;
	}
	public void setCodeRepository(Long codeRepository) {
		this.codeRepository = codeRepository;
	}

	public Long getPipeline(){
		return pipeline;
	}
	public void setPipeline(Long pipeline) {
		this.pipeline = pipeline;
	}

	public Boolean getAutoStart(){
		return autoStart;
	}
	public void setAutoStart(Boolean autoStart) {
		this.autoStart = autoStart;
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

