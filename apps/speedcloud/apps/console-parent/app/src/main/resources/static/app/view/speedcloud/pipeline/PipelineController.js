Ext.define('AM.view.speedcloud.pipeline.PipelineController', {
	extend: 'Ext.app.ViewController',
	requires: [
        'AM.view.speedcloud.pipeline.PipelineEditPanel'
        ,'AM.view.speedcloud.pipeline.PipelineExecPanel'
	]
	,alias: 'controller.speedcloud.pipeline.PipelineController'

	,onMainPanelRowClick:function(tablepanel, record, item, index, e, options) {
		//点击主数据的某行
		var me = this;

		var detailTabPanel = me.lookup('detailTabPanel');
		if(detailTabPanel) {
            detailTabPanel.expand();
        }


	}

    ,onAddButtonClick: function() {

        var modelConfig = {}
        var pipeline = Ext.create('AM.model.speedcloud.pipeline.Pipeline', modelConfig);
        console.log("phantom:"+pipeline.phantom)
        console.log(pipeline)
        this.createPipelineTabOnMainContenPanel(pipeline, Ext.id());
    }
    /**在系统主面板创建Tab页*/
    ,createPipelineTabOnMainContenPanel:function(record, referenceId){

        var me = this;

        this.fireViewEvent('createMainTabPanel', this.getView()
            ,{
                xtype:'speedcloud.pipeline.PipelineEditPanel'
                , reference:'PipelineEditPanel_'+referenceId
                , title:record.phantom?'新建流水线':'编辑流水线【'+record.get('name')+"】"
                , viewModel:{
                    data:{
                        record:record
                        ,"phantom":record.phantom
                    }
                    ,stores:{
                        paramStore:Ext.create('AM.store.speedcloud.pipeline.PipelineParamStore').applyCondition({pipeline:Ext.isNumeric(record.get('id'))?record.get('id')+"":-999}).load()
                        ,stageStore:Ext.create('AM.store.speedcloud.pipeline.PipelineStageStore').applyCondition({pipeline:Ext.isNumeric(record.get('id'))?record.get('id')+"":-999}).load()
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
                        	Ext.MsgUtil.show('操作成功','删除流水线成功!');
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
        console.log("phantom:"+record.phantom)
        this.createPipelineTabOnMainContenPanel(record, record.getId());
    }
    ,onExecButtonClick: function(){
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
                xtype:'speedcloud.pipeline.PipelineExecPanel'
                , reference:'PipelineExecPanel_'+record.getId()
                , title:'流水线详情【'+record.get('name')+"】"
                , viewModel:{
                    data:{
                        pipeline:record
                    }
                    ,stores:{
                        paramStore:Ext.create('AM.store.speedcloud.pipeline.PipelineParamStore').applyCondition({pipeline:record.getId()}).load()
                        ,stageStore:Ext.create('AM.store.speedcloud.pipeline.PipelineStageStore').applyCondition({pipeline:record.getId()}).load()
                    }
                }
            }
        );
    }
    ,onDirectExecButtonClick: function(){
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
        var taskExecConfig = {executeTargetId:record.get('id'), executeTargetType:'PIPELINE'}
        var taskExec = Ext.create('AM.model.speedcloud.pipeline.exec.PipelineExecInstance', taskExecConfig);
        taskExec.save()
    }
    ,onSimpleSearchButtonClick: function(button, e, options) {
        var me = this;
        var searchWindow = me.lookupReference('mainSearchWindow');
        searchWindow.onSearchButtonClick();
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