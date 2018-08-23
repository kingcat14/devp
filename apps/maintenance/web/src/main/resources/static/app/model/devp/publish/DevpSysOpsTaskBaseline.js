Ext.define('AM.model.devp.publish.DevpSysOpsTaskBaseline', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'devp/publish/devpsysopstaskbaseline'
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
            name: 'cmSameas'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'cmType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'cmRepository'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'cmTag'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'cmUser'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'cmPassword'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'subDir'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'baseline'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'svnFromUrl'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'svnToUrl'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'operType'
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