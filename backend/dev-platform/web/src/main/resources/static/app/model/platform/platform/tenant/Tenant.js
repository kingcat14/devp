Ext.define('AM.model.platform.platform.tenant.Tenant', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'platform/tenant/tenant'
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
            name: 'tenantCode'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'tenantType'
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
            name: 'country'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'province'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'city'
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
            name: 'fax'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'telephoneNo'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'crmCode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'prefixDomainName'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'status'
            ,type:'int'
            ,allowNull:false
            ,critical:true
        }
    ]
});