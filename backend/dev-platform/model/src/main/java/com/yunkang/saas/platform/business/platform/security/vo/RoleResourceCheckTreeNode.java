package com.yunkang.saas.platform.business.platform.security.vo;


import com.yunkang.saas.platform.business.resource.vo.ResourceVO;

/**
 */
public class RoleResourceCheckTreeNode {

	private Long id;
	private String name;
	private Long roleId;
	private Long resourceId;
	private Long relationId;
	private Boolean checked;
	protected boolean leaf;

	/**
	 * Constructor for ResourceTreeNode.
	 * @param resource Resource
	 */
	public RoleResourceCheckTreeNode(ResourceVO resource, Long roleId){
		this.id = resource.getId();
		this.name = resource.getName();
		this.roleId = roleId;
		this.resourceId = resource.getId();
		this.checked = false;
		this.leaf = false;
	}

	public RoleResourceCheckTreeNode(ResourceVO resource, Long roleId, Boolean checked){
		this.id = resource.getId();
		this.name = resource.getName();
		this.roleId = roleId;
		this.resourceId = resource.getId();
		this.checked = checked;
		this.leaf = false;
	}



	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
}
