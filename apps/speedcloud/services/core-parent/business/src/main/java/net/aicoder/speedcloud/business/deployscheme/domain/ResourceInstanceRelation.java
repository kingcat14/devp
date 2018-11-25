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
 * 方案资源关联实例
 * @author icode
 */
@Entity(name = "deployscheme_resource_instance_relation")
@Table(appliesTo = "deployscheme_resource_instance_relation", comment = "[方案资源关联实例]")
//@DynamicUpdate
//@DynamicInsert
public class ResourceInstanceRelation extends BaseEntity<Long>{

	public static final String PROPERTY_TID = "tid";
	public static final String PROPERTY_TYPE = "type";
	public static final String PROPERTY_STATUS = "status";
	public static final String PROPERTY_NOTES = "notes";
	public static final String PROPERTY_SEQ = "seq";
	public static final String PROPERTY_PROJECT = "project";
	public static final String PROPERTY_SCHEME = "scheme";
	public static final String PROPERTY_RESOURCE = "resource";
	public static final String PROPERTY_ASSET = "asset";
	public static final String PROPERTY_ASSET_CATEGORY_CODE = "assetCategoryCode";
	public static final String PROPERTY_ASSET_TYPE_CODE = "assetTypeCode";


    @Id
    @Column(name = "id")
    private Long id;


    /**
    * 租户编号
    * [租户编号]
    */
    @Column(name = "tid", nullable = false, updatable = true)
	private Long tid;

    /**
    * 类型
    * [类型]
    */
    @Column(name = "type", nullable = true, updatable = true)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;

    /**
    * 状态
    * [状态]true:生效，false:失效
    */
    @Column(name = "status", nullable = true, updatable = true)
	private Boolean status;

    /**
    * 备注
    * [备注]
    */
    @Column(name = "notes", nullable = true, updatable = true)
	@Size(max = 255, message = "备注超长，最多255个字符")
	private String notes;

    /**
    * 顺序号
    * [顺序号]
    */
    @Column(name = "seq", nullable = true, updatable = true)
	private Integer seq;

    /**
    * 产品编号
    * [产品编号]
    */
    @Column(name = "project", nullable = true, updatable = true)
	private String project;

    /**
    * 部署方案编号
    * [部署方案编号]
    */
    @Column(name = "scheme", nullable = false, updatable = false)
	private Long scheme;

    /**
    * 关联资源编号
    * [关联资源编号]
    */
    @Column(name = "resource", nullable = false, updatable = true)
	private Long resource;

    /**
    * 关联实例编号
    * [关联IT资产编号]
    */
    @Column(name = "asset", nullable = true, updatable = true)
	private Long asset;

    /**
    * 关联实例类别代码
    * [关联IT资产元素类型]
    */
    @Column(name = "asset_category_code", nullable = true, updatable = true)
	@Size(max = 255, message = "关联实例类别代码超长，最多255个字符")
	private String assetCategoryCode;

    /**
    * 关联实例类型代码
    * [关联IT资产类型代码]
    */
    @Column(name = "asset_type_code", nullable = true, updatable = true)
	@Size(max = 255, message = "关联实例类型代码超长，最多255个字符")
	private String assetTypeCode;

	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Boolean getStatus(){
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}

	public Long getScheme(){
		return scheme;
	}
	public void setScheme(Long scheme) {
		this.scheme = scheme;
	}

	public Long getResource(){
		return resource;
	}
	public void setResource(Long resource) {
		this.resource = resource;
	}

	public Long getAsset(){
		return asset;
	}
	public void setAsset(Long asset) {
		this.asset = asset;
	}

	public String getAssetCategoryCode(){
		return assetCategoryCode;
	}
	public void setAssetCategoryCode(String assetCategoryCode) {
		this.assetCategoryCode = assetCategoryCode;
	}

	public String getAssetTypeCode(){
		return assetTypeCode;
	}
	public void setAssetTypeCode(String assetTypeCode) {
		this.assetTypeCode = assetTypeCode;
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

