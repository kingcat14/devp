Ext.define('AM.model.speedcloud.pipeline.PipelineStageTaskRelation', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/pipeline/pipelinestagetaskrelation'
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
            name: 'stage'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'task'
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
    ]
});