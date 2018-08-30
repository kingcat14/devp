Ext.define('AM.store.icode4.codegen.DomainModelCodeGenTaskStore', {
    extend: 'Ext.data.Store'
    ,requires: [
        'AM.model.icode4.codegen.DomainModelCodeGenTask'
    ]
    ,constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true
            ,model: 'AM.model.icode4.codegen.DomainModelCodeGenTask'
            ,proxy: {
                type: 'memory'
            }

        }, cfg)]);
    }
});
