Ext.define('AM.model.speedcloud.pipeline.template.PipelineTemplateTask', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/pipeline/template/pipelinetemplatetask'
        ,listeners: {
            exception: {
                fn:  function(server, response, operation, options) {
                    var errorBody = Ext.decode(response.responseText);
                    Ext.MessageBox.alert('操作失败('+errorBody.code+')', errorBody.message);
                }
            }
        }
    },
    fields: [
    	{
            name: 'id'
            ,type:'string'
            ,allowNull:true
        }
    	,{
            name: 'tid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'name'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'taskType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'execType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'taskStartTime'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'taskDayOfWeeks'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'description'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});