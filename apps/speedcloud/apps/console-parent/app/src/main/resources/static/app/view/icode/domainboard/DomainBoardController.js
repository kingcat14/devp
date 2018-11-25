Ext.define('AM.view.icode.domainboard.DomainBoardController', {
	extend: 'Ext.app.ViewController'
	,requires: [
        'AM.model.icode.project.Component'
	    ,'AM.model.icode.domain.Domain'
        ,'AM.model.icode.domain.Entity'
        ,'AM.model.icode.domain.EntityAction'
        ,'AM.model.icode.domain.EntityActionParameter'

	]
	,alias: 'controller.icode.domainboard.DomainBoardController'
	,reloadTree:function(){
		var me = this;

        var domainTreePanel = me.lookupReference('domainTreePanel')

        var selectedPath;
        var isExpanded = false;
        if(domainTreePanel.getSelectionModel( ).getLastSelected()) {
            selectedPath = domainTreePanel.getSelectionModel().getLastSelected().getPath();
            isExpanded = domainTreePanel.getSelectionModel().getLastSelected().isExpanded();
            console.log("selectedPath:" + selectedPath)
        }

        domainTreePanel.getView().getStore().load({
            callback:function(records, operation, success){
                if(success) {
                    //systemTreePanel.expandPath(path);
                    if(selectedPath){
                        //console.log("selectedPath:" + selectedPath)
                        domainTreePanel.selectPath(selectedPath);
                        if(isExpanded) {
                            domainTreePanel.expandPath(selectedPath);
                        }
                    }

                }
            }
        });
    }
    ,treeItemClick:function(view, record, item, index, event){
        var me = this;
        var type = record.get('type');
        var objectId = record.get('objectId');


        if("PRODUCT"==type) {
            //点击了模板集合
            this.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(0);
            AM.model.icode.project.Product.load(objectId, {
                success: function (loadedRecord, operation) {
                    me.lookupReference('domainBoardProductPanel').setModel(loadedRecord);
                }
            });
        }
        if("COMPONENT"==type) {
            //点击了组件
            this.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(1);
            AM.model.icode.project.Component.load(objectId, {
                success: function (loadedRecord, operation) {
                    me.lookupReference('domainBoardComponentPanel').setModel(loadedRecord);
                }
            });
        }

        if("DOMAIN"==type) {
            //点击了领域
            this.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(2);

            AM.model.icode.domain.Domain.load(objectId, {
                success: function (loadedRecord, operation) {
                    me.lookupReference('domainBoardDomainPanel').setModel(loadedRecord);
                }
            });
        }

        if("ENTITY"==type) {

            //点击了实体
            this.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(3);

            var componentNode = record.parentNode;
            while(componentNode && componentNode.get('type')!='COMPONENT'){
                componentNode = componentNode.parentNode;
            }

            var component = AM.model.icode.project.Component.create(componentNode.get('object'))

            AM.model.icode.domain.Entity.load(objectId, {
                success: function (loadedRecord, operation) {
                    me.lookupReference('domainBoardEntityPanel').getViewModel().set('component', component);
                    me.lookupReference('domainBoardEntityPanel').setModel(loadedRecord);
                }
            });
        }
        if("ENTITY_ACTION"==type) {
            //点击了模板集合
            this.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(4);

            AM.model.icode.domain.EntityAction.load(objectId, {
                success: function (loadedRecord, operation) {
                    me.lookupReference('domainBoardEntityActionPanel').setModel(loadedRecord);
                }
            });
        }
        if("PARAMETER"==type) {
            //点击了模板集合
            this.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(5);

            AM.model.icode.domain.EntityActionParameter.load(objectId, {
                success: function (loadedRecord, operation) {
                    me.lookupReference('entityActionParameterPanel').setModel(loadedRecord);
                }
            });
        }

    }
    ,treeItemContextMenu: function(view, record, node, index, e) {

        var me = this;

        e.stopEvent();
        // console.log(e.getPositioning())
        var type = record.get('type');
        console.log(type)
        if('PRODUCT'==type){
            me.getView().contextMenu.product.showAt(e.getXY());
        }else if('COMPONENT'==type){
            me.getView().contextMenu.component.showAt(e.getXY());
        }else if('DOMAIN'==type){
            me.getView().contextMenu.domain.showAt(e.getXY());
        }else if('ENTITY'==type){
            me.getView().contextMenu.entity.showAt(e.getXY());
        }
        else if('ENTITY_ACTION'==type){
            me.getView().contextMenu.entityAction.showAt(e.getXY());
        }


        return true;
    }

    ,addDomainAction:function () {
		var me = this;
        var treePanel = me.lookupReference('domainTreePanel');
        var record = treePanel.getSelectionModel().getSelection()[0];
        if (!record) {
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }
        //默认点的是COMPONENT节点
        var componentId = record.get('objectId');
        var parentDomainId = null;
        if('DOMAIN' == record.get('type')){
            componentId = null;
            parentDomainId = record.get('objectId');
        }

        var domainRecord = AM.model.icode.domain.Domain.create({parent:parentDomainId, component:componentId});
        var domainBoardDomainPanel = me.lookupReference('domainBoardDomainPanel');
        domainBoardDomainPanel.setTitle("领域信息");
        domainBoardDomainPanel.setModel(domainRecord);
        domainBoardDomainPanel.getViewModel().set('componentId', componentId);
        me.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(domainBoardDomainPanel);
    }

    ,addEntityAction:function(){
	    var me = this;
        var treePanel = me.lookupReference('domainTreePanel');
        var record = treePanel.getSelection()[0];
        if (!record) {
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        if("DOMAIN" != record.get('type') ){

            Ext.Msg.show({title: '操作失败', msg: '请选择模块', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;

        }

        var componentNode = record.parentNode;
        while(componentNode && componentNode.get('type')!='COMPONENT'){
            componentNode = componentNode.parentNode;
        }
        var component = AM.model.icode.project.Component.create(componentNode.get('object'))
        console.log('component:')
        console.log(component)
        var domainId = record.get('objectId');

        var entityRecord = AM.model.icode.domain.Entity.create({"domain" : domainId, persist:true});

        var domainBoardEntityPanel = me.lookupReference('domainBoardEntityPanel')
        domainBoardEntityPanel.setTitle("新增领域对象");
        domainBoardEntityPanel.getViewModel().set('component', component);
        domainBoardEntityPanel.setModel(entityRecord);




        me.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(domainBoardEntityPanel);
    }
    ,addEntityActionAction:function(){
        var me = this;
        var treePanel = me.lookupReference('domainTreePanel');
        var record = treePanel.getSelection()[0];
        if (!record) {
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        if("ENTITY" != record.get('type') ){

            Ext.Msg.show({title: '操作失败', msg: '请选择对象', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;

        }

        var entityId = record.get('objectId');

        var entityActionRecord = AM.model.icode.domain.EntityAction.create({"entity" : entityId});

        var domainBoardEntityActionPanel = me.lookupReference('domainBoardEntityActionPanel')
        domainBoardEntityActionPanel.setTitle("新增领域服务");
        domainBoardEntityActionPanel.setModel(entityActionRecord);

        me.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(domainBoardEntityActionPanel);
    }
    ,setEntityActionParamAction:function(type){
        var me = this;
        var treePanel = me.lookupReference('domainTreePanel');
        var record = treePanel.getSelection()[0];
        if (!record) {
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        if("ENTITY_ACTION" != record.get('type') ){

            Ext.Msg.show({title: '操作失败', msg: '请选择行为', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;

        }

        var actionId = record.get('objectId');

        var entityAction = AM.model.icode.domain.EntityAction.create(record.get('object'));

        var entityActionParameter = AM.model.icode.domain.EntityActionParameter.create({"entityAction" : actionId});
        var entityActionParameterPanel = me.lookupReference('entityActionParameterPanel')
        entityActionParameterPanel.getViewModel().set('type', type);
        entityActionParameterPanel.getViewModel().set('entityAction', entityAction);
        entityActionParameterPanel.setModel(entityActionParameter);
        me.lookupReference('domainBoardContentPanel').getLayout().setActiveItem(entityActionParameterPanel);

        var parameterId = record.get('object').request;
        if(type == 'OUT'){
            parameterId = record.get('object').response;
        }
        console.log('parameterId:'+parameterId)
        //已经有了该对象
        if(parameterId){
            entityActionParameter = AM.model.icode.domain.EntityActionParameter.load(parameterId,{
                success: function (loadedRecord, operation) {
                    entityActionParameterPanel.setModel(entityActionParameter);
                }
            })
        }

    }
})