Ext.define('AM.view.speedcloud.app.AppBaseInfoController', {
	extend: 'Ext.app.ViewController'
	,requires: [
	    'AM.model.speedcloud.app.AppBaseInfo'
        ,'AM.model.speedcloud.app.CodeRepository'
        ,'AM.model.speedcloud.app.AppDevelopConfig'
	]
	,alias: 'controller.speedcloud.app.AppBaseInfoController'

    ,baseInfoSaved:function(appBaseInfo ,b ,c){
        var appBaseInfoStore = this.getViewModel().getStore('store');
        if(!appBaseInfoStore.getById(appBaseInfo.getId())){
            appBaseInfoStore.add(appBaseInfo);
            this.lookupReference('mainGridPanel').setSelection(appBaseInfo)
        }
    }
    ,productChange:function(combo, newValue){
	    var appBaseInfoStore = this.getViewModel().getStore('store')
        appBaseInfoStore.applyCondition({project:newValue}).load()
    }

    ,onAppRowSelect:function(table, record, rowIndex, event) {
        var me = this;
        me.lookupReference("detailPanel").setModel(record);

        var appBaseInfo = AM.model.speedcloud.app.AppBaseInfo.load(record.getId(), {
            success: function (loadedRecord, operation) {

                // if(loadedRecord.codeRepositoryVO) {
                //     var codeRepository = Ext.create('AM.model.speedcloud.app.CodeRepository', loadedRecord.codeRepositoryVO);
                //     this.lookupReference("codeRepositoryEditPanel").setModel(codeRepository);
                // }

                var codeRepository = Ext.create('AM.model.speedcloud.app.CodeRepository', loadedRecord.get('codeRepositoryVO'));
                codeRepository.set('app', record.getId())
                me.lookupReference("codeRepositoryEditPanel").setModel(codeRepository);

                var appDevelopConfig = Ext.create('AM.model.speedcloud.app.AppDevelopConfig', loadedRecord.get('appDevelopConfigVO'));
                me.lookupReference("appDevelopConfigEditPanel").setModel(appDevelopConfig);

            }
            ,failure: function (loadedRecord, operation) {}
            ,callback: function (loadedRecord, operation) {}
        })
    }
    ,onAddButtonClick: function() {
        var currentProject = this.lookupReference('currentProject').getValue();
        if(!currentProject){
            Ext.MessageBox.show({title: '请先选择产品', msg: '请先选择产品', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            this.lookupReference('currentProject').expand();
            return;
        }
        var modelConfig = {project:currentProject};
        var record = Ext.create('AM.model.speedcloud.app.AppBaseInfo', modelConfig);

        this.lookupReference("detailPanel").setModel(record);
        this.lookupReference("codeRepositoryEditPanel").setModel(null);
        this.lookupReference("appDevelopConfigEditPanel").setModel(null);
    }
    ,onDeleteButtonClick: function(button, e, options) {
        var me = this;
        var mainGridPanel = me.lookupReference('mainGridPanel');
        var store = mainGridPanel.getStore();
        var selections = mainGridPanel.getSelectionModel( ).getSelection( );
        store.remove(selections);
        store.sync({
            success:function(batch,options){

		        var count = store.getCount();

		        var targetPage = count<=0 ? store.currentPage-1 : store.currentPage;
		        targetPage = targetPage <=0 ? 1 :targetPage;
                store.loadPage(targetPage,{
                    scope: this
                    ,callback: function(records, operation, success) {
                        if(!success)
                        	Ext.MessageBox.show({title: '操作失败', msg: '重新加载数据失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                        else {
                            Ext.MsgUtil.notification('操作成功', '删除应用（系统）成功!');
                            me.lookupReference("detailPanel").setModel(null);
                            me.lookupReference("codeRepositoryEditPanel").setModel(null);
                            me.lookupReference("appDevelopConfigEditPanel").setModel(null);
                        }
                    }
                });
            }
            ,scope:me.getStore()
        });
    }
	,onEditButtonClick: function(){
        var me = this;
        var mainGridPanel = me.lookupReference('mainGridPanel');
        var selections = mainGridPanel.getSelectionModel( ).getSelection( );
        if(selections.length <= 0){
            Ext.MessageBox.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }
        var record = selections[0];
        me.showEditWindow(record, mainGridPanel.getView().getRow(record));
    }
    ,reloadStore:function () {

        var me = this;

        var mainGridPanel = me.lookupReference('mainGridPanel');

        mainGridPanel.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.notification('操作成功', '同步列表成功');
                }
            }
        });
    }

})