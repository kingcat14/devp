Ext.define('AM.model.maintenance.hardware.NetworkDevice', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'hardware/networkDevice'
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
            name: 'etype'
            ,type:'string'
            ,allowNull:true
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
            name: 'hardwareModel'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'softwareModel'
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
            name: 'createDate'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'expireDate'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'assetProject'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'assetArea'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'assetLocation'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'intAccessAddr'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'extAccessAddr'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'acquisitionMode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'acquisitionDesc'
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
            name: 'notes'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'prdRid'
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
    	,{
            name: 'attachment'
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