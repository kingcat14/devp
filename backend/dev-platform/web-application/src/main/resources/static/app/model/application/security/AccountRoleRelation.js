Ext.define('AM.model.application.security.AccountRoleRelation', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        headers:{"Accept":"application/json"},
        url: 'application/security/accountRoleRelation'
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