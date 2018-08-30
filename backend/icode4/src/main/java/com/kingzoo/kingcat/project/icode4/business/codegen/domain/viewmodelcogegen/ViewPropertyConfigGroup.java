package com.kingzoo.kingcat.project.icode4.business.codegen.domain.viewmodelcogegen;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个属性组
 * @author gonghongrui on 2018/4/2.
 */
public class ViewPropertyConfigGroup {

	public List<ViewModelPropertyConfig> smallPropertyList = new ArrayList<>();
	public List<ViewModelPropertyConfig> gridPropertyList = new ArrayList<>();
	public List<ViewModelPropertyConfig> textPropertyList = new ArrayList<>();
	public List<ViewModelPropertyConfig> allPropertyList = new ArrayList<>();

	public void addProperty(ViewModelPropertyConfig viewModelPropertyConfig){

		this.allPropertyList.add(viewModelPropertyConfig);

		if("Text".equals(viewModelPropertyConfig.getType())){
			textPropertyList.add(viewModelPropertyConfig);
		}else{
			smallPropertyList.add(viewModelPropertyConfig);
		}

	}


	public List<ViewModelPropertyConfig> getSmallPropertyList() {
		return smallPropertyList;
	}

	public void setSmallPropertyList(List<ViewModelPropertyConfig> smallPropertyList) {
		this.smallPropertyList = smallPropertyList;
	}

	public List<ViewModelPropertyConfig> getTextPropertyList() {
		return textPropertyList;
	}

	public void setTextPropertyList(List<ViewModelPropertyConfig> textPropertyList) {
		this.textPropertyList = textPropertyList;
	}

	public List<ViewModelPropertyConfig> getAllPropertyList() {
		return allPropertyList;
	}

	public void setAllPropertyList(List<ViewModelPropertyConfig> allPropertyList) {
		this.allPropertyList = allPropertyList;
	}
}
