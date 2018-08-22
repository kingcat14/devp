Ext.define('AM.model.devp.ops.DevpOpsAttachment', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'ops/devpOpsAttachment'
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
            name: 'etype'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'code'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'name'
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
            name: 'typeCode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'typeName'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'fileType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'accessType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'domain'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'address'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'fileVersion'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'nexusType'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'nexusRid'
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
    	,{
            name: 'cmodifyUcode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});