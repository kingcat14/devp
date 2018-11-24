Ext.define('AM.model.icode.project.ComponentLocalLocation', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'icode/project/componentlocallocation'
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
            name: 'accountId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'component'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'location'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});