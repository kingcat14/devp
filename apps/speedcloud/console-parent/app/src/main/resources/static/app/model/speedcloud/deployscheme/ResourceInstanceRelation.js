Ext.define('AM.model.speedcloud.deployscheme.ResourceInstanceRelation', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/deployscheme/resourceinstancerelation'
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
            name: 'type'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'status'
            ,type:'bool'
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
            name: 'seq'
            ,type:'int'
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
            name: 'scheme'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'resource'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'asset'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'assetCategoryCode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'assetTypeCode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});