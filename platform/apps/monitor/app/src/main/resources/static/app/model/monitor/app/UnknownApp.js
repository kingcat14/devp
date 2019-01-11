Ext.define('AM.model.monitor.app.UnknownApp', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'monitor/app/unknownapp'
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
            name: 'code'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'registerTime'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'alive'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'aliveCount'
            ,type:'int'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'maxCount'
            ,type:'int'
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
            name: 'ignored'
            ,type:'bool'
            ,allowNull:true
            ,critical:true
        }
    ]
});