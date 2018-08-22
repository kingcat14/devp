Ext.define('AM.model.platform.security.AccountRoleRelation', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        headers:{"Accept":"application/json"},
        url: 'platform/security/accountRoleRelation'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'accountId'
			,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'roleId'
			,type:'string'
            ,allowNull:false
            ,critical:true
        }
    ]
});