Ext.define('AM.model.platform.security.RoleResourceRelation', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        headers:{"Accept":"application/json"},
        url: 'platform/security/roleResourceRelation'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'resourceId'
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