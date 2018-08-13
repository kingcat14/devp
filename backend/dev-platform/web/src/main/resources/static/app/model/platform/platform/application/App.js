Ext.define('AM.model.platform.platform.application.App', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'platform/application/app'
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
            name: 'appCategoryId'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'label'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'enable'
            ,type:'bool'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'onBoardTime'
            ,type:'date'
            ,dateFormat: 'time'
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
            name: 'description'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'visible'
            ,type:'bool'
            ,allowNull:true
            ,critical:true
        }
    ]
});