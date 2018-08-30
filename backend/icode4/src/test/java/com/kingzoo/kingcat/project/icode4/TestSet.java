package com.kingzoo.kingcat.project.icode4;

import com.kingzoo.kingcat.project.icode4.business.system.dao.DomainModelPropertyDao;
import com.kingzoo.kingcat.project.icode4.business.system.dao.DomainModelViewPropertyDao;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelProperty;
import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModelViewProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Icode4Application.class)
public class TestSet {

    @Autowired
    @Qualifier("domainModelPropertyDao")
    private DomainModelPropertyDao domainModelPropertyDao;

    @Autowired
    @Qualifier("domainModelViewPropertyDao")
    private DomainModelViewPropertyDao domainModelViewPropertyDao;

    @Test
    public void createViewPropertyForOldProperty() {

        List<DomainModelProperty> all = domainModelPropertyDao.findAll();

        for(DomainModelProperty domainModelProperty : all){

            DomainModelViewProperty domainModelViewProperty = domainModelViewPropertyDao.findOne(domainModelProperty.getId());

            if(domainModelViewProperty == null) {

                domainModelViewProperty = new DomainModelViewProperty();

                //2.新增显示属性
                BeanUtils.copyProperties(domainModelProperty, domainModelViewProperty);
                domainModelViewProperty.setAddable(!domainModelProperty.getPrimaryKey());
                domainModelViewProperty.setAddRequired(false);
                domainModelViewProperty.setAddViewable(!domainModelProperty.getPrimaryKey());
                domainModelViewProperty.setEditable(!domainModelProperty.getPrimaryKey());
                domainModelViewProperty.setEditRequired(domainModelProperty.getPrimaryKey());
                domainModelViewProperty.setEditViewable(!domainModelProperty.getPrimaryKey());
                domainModelViewProperty.setSearchCondition(!domainModelProperty.getPrimaryKey());
                domainModelViewProperty.setSimpleSearchCondition(false);

                domainModelViewPropertyDao.insert(domainModelViewProperty);
            }
        }
    }



}
