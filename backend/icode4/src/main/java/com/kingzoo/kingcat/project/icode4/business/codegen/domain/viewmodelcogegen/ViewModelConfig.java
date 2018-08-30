package com.kingzoo.kingcat.project.icode4.business.codegen.domain.viewmodelcogegen;

import com.kingzoo.kingcat.project.icode4.business.codegen.domain.ModelConfig;
import com.kingzoo.kingcat.project.icode4.business.system.cogegen.entity.DomainModelConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gonghongrui on 14/11/4.
 */
public class ViewModelConfig extends ModelConfig {


    /**
     * 备注
     *
     */
    private String memo;

    /**
     * 描述
     *
     */
    private String description;

    /**
     * 展现排序
     *
     */
    private Integer viewIndex;

    /**
     * 所属领域对象
     *
     */
    private DomainModelConfig domainModel;


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getViewIndex() {
        return viewIndex;
    }

    public void setViewIndex(Integer viewIndex) {
        this.viewIndex = viewIndex;
    }

    public DomainModelConfig getDomainModel() {
        return domainModel;
    }

    public void setDomainModel(DomainModelConfig domainModel) {
        this.domainModel = domainModel;
    }

    @Override
    public String getModelType() {
        return "VO";
    }

    private ViewPropertyConfigGroup domainModelPropertyGroup = new ViewPropertyConfigGroup();

    private List<ViewModelPropertyConfig> attribute = new ArrayList<>();

    private List<ViewModelPropertyConfig> referenceProperty = new ArrayList<>();
    private List<DomainModelConfig> referenceDomainModel = new ArrayList<>();

    private List<ViewModelPropertyConfig> searchCondition = new ArrayList<>();

    private List<ViewModelPropertyConfig> quickSearchCondition = new ArrayList<>();


    private List<ViewModelPropertyConfig> propertyGroupList = new ArrayList<>();


    public List<ViewModelPropertyConfig> getAttribute() {
        return attribute;
    }

    public List<ViewModelPropertyConfig> getReferenceProperty(){
        return referenceProperty;
    }


    public void setAttribute(List<ViewModelPropertyConfig> attribute) {

        this.attribute = new ArrayList<>();


        Set<String> referenceDomainModelId = new HashSet<>();

        for(ViewModelPropertyConfig property : attribute){
//            if(property.getReference()){
//                referenceProperty.add(property);
//
//            }

            //所属属性组为空,且关联到核心对象，则是核心对象的属性
            if(StringUtils.isEmpty(property.getPropertyGroup()) && property.getDomainModel().getId().equals(this.getDomainModel().getId())){
                domainModelPropertyGroup.addProperty(property);
            }
            if(property.getCoreRelation()){
                domainModelPropertyGroup.addProperty(property);
            }


            if(property.getSearchCondition()){
                searchCondition.add(property);
            }
            if(property.getQuickSearchCondition()){
                quickSearchCondition.add(property);
            }

            //得到所有属性引用的领域对象
            if(property.getDomainModel() != null && !referenceDomainModelId.contains(property.getDomainModel().getId())){
                referenceDomainModelId.add(property.getDomainModel().getId());
                referenceDomainModel.add(property.getDomainModel());
            }

            /*处理属性组*/
            if(property.getCoreRelation()){
                propertyGroupList.add(property);
            }
        }
    }

    public List<ViewModelPropertyConfig> getSearchCondition() {

        return searchCondition;
    }

    public List<ViewModelPropertyConfig> getQuickSearchCondition() {

        return quickSearchCondition;
    }


    public List<ViewModelPropertyConfig> getGridColumn() {
        List<ViewModelPropertyConfig> sc = new ArrayList<>();
        for (ViewModelPropertyConfig attr:attribute){
            if(!attr.getGridHidden()){
                sc.add(attr);
            }
        }
        return sc;
    }
    public List<ViewModelPropertyConfig> getFormField() {
        List<ViewModelPropertyConfig> sc = new ArrayList<>();
        for (ViewModelPropertyConfig attr:attribute){
            if(!attr.getFormHidden()){
                sc.add(attr);
            }
        }
        return sc;
    }

    public List<ViewModelPropertyConfig> getTextAttribute(){
        List<ViewModelPropertyConfig> sc = new ArrayList<>();
        for (ViewModelPropertyConfig attr:attribute){
            if("Text".equalsIgnoreCase(attr.getType())){
                sc.add(attr);
            }
        }
        return sc;
    }

    public boolean hasReferenceProperty(){
        for (ViewModelPropertyConfig attr:attribute){
            if(attr.getReference()){
                return true;
            }
        }
        return false;
    }

    public List<DomainModelConfig> getReferenceDomainModel() {
        return referenceDomainModel;
    }

    public void setReferenceDomainModel(List<DomainModelConfig> referenceDomainModel) {
        this.referenceDomainModel = referenceDomainModel;
    }

    public List<ViewModelPropertyConfig> getPropertyGroupList() {
        return propertyGroupList;
    }

    public void setPropertyGroupList(List<ViewModelPropertyConfig> propertyGroupList) {
        this.propertyGroupList = propertyGroupList;
    }

    public ViewPropertyConfigGroup getDomainModelPropertyGroup() {
        return domainModelPropertyGroup;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
