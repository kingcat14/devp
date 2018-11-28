Ext.define('AM.store.icode.domain.DomainTreeStore', {
    extend: 'Ext.data.TreeStore'
    ,requires: [
        'Ext.data.TreeModel'
        ,'AM.model.util.CommonTreeNode'
    ]
    ,constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true
            ,model: 'AM.model.util.CommonTreeNode'
            ,proxy: {
                type: 'rest'
                ,url: '/icode/componentboard/tree'
                //,actionMethods:{read:'POST'}
                ,api:{read:"/icode/componentboard/tree"}
                ,listeners: {
                    exception: {
                        fn: me.onAjaxProxyException
                        ,scope: me
                    }
                }
            }
            ,listeners: {
                beforeload: {
                    fn:me.beforeload
                    ,scope: me
                }
            }
        }, cfg)]);
    }

    ,onAjaxProxyException: function(server, response, operation, options) {
        //var store = options.scope;
        var store = this;

        var error = operation.getError();
        if(error.status){
            error = error.status + ' ' + error.statusText;
        }
        //Ext.Msg.show({title: '操作失败', msg: response.responseText, buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
        Ext.Msg.show({title: '操作失败', msg: "ERROR:"+response.status+"<br/>请重试或联系管理员", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});

        if('read' !== operation.action){
            store.load();
        }
    }
    ,beforeload :function(store, operation, eOpts ){
        operation.setParams({type:operation.node.get('type'),objectId:operation.node.get('objectId')})


    }


});
