
Ext.define('AM.store.security.AllResourceTreeStore', {
    extend: 'Ext.data.TreeStore',

    requires: [
        'AM.model.security.ResourceTreeNode'
    ],

	model: 'AM.model.security.ResourceTreeNode',
	autoLoad: true,
	nodeParam:'id',
	root: {
		expanded: true,
		//text: "My Root",
        name:'Root',
		id: '-1'
	},
    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({

            //storeId: 'security.AllResourceTreeStore',//设置后,初始化这个store都是同一个值了
            proxy: {
                type: 'ajax',
                headers:{Accept:'application/json'},
	            paramsAsJson:true,
                api: {
                    create: 'security/resource.do?method=add',
                    read: 'security/resource?method=getChildNodes',
                    update: 'security/resource.do?method=update',
                    destroy: 'security/resource.do?method=delete'
                },
                reader: {
                    type: 'json'
                }
            }
        }, cfg)]);
    }
});