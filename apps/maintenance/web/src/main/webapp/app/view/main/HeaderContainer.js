
Ext.define('AM.view.main.HeaderContainer', {
    extend: 'Ext.container.Container',
    alias: 'widget.mainHeadercontainer',

    height: 52,
	html:'<h1 style="font-size: 24px;color: #fff;font-weight: normal;padding-left: 10px">AICODER - 运维管理工具</h1>',
    //html: '<h1 style="font-size: 16px;color: #fff;font-weight: normal;">iCode</h1>',
    //style: 'background: #7F99BE url(resources/images/layout-browser-hd-bg.gif) repeat-x center;',

    initComponent: function() {
        var me = this;

        me.callParent(arguments);
    }

});