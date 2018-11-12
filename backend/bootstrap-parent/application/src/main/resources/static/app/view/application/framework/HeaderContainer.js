Ext.define('AM.view.application.framework.HeaderContainer', {
    extend: 'Ext.container.Container',
    alias: 'widget.mainHeadercontainer',
    requires:[
        'AM.view.application.framework.UserSwitcher'
        ,'AM.view.application.framework.HeaderContainerController'
    ],
    title: '',
    height: 52,
    layout: {
        type: 'hbox',
        align: 'middle'
    }
    ,style: 'background: #28384a;border-bottom: 1px solid #0d1218;'
    ,controller:'HeaderContainerController'
    ,initComponent: function() {
        var me = this;
        document.title = this.title;
        //me.callParent(arguments);
        this.items = [{
            xtype: 'component',
            id: 'app-header-logo',
            style: 'color: white;font-size: 20px;margin: 0 10px;',
            cls: [ 'fas', 'fa-search', 'ext-sencha' ]
        }
        ,{
            xtype: 'component',
            id: 'app-header-title',
            style: 'color: white;font-size: 18px;font-weight: bold;padding: 10px 0 10px 0;',
            html: this.title,
        }
        ,{
            xtype: 'component',
            // id: 'app-header-title',
            flex: 1
        }
        ,{
                xtype: 'userSwitcher'
                ,style: 'color: white;font-size: 20px;margin: 0 10px;'
                ,html:'你好'
                ,listeners:{
                    afterrender: 'loadUserInfo'
                }
            }
        ];

        this.callParent();
    }
    ,setTitle:function(title){
        this.title = title;
        this.down('#app-header-title').setHtml(title);
        document.title = title;
    }


});