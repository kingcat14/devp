Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskController', {
	extend: 'Ext.app.ViewController',
	requires: [
        'AM.view.speedcloud.pipeline.task.PipelineTaskEditPanel'
        ,'AM.view.speedcloud.pipeline.task.PipelineTaskDetailPanel'
        ,'AM.model.speedcloud.pipeline.exec.PipelineTaskExecInstance'
	]
	,alias: 'controller.speedcloud.pipeline.task.PipelineTaskController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;
        me.createTaskTabOnMainContenPanel(record, record.get(id));
	}
    ,onAddButtonClick: function() {

        var modelConfig = {name:'_新增', execType:'MANUAL'}

        var record = Ext.create('AM.model.speedcloud.pipeline.task.PipelineTask', modelConfig);
        this.createTaskTabOnMainContenPanel(record, Ext.id());

	}
    /**在系统主面板创建Tab页*/
    ,createTaskTabOnMainContenPanel:function(record, referenceId){
        this.fireViewEvent('createMainTabPanel', this.getView()
            ,{
                xtype:'speedcloud.pipeline.task.PipelineTaskEditPanel'
                , reference:'PipelineTaskEditPanel_'+referenceId
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
                        actionStore:Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskActionStore').applyCondition({task:Ext.isNumeric(record.get('id'))?record.get('id')+"":-999}).load()
                        ,paramStore:Ext.create('AM.store.speedcloud.pipeline.task.PipelineTaskParamStore').applyCondition({task:Ext.isNumeric(record.get('id'))?record.get('id')+"":-999}).load()
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
    ,onDetailButtonClick: function(){
        var me = this;
        var mainGridPanel = me.lookupReference('mainGridPanel');
        var selections = mainGridPanel.getSelectionModel( ).getSelection( );
        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }
        var record = selections[0];

        this.fireViewEvent('createMainTabPanel', this.getView()
            ,{
                xtype: 'speedcloud.pipeline.task.PipelineTaskDetailPanel'
                ,reference:'PipelineTaskDetailPanel_'+record.getId()
                , viewModel: {
                    data: {record: record}
                    ,stores:{
                        execInstanceStore:Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecInstanceStore').applyCondition({executeTargetId:record.getId()}).load()
                        ,execNodeStore : Ext.create('AM.store.speedcloud.pipeline.exec.PipelineExecNodeStore').applyCondition({relationObjId:record.getId()}).load()
                    }
                }
            }
        )
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
        me.createTaskTabOnMainContenPanel(record, record.get(id));
    }
    ,onExecButtonClick: function(){
	    //执行task
        var me = this;
        var mainGridPanel = me.lookupReference('mainGridPanel');
        var selections = mainGridPanel.getSelectionModel( ).getSelection( );
        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }
        var record = selections[0];

        //创建一个执行对象
        var taskExec = me.createTaskExec(record.get('id'));
        taskExec.save()
    }
    ,createTaskExec:function(taskId){
        /** 创建定制化执行实例*/
        var me = this;

        //1.
        var taskExec = AM.model.speedcloud.pipeline.exec.PipelineTaskExecInstance.create({nodeId:taskId, nodeType:'TASK', subNodeList:[]});

        //TODO 处理添加执行参数

        return taskExec;

    }
    ,onSimpleSearchButtonClick: function(button, e, options) {
        var me = this;
        var searchWindow = me.lookupReference('mainSearchWindow');
        searchWindow.onSearchButtonClick();

    }
    ,onExportButtonClick: function(button, e, options) {

        var me = this;
        var searchWindow = me.lookupReference('mainSearchWindow');
        var condition = searchWindow.getCondition();
        if(!condition){
            condition = {searchCondition:{}};
        }
        if (!Ext.fly('formFly')) {
            var frm = document.createElement('form');
            frm.id = 'formFly';
            frm.className = 'x-hidden';
            document.body.appendChild(frm);
        }
        console.log(condition)
        Ext.Ajax.request({
            disableCaching: true
            ,url: "pipeline/task/pipelineTask/export"
            ,method: "POST"
            ,async: false  //ASYNC 是否异步( TRUE 异步 , FALSE 同步)
            ,params:condition
            ,isUpload: true
            ,form: Ext.fly('formFly')
        });

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