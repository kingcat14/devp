Ext.define('AM.model.platform.platform.application.ApplicationPassword', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'platform/application/applicationPassword'
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
            name: 'appId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'accessId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'accessKey'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    ]
});