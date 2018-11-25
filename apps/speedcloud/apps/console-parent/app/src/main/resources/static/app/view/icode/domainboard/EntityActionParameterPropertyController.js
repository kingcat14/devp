Ext.define('AM.view.icode.domainboard.EntityActionParameterPropertyController', {
	extend: 'Ext.app.ViewController'
	,requires: [

	]
	,alias: 'controller.icode.domainboard.EntityActionParameterPropertyController'

    ,onAddButtonClick: function() {

        var me = this;
        var store = me.getViewModel().getStore('store')
        var count = store.getCount();

        // Create a model instance
        var r = Ext.create('AM.model.icode.domain.EntityActionParameterProperty', {
            type:'String'
            ,actionParameter:null
            , idx:count+1
        });

        store.insert(count, r);
        me.lookupReference('mainGridPanel').findPlugin('cellediting').startEdit(r, 2);
    }

    ,reloadStore:function () {

        var me = this;

        var mainGridPanel = me.lookupReference('mainGridPanel');

        mainGridPanel.getStore().load({
            callback: function (records, operation, success) {
                if (success) {
                    Ext.MsgUtil.notification('操作成功', '同步列表成功');
                }
            }
        });
    }
})