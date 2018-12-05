Ext.define('AM.store.icode.codegenboard.TplFileTreeStore', {
    extend: 'Ext.data.TreeStore'
    ,requires: [
        'AM.model.util.CheckTreeNode'
    ]
    ,constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: false
            ,model: 'AM.model.util.CheckTreeNode'
            ,pageSize: 100000
            ,limitParam:'pageSize'
            ,proxy: {
                type: 'rest'
                ,url: 'codegen/tplFileTree'
                //,actionMethods:{read:'POST'}
                ,api:{read:"codegen/tplFileTree"}
                ,listeners: {
                    exception: {
                        fn: me.onAjaxproxyException
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
    },

    onAjaxproxyException: function(server, response, operation, options) {
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
