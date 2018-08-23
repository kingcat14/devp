Ext.define('AM.model.devp.publish.DevpSysOpsTaskDeploy', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'devp/publish/devpsysopstaskdeploy'
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
            name: 'deployPath'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'backupPath'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'preAction'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'deployAction'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'postAction'
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
            name: 'notes'
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
            name: 'schemeRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'cmpRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'taskRid'
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
            name: 'createUcode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'createUname'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'modifyUcode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'modifyUname'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});