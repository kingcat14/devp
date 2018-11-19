package net.aicoder.speedcloud.business.deployscheme.domain;

import com.yunkang.saas.common.jpa.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;



/**
 * 方案资源间关系
 * @author icode
 */
@Entity(name = "deployscheme_resource_relation")
@Table(appliesTo = "deployscheme_resource_relation", comment = "[方案资源间关系]")
//@DynamicUpdate
//@DynamicInsert
public class ResourceRelation extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_RESOURCE = "resource";
	public static final String PROPERTY_REF_RESOURCE = "refResource";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_CODE = "code";
	public static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_ALIAS = "alias";
	public static final String PROPERTY_DESCRIPTION = "description";
	public static final String PROPERTY_SCHEME = "scheme";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_DIRECTION = "direction";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 租户编号
    * [租户编号]
    */
    @Column(name = "tid", nullable = false, updatable = false)
	private Long tid;

    /**
    * 主资源
    * [元素类型]-SYS_DPY_CMP_REF // 部署组件关联元素
    */
    @Column(name = "resource", nullable = false, updatable = true)
	private Long resource;

    /**
    * 关联资源
    * [关联产品编号]
    */
    @Column(name = "ref_resource", nullable = false, updatable = true)
	private Long refResource;

    /**
    * 对应关系类型
    * [类型]-关联类型：部署到、连接、调用
    */
    @Column(name = "type", nullable = true, updatable = true)
	private Long type;

    /**
    * 对应关系代码
    * [对应关系代码]
    */
    @Column(name = "code", nullable = true, updatable = true)
	@Size(max = 255, message = "对应关系代码超长，最多255个字符")
	private String code;

    /**
    * 对应关系名称
    * [对应关系名称]
    */
    @Column(name = "name", nullable = true, updatable = true)
	@Size(max = 255, message = "对应关系名称超长，最多255个字符")
	private String name;

    /**
    * 对应关系别名
    * [对应关系别名]
    */
    @Column(name = "alias", nullable = true, updatable = true)
	@Size(max = 255, message = "对应关系别名超长，最多255个字符")
	private String alias;

    /**
    * 对应关系描述
    * [对应关系描述]
    */
    @Column(name = "description", nullable = true, updatable = true)
	@Size(max = 255, message = "对应关系描述超长，最多255个字符")
	private String description;

    /**
    * 部署方案编号
    * [部署方案编号]
    */
    @Column(name = "scheme", nullable = false, updatable = false)
	private Long scheme;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

    /**
    * 对应关系方向
    * [方向]-(保留)
    */
    @Column(name = "direction", nullable = true, updatable = true)
	@Size(max = 255, message = "对应关系方向超长，最多255个字符")
	private String direction;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getResource(){
		return resource;
	}
	public void setResource(Long resource) {
		this.resource = resource;
	}

	public Long getRefResource(){
		return refResource;
	}
	public void setRefResource(Long refResource) {
		this.refResource = refResource;
	}

	public Long getType(){
		return type;
	}
	public void setType(Long type) {
		this.type = type;
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

	public String getAlias(){
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Long getScheme(){
		return scheme;
	}
	public void setScheme(Long scheme) {
		this.scheme = scheme;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDirection(){
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
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

