Ext.define('AM.model.monitor.app.Application', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        // ,headers:{"Accept":"application/json"}
        ,paramsAsJson:true
        ,url: 'monitor/app/application'
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
            name: 'name'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'code'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'totalCount'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'aliveCount'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'alarm'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'enable'
            ,type:'bool'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'thresholdValue'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'status'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});