Ext.define('AM.model.deploy.ops.DevpOpsIssue', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'ops/devpOpsIssue'
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
            name: 'type'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'issue'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'reply'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'status'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'hasAttachment'
            ,type:'int'
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
            name: 'nexusVersion'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'nexusPhase'
            ,type:'string'
            ,allowNull:true
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