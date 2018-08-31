Ext.define('AM.model.speedcloud.pipeline.PipelineTask', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/pipeline/pipelinetask'
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
            name: 'stage'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'execOrder'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'taskType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});