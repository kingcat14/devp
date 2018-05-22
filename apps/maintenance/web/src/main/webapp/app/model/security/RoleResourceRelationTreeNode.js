
Ext.define('AM.model.security.RoleResourceRelationTreeNode', {
    extend: 'Ext.data.Model',

    fields: [
	    {
		    name: 'id',
		    type:'string'
	    }
	    ,{
		    name: 'name',
		    type: 'string'
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

        ,{
            name: 'checked',
            defaultValue: false,
            type: 'boolean'
        }
    ]
});