Ext.define('AM.model.asset.asset.info.AssetOwnership', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'asset/asset/info/assetownership'
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
            name: 'category'
            ,type:'string'
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
            name: 'assetDept'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'assetManager'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'useDept'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'useManager'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'opsDept'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'opsManager'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'bizLine'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'bizManager'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'goliveDate'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'majorCust'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'custManager'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'custUsage'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'acquisitionProvider'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});