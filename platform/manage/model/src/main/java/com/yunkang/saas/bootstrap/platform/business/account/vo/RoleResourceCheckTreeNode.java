package com.yunkang.saas.bootstrap.platform.business.account.vo;


import com.yunkang.saas.bootstrap.application.business.resource.vo.ResourceVO;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class RoleResourceCheckTreeNode implements Serializable {

	private Long id;
	private String name;
	private Long roleId;
	private Long resourceId;
	private Long relationId;
	private Boolean checked;
	private Long parentCode;
	private Long code;
	protected boolean leaf;
	protected boolean expanded;
	protected List<RoleResourceCheckTreeNode> children;

	/**
	 * Constructor for ResourceTreeNode.
	 * @param resource Resource
	 */
	public RoleResourceCheckTreeNode(ResourceVO resource){
		this.id = resource.getId();
		this.name = resource.getName();
		this.resourceId = resource.getId();
		this.checked = false;
		this.leaf = ResourceVO.TYPE_FUNCTION.equals(resource.getType());
	}

	public RoleResourceCheckTreeNode(ResourceVO resource, Long roleId){
		this(resource);
		this.roleId = roleId;
		this.checked = false;
		this.leaf = false;
	}

	public RoleResourceCheckTreeNode(ResourceVO resource, Long roleId, Boolean checked){
		this(resource, roleId);
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

	public Long getParentCode() {
		return parentCode;
	}
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}

	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<RoleResourceCheckTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<RoleResourceCheckTreeNode> children) {
		this.children = children;
		this.expanded = true;
	}
	public void addChild(RoleResourceCheckTreeNode resource){
		if(CollectionUtils.isEmpty(children)){
			children = new ArrayList<>();
		}
		children.add(resource);
		this.expanded = true;
	}
}
