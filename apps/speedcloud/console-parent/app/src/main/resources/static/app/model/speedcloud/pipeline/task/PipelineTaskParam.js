Ext.define('AM.model.speedcloud.pipeline.task.PipelineTaskParam', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/pipeline/task/pipelinetaskparam'
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
            name: 'taskType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'name'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'type'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'defaultValue'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'viewOrder'
            ,type:'int'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'description'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'deletable'
            ,type:'bool'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'enumValue'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'value'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});