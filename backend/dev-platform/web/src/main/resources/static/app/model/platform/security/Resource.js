Ext.define('AM.model.platform.security.Resource', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'platform/security/resource'
    },
    fields: [
    	{
            name: 'name'
			,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'url'
			,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'type'
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
            name: 'parentCode'
			,type:'string'
            ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'appId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'hidden'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'orderIndex'
			,type:'int'
            ,allowNull:false
            ,critical:true
        }
    ]
});