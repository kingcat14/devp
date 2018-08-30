Ext.define('AM.model.security.ResourceTreeNode', {
	extend: 'Ext.data.TreeModel',

	fields: [
		{
			name: 'id'
			,type: 'string'
		}
		,{
			name: 'name'
		}
		,{
			name: 'url'
		}
		,{
			name: 'leaf'
		}
		,{
			name: 'iconCls'
		}
		,{
			name: 'type'
		}
		,{
			name: 'orderIndex'
		}
		,{
			name: 'parentId'
		}
	]
});




