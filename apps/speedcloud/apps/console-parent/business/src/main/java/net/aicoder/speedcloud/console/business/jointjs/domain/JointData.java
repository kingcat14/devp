package net.aicoder.speedcloud.console.business.jointjs.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 图形数据
 * @author icode
 */
@Entity
@Table(appliesTo = "joint_data", comment = "[图形数据]")
//@DynamicUpdate
//@DynamicInsert
public class JointData extends BaseEntity<String>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_SCHEME = "scheme";
	public static final String PROPERTY_VIEW_JSON = "viewJson";
	public static final String PROPERTY_TYPE = "type";


    @Id
    @Column(name = "id")
    private String id;


    /**
    * 租户id
    * 
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 方案ID
    * 
    */
    @Column(name = "scheme", nullable = true, updatable = true)
	private Long scheme;

    /**
    * json
    * 
    */
    @Column(name = "view_json", nullable = true, updatable = true, length=1999, columnDefinition = "TEXT")
	private String viewJson;

    /**
    * 类型
    * 
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getScheme(){
		return scheme;
	}
	public void setScheme(Long scheme) {
		this.scheme = scheme;
	}

	public String getViewJson(){
		return viewJson;
	}
	public void setViewJson(String viewJson) {
		this.viewJson = viewJson;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

