Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskAddPanelController', {
	extend: 'Ext.app.ViewController',
	requires: [
        'AM.model.speedcloud.pipeline.task.PipelineTaskParam'
	]
	,alias: 'controller.speedcloud.pipeline.task.PipelineTaskAddPanelController'


    ,onAddTaskParamClick:function(){
        var modelConfig = {}
        var taskParam = Ext.create('AM.model.speedcloud.pipeline.task.PipelineTaskParam', modelConfig);
        this.lookup('taskParamGrid').getStore().add(taskParam);

    }
    ,onDeleteTaskParamClick:function (grid, rowIndex, colIndex){
        grid.getStore().getAt(rowIndex).drop();
    }


    ,onTaskSaveButtonClick:function(){

	    var me = this;
        var pipelineTask = Ext.create('AM.model.speedcloud.pipeline.task.PipelineTask');
	    this.lookup("taskFormPanel").updateRecord(pipelineTask);

	    console.log(pipelineTask)
        if (!this.lookup("taskFormPanel").getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }
        if(Ext.isEmpty(pipelineTask.get('name')) || pipelineTask.get('name') == "_"){
            this.lookup("nameField").focus();
            Ext.MessageBox.alert('提交失败', '请填写任务名称');
            return;
        }

        if(pipelineTask.get('execType') == 'DAILY'){
            if(Ext.isEmpty(pipelineTask.get('taskStartTime'))){
                Ext.MessageBox.alert('提交失败', '执行时间必填');
                return;
            }
        }
        if(pipelineTask.get('execType') == 'WEEKLY'){
            if(Ext.isEmpty(pipelineTask.get('taskDayOfWeeks'))){
                Ext.MessageBox.alert('提交失败', '执行日必填');
                return;
            }
            if(Ext.isEmpty(pipelineTask.get('taskStartTime'))){
                Ext.MessageBox.alert('提交失败', '执行时间必填');
                return;
            }
        }

        var taskStartTimeValue = me.lookup('taskStartTimeField').getValue();
        var taskStartTime = Ext.Date.format(taskStartTimeValue, 'H:i');
        pipelineTask.set('taskStartTime', taskStartTime)

        //开始处理任务包含的操作
        var actionList = [];
        var actionStore = this.lookup('actionGrid').getStore();
        var i = 0;
        actionStore.each(function(action){
            action.set('execIndex', i++)
            actionList.push(action.getData())
        })

        if(actionList == null || actionList.length == 0){
            Ext.MessageBox.alert('提交失败', '任务必须至少包含一个操作');
            return;
        }

        console.log(pipelineTask);
        pipelineTask.set('actions', actionList);
        console.log(Ext.encode(pipelineTask))



        pipelineTask.save({
            success: function () {
                Ext.MsgUtil.show('操作成功', '保存任务成功!');
                me.hide();
            }
        });

    }
})