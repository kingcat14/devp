Ext.define('AM.view.speedcloud.pipeline.template.PipelineTemplateTaskController', {
	extend: 'Ext.app.ViewController',
	requires: [
        'AM.view.speedcloud.pipeline.template.PipelineTemplateTaskEditPanel'
	]
	,alias: 'controller.speedcloud.pipeline.template.PipelineTemplateTaskController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;
        me.createTaskTabOnMainContenPanel(record, record.get(id));
	}
    ,onAddButtonClick: function() {

        var modelConfig = {name:'_新增', execType:'MANUAL'}

        var record = Ext.create('AM.model.speedcloud.pipeline.template.PipelineTemplateTask', modelConfig);
        this.createTaskTabOnMainContenPanel(record, Ext.id());

	}
    /**在系统主面板创建Tab页*/
    ,createTaskTabOnMainContenPanel:function(record, referenceId){
        this.fireViewEvent('createMainTabPanel', this.getView()
            ,{
                xtype:'speedcloud.pipeline.template.PipelineTemplateTaskEditPanel'
                , reference:'PipelineTemplateTaskEditPanel_'+referenceId
                // , title:record.get('name')
                , viewModel:{
                    data:{record:record, taskDayOfWeeksArray:{}, taskStartTime:{}}
                    ,formulas:{
                        'taskDayOfWeeksArray':{
                            get: function (get) {
                                var array = Ext.valueFrom(get('record.taskDayOfWeeks'),'').split(",");
                                for(var i in array){
                                    array[i] = Ext.Number.from(array[i], 999);
                                }
                                return {taskDayOfWeeks:array};
                            }
                            ,set: function (value) {
                                console.log(value)
                                var result = value.taskDayOfWeeks;
                                if(Ext.isArray(value.taskDayOfWeeks)){
                                    result = value.taskDayOfWeeks.join(",")
                                }
                                this.set('record.taskDayOfWeeks', result)
                            }
                        }
                        ,'taskStartTime':{
                            bind: '{record.taskStartTime}'
                            ,get: function (taskStartTime) {return taskStartTime;}
                            ,set: function (value) {
                                var taskStartTime = Ext.Date.format(value, 'H:i');
                                this.data.record.set('taskStartTime', taskStartTime);
                            }}
                    }
                    ,stores:{
                        // actionStore:Ext.create('AM.store.speedcloud.pipeline.template.PipelineTemplateTaskActionStore').applyCondition({task:Ext.isNumeric(record.get('id'))?record.get('id')+"":-999}).load()
                        // ,paramStore:Ext.create('AM.store.speedcloud.pipeline.template.PipelineTemplateTaskParamStore').applyCondition({task:Ext.isNumeric(record.get('id'))?record.get('id')+"":-999}).load()
                        actionStore:Ext.create('AM.store.speedcloud.pipeline.template.PipelineTemplateTaskActionStore').applyCondition({task:record.phantom?-999:record.get('id')}).load()
                        ,paramStore:Ext.create('AM.store.speedcloud.pipeline.template.PipelineTemplateTaskParamStore').applyCondition({task:record.phantom?-999:record.get('id')}).load()

                    }
                }
            }
        );
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
                    scope: this,
                    callback: function(records, operation, success) {
                        if(!success)
                        	Ext.Msg.show({title: '操作失败', msg: '重新加载数据失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
                        else
                        	Ext.MsgUtil.show('操作成功','删除任务成功!');
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
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }
        var record = selections[0];
        console.log(record)
        me.createTaskTabOnMainContenPanel(record, record.get(id));
    }

    ,onSimpleSearchButtonClick: function(button, e, options) {
        var me = this;
        var searchWindow = me.lookupReference('mainSearchWindow');
        searchWindow.onSearchButtonClick();

    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;
        var addWindow = me.lookupReference('mainAddWindow');
        addWindow.setModel(model);
        addWindow.show(targetComponent);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;
        var editWindow = me.lookupReference('mainEditWindow');
        editWindow.setModel(model);
        editWindow.show(targetComponent);
        return editWindow;
    }
    ,showSearchWindow: function() {
        var me = this;
        var searchWindow = me.lookupReference('mainSearchWindow');
        searchWindow.show();
    }
	,reloadStore:function () {

        var me = this;

        var mainGridPanel = me.lookupReference('mainGridPanel');

        mainGridPanel.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.show('操作成功', '同步列表成功');
                }
            }
        });
    }
    ,doSearch:function () {
        var me = this;
        var mainSearchWindow = me.lookupReference ('mainSearchWindow');
        var mainGridPanel = me.lookupReference('mainGridPanel');
        mainGridPanel.getStore().proxy.extraParams={searchCondition:mainSearchWindow.getCondition()};
        mainGridPanel.getStore().load({
            params:{
                start:0
                ,page:0
            }
        });
    }
})