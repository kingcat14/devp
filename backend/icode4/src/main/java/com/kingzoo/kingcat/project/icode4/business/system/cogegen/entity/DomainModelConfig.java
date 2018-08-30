package com.kingzoo.kingcat.project.icode4.business.system.cogegen.entity;

import com.kingzoo.kingcat.project.icode4.business.codegen.domain.ModelConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 领域模型的模型配置
 * Created by gonghongrui on 14/11/4.
 */
public class DomainModelConfig extends ModelConfig {

	private boolean rootModel = true;

    private List<DomainModelConfig> childModelList = new ArrayList<>();

    private DomainModelConfig parentModel;

    private String tableName;

    private String description;

    @Override
    public String getModelType() {
        return "EO";
    }

    private DomainModelPropertyConfig primaryKey;

    /**属性引用到的其他对象(重复的对象只存在一份)*/
    private List<DomainModelConfig> reference = new ArrayList<>();

    /**属性(引用组合并成一个属性)*/
    private List<DomainModelPropertyConfig> attribute = new ArrayList<>();

    private List<DomainModelPropertyConfig> referenceProperty = new ArrayList<>();


    private List<DomainModelPropertyConfig> simpleConditionList = new ArrayList<>();
    private List<DomainModelPropertyConfig> visiblePropertyList = new ArrayList<>();
    private List<DomainModelPropertyConfig> addablePropertyList = new ArrayList<>();
    private List<DomainModelPropertyConfig> editablePropertyList = new ArrayList<>();
    private List<DomainModelPropertyConfig> gridPropertyList = new ArrayList<>();
    private List<DomainModelPropertyConfig> searchPropertyList = new ArrayList<>();

    private List<DomainModelPropertyConfig> requiredPropertyList = new ArrayList<>();
    private List<DomainModelPropertyConfig> persistPropertyList = new ArrayList<>();

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public List<DomainModelConfig> getReference(){
        return reference;
    }
    public void addReference(DomainModelConfig newReference){

        /*判断一下,重复的就不再添加了*/
        for(DomainModelConfig config : reference){
            if(config.getId().equals(newReference.getId())){
                return;
            }
        }
        reference.add(newReference);
    }

    public List<DomainModelPropertyConfig> getAttribute() {
        return attribute;
    }
    public void setAttribute(List<DomainModelPropertyConfig> attributeList) {

        for(DomainModelPropertyConfig propertyConfig : attributeList ) {

            if (propertyConfig.getPrimaryKey()) {
                this.primaryKey = propertyConfig;
                continue;
            }

            if (propertyConfig.getVisible()) {
                visiblePropertyList.add(propertyConfig);
            }

            if((propertyConfig.getViewConfig() != null && (propertyConfig.getViewConfig().getSearchCondition()
            ))){
                searchPropertyList.add(propertyConfig);
            }

            if((propertyConfig.getViewConfig() != null && (propertyConfig.getViewConfig().getAddable()
                    ||propertyConfig.getViewConfig().getAddRequired()
                    ||propertyConfig.getViewConfig().getAddViewable()
            ))){
                addablePropertyList.add(propertyConfig);
            }

            if((propertyConfig.getViewConfig() != null && (propertyConfig.getViewConfig().getEditable()
                    ||propertyConfig.getViewConfig().getEditRequired()
                    ||propertyConfig.getViewConfig().getEditViewable()
            ))||propertyConfig.getEditable()){
                editablePropertyList.add(propertyConfig);
            }

            if(propertyConfig.getViewConfig() != null && propertyConfig.getViewConfig().getGirdColumn()) {
                gridPropertyList.add(propertyConfig);
            }
            if(propertyConfig.getViewConfig() != null && propertyConfig.getViewConfig().getSimpleSearchCondition()) {
                simpleConditionList.add(propertyConfig);
            }
//            if(propertyConfig.getViewConfig() != null && propertyConfig.getViewConfig().getSearchCondition()) {
//                searchCondition.add(propertyConfig);
//            }

            if (propertyConfig.getRequired()){
                requiredPropertyList.add(propertyConfig);
            }

            if (propertyConfig.getPersist()){
                persistPropertyList.add(propertyConfig);
            }

            this.attribute.add(propertyConfig);

            //如果属性是引用, 则将属性加入引用
            if(propertyConfig.isReferenceFlag()){
                this.addReference(propertyConfig.getReference());
                this.referenceProperty.add(propertyConfig);
            }

        }


    }

    public List<DomainModelPropertyConfig> getDbColumn() {
        return visiblePropertyList;
    }

    /**
     * 获取小字段(非超长字符串的字段)
     * @return
     */
    public List<DomainModelPropertyConfig> getSmallAttribute(){
        List<DomainModelPropertyConfig> sc = new ArrayList<>();
        for (DomainModelPropertyConfig attr : attribute){
            if("Text".equalsIgnoreCase(attr.getType())){
                continue;
            }

            sc.add(attr);

        }
        return sc;
    }
    public List<DomainModelPropertyConfig> getTextAttribute(){
        List<DomainModelPropertyConfig> sc = new ArrayList<>();
        for (DomainModelPropertyConfig attr:attribute){
            if("Text".equalsIgnoreCase(attr.getType())){
                sc.add(attr);
            }
        }
        return sc;
    }
    public boolean hashReferenceProperty(){
        for (DomainModelPropertyConfig attr:attribute){
            if(attr.isReferenceFlag()){
                return true;
            }
        }
        return false;
    }

    public List<DomainModelPropertyConfig> getUiColumn() {
        List<DomainModelPropertyConfig> sc = new ArrayList<>();
        for (DomainModelPropertyConfig attr : attribute){

            //主引用关联不展示
            if(attr.getReferencePropertyCode() == null && attr.isReferenceFlag()) {
                continue;
            }
            if(attr.getVisible() ){
                sc.add(attr);
            }
        }
        return sc;
    }

    public List<DomainModelConfig> getChildModelList() {
        return childModelList;
    }
    public void setChildModelList(List<DomainModelConfig> childModelList) {
        this.childModelList = childModelList;
    }
    public boolean hasChildModel(){
        return CollectionUtils.isNotEmpty(this.childModelList);
    }

    public DomainModelConfig getParentModel() {
        return parentModel;
    }
    public void setParentModel(DomainModelConfig parentModel) {
        this.parentModel = parentModel;
    }

    public boolean isRootModel() {
		return rootModel;
	}
	public void setRootModel(boolean rootModel) {
		this.rootModel = rootModel;
	}

    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DomainModelPropertyConfig getPrimaryKey() {
        return primaryKey;
    }
    public void setPrimaryKey(DomainModelPropertyConfig primaryKey) {
        this.primaryKey = primaryKey;
    }


    public List<DomainModelPropertyConfig> getSearchPropertyList() {
        return searchPropertyList;
    }
    public void setSearchPropertyList(List<DomainModelPropertyConfig> searchPropertyList) {
        this.searchPropertyList = searchPropertyList;
    }

    public List<DomainModelPropertyConfig> getVisiblePropertyList() {
        return visiblePropertyList;
    }

    public List<DomainModelPropertyConfig> getAddablePropertyList() {
        return addablePropertyList;
    }
    public void setAddablePropertyList(List<DomainModelPropertyConfig> addablePropertyList) {
        this.addablePropertyList = addablePropertyList;
    }

    public void setVisiblePropertyList(List<DomainModelPropertyConfig> visiblePropertyList) {
        this.visiblePropertyList = visiblePropertyList;
    }
    public List<DomainModelPropertyConfig> getEditablePropertyList() {
        return editablePropertyList;
    }

    public void setEditablePropertyList(List<DomainModelPropertyConfig> editablePropertyList) {
        this.editablePropertyList = editablePropertyList;
    }

    public List<DomainModelPropertyConfig> getRequiredPropertyList() {
        return requiredPropertyList;
    }
    public void setRequiredPropertyList(List<DomainModelPropertyConfig> requiredPropertyList) {
        this.requiredPropertyList = requiredPropertyList;
    }

    public List<DomainModelPropertyConfig> getPersistPropertyList() {
        return persistPropertyList;
    }
    public void setPersistPropertyList(List<DomainModelPropertyConfig> persistPropertyList) {
        this.persistPropertyList = persistPropertyList;
    }

    public List<DomainModelPropertyConfig> getReferenceProperty() {
        return referenceProperty;
    }

    public List<DomainModelPropertyConfig> getSimpleConditionList() {
        return simpleConditionList;
    }

    public List<DomainModelPropertyConfig> getGridPropertyList() {
        return gridPropertyList;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
