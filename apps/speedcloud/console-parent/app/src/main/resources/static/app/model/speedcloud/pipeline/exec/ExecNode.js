Ext.define('AM.model.speedcloud.pipeline.exec.ExecNode', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/pipeline/exec/execnode'
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
            name: 'code'
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
            name: 'nodeType'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'execMode'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'status'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'result'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'exec'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'resultMessage'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'startTime'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'parentId'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'task'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'autoStart'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'execIndex'
            ,type:'int'
            ,allowNull:false
            ,critical:true
        }
    ]
});