package net.aicoder.maintenance.business.asset.info.controller;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.maintenance.business.asset.info.vo.AssetTypeVO;

public class AssetTypeTreeNode extends AssetTypeVO {

    protected boolean leaf;
    protected String iconCls;
    protected boolean expanded;
    private List<AssetTypeTreeNode> children;

    public boolean isLeaf() {
        return leaf;
    }
    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getIconCls() {
        return iconCls;
    }
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<AssetTypeTreeNode> getChildren() {
        return children;
    }
    public void setChildren(List<AssetTypeTreeNode> children) {
        this.children = children;
    }
    public void addChild(AssetTypeTreeNode node){
        if(children == null){
            children = new ArrayList<>();
        }
        children.add(node);
        expanded = true;
        leaf = false;
    }
}
