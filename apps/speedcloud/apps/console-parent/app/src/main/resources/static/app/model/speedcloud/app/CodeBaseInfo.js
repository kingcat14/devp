Ext.define('AM.model.speedcloud.app.CodeBaseInfo', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/app/codebaseinfo'
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
            name: 'codeRepertory'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'language'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'languageLevel'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});