Ext.define('AM.store.icode.codegen.DomainModelCodeGenTaskStore', {
    extend: 'Ext.data.Store'
    ,requires: [
        'AM.model.icode.codegen.DomainModelCodeGenTask'
    ]
    ,constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true
            ,model: 'AM.model.icode.codegen.DomainModelCodeGenTask'
            ,proxy: {
                type: 'memory'
            }

        }, cfg)]);
    }
});
