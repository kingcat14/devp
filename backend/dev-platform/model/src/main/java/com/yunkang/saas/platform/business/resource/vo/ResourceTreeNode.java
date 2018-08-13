package com.yunkang.saas.platform.business.resource.vo;


import com.yunkang.saas.platform.business.platform.application.vo.AppVO;

/**
 */
public class ResourceTreeNode extends ResourceVO {
	
	/**
	 * Constructor for ResourceTreeNode.
	 * @param resource Resource
	 */
	public ResourceTreeNode(ResourceVO resource){

		this.setId(resource.getId());
		this.setAppId(resource.getAppId());
		this.setName(resource.getName());
		this.setCode(resource.getCode());
		this.setUrl(resource.getUrl());
		this.setType(resource.getType());
		this.setOrderIndex(resource.getOrderIndex());
		this.setParentCode(resource.getParentCode());
		this.leaf = "function".equals(resource.getType());
		this.setIconCls(resource.getType());
	}

	public ResourceTreeNode(AppVO appVO){

		this.setId(appVO.getId());
		this.setAppId(appVO.getId());
		this.setName("(应用)"+appVO.getName());
		this.setCode(-1L);
		this.setUrl(null);
		this.setType("APP");
		this.setOrderIndex(getOrderIndex());
		this.setParentCode(0L);
		this.leaf = false;
//		this.setIconCls();
	}

	protected boolean leaf;
	protected String iconCls;
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = "function".equals(iconCls)?"x-fa fa-file":"x-fa fa-folder";
	}



}
