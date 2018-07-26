Ext.define('AM.model.deploy.ops.DevpOpsElementInfo', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'ops/devpOpsElementInfo'
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
            name: 'code'
            ,type:'string'
            ,allowNull:false
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
            name: 'elmRid'
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
            name: 'seq'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoCode1'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoValue1'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoCode2'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoValue2'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoCode3'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoValue3'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoCode4'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoValue4'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoCode5'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoValue5'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'notes'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'parasCode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});