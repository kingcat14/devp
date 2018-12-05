Ext.define('AM.model.speedcloud.config.CodeRepositoryType', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/config/coderepositorytype'
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
    ]
});