Ext.define('AM.model.speedcloud.pipeline.PipelineStageSelectableNodeParam', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/pipeline/pipelinestageselectablenodeparam'
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
            name: 'nodeId'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'nodeType'
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
    ]
});