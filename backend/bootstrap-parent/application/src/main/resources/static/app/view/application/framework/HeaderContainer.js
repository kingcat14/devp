Ext.define('AM.view.application.framework.HeaderContainer', {
    extend: 'Ext.container.Container',
    alias: 'widget.mainHeadercontainer',
    title: '',
    height: 52,
    layout: {
        type: 'hbox',
        align: 'middle'
    }
    ,style: 'background: #28384a;border-bottom: 1px solid #0d1218;'

    ,initComponent: function() {
        var me = this;
        document.title = this.title;
        //me.callParent(arguments);
        this.items = [{
            xtype: 'component',
            id: 'app-header-logo',
            style: 'color: white;font-size: 26px;margin: 0 10px;',
            cls: [ 'ext', 'ext-sencha' ]
        },{
            xtype: 'component',
            id: 'app-header-title',
            style: 'color: white;font-size: 18px;font-weight: bold;padding: 10px 0 10px 0;',
            html: this.title,
            flex: 1
        }];
        this.callParent();
    }
    ,setTitle:function(title){
        this.title = title;
        this.down('#app-header-title').setHtml(title);
        document.title = title;
    }

});