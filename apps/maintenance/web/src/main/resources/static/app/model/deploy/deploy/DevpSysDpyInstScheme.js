Ext.define('AM.model.deploy.deploy.DevpSysDpyInstScheme', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'deploy/devpSysDpyInstScheme'
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
            name: 'rid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'tid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'etype'
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
            name: 'code'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'alias'
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
            name: 'recordState'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'type'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'subType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'stereotype'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'prdRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'resRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'instRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'schemeRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'seq'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    ]
});