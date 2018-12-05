Ext.define('AM.model.speedcloud.app.AppDevelopConfig', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/app/appdevelopconfig'
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
            name: 'app'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'developDatabase'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'developDomainName'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'testDatabase'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'testDomainName'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'productionDatabase'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'productionDomainName'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});