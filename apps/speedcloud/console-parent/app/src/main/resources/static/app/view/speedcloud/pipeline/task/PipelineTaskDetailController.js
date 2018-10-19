Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskDetailController', {
	extend: 'Ext.app.ViewController',
	requires: [
        ,'AM.view.speedcloud.pipeline.exec.PipelineExecNodeLogWindow'
	]
	,alias: 'controller.speedcloud.pipeline.task.PipelineTaskDetailController'

    ,onViewExecNodeLogClick:function (view, rowIndex, colIndex, item, e, record) {
        var execNodeId = record.getId()
        console.log('execNodeId:' + record.getId())

        var aa = AM.model.speedcloud.pipeline.exec.PipelineExecNodeLog.load(execNodeId, {
            success: function (record, operation) {
                console.log(record)

                var logPanel = Ext.create('AM.view.speedcloud.pipeline.exec.PipelineExecNodeLogWindow', {viewModel:{data:{record:record}}, frame:true});
                // logPanel.setModel(record);
                logPanel.show();
            }
        });

    }

})