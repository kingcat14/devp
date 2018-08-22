Ext.define('AM.model.devp.ops.DevpOpsCiGroup', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'ops/devpOpsCiGroup'
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
            name: 'groupRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'ciRid'
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
            name: 'parasCode'
            ,type:'string'
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