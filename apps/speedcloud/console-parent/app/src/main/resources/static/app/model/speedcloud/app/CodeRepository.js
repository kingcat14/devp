Ext.define('AM.model.speedcloud.app.CodeRepository', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/app/coderepository'
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
            name: 'type'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'url'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'developType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'username'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'password'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});