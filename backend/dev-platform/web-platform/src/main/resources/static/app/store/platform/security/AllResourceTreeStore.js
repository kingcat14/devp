
Ext.define('AM.store.platform.security.AllResourceTreeStore', {
    extend: 'Ext.data.TreeStore',

    requires: [
        'AM.model.platform.security.ResourceTreeNode'
    ],

	model: 'AM.model.platform.security.ResourceTreeNode',
	autoLoad: false,
	nodeParam:'id',
	root: {
		expanded: false,
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
                    create: 'platform/security/resource.do?method=add',
                    read: 'platform/security/resource?method=getChildNodes',
                    update: 'platform/security/resource.do?method=update',
                    destroy: 'platform/security/resource.do?method=delete'
                },
                reader: {
                    type: 'json'
                    ,rootProperty:function(data){
                        console.log(data);
                        // Extract child nodes from the items or children property in the dataset
                        return data.data || data.children;
                    }
                }
            }
        }, cfg)]);
    }
});