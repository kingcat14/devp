Ext.define('AM.model.monitor.alarm.Alarm', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'monitor/alarm/alarm'
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
            name: 'app'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'counter'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'value'
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
            name: 'occurTime'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'lastOccurTime'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'disappearTime'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'occurCount'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'content'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});