Ext.define('AM.view.dashboard.Dashboard', {
	extend: 'Ext.panel.Panel'
	,title:'我的应用'
	,bodyCls: 'app-dashboard'
	,reference: 'dashboard'
	,controller: 'main-Dashboard'
	,requires:[
		'AM.view.dashboard.DashboardController'
		, 'AM.view.dashboard.UserDetailPanel'
		, 'AM.store.application.application.AppStore'
	]
	,bodyPadding: 10
	,layout: {
		type: 'vbox',
		align: 'stretch'
	}
	,initComponent: function() {

		var me = this;


		Ext.apply(me, {

            items: [
                {
                    xtype: 'container'
                    ,layout: 'hbox'
                    ,margin: '0 0 10 0'
                    ,layouts:{
                        margin: '10 10 10 10'
                    }
                    ,defaults:{
                        margin: '10 10 0 0'
                    }
                    ,items: [
                        {
                            xtype: 'textfield'
                            ,fieldLabel: '应用名称'
                            ,labelAlign: 'left'
                            ,labelStyle: 'font-weight: bold;'
                            ,labelWidth: 70
                            ,margin: '10 0 10 0'
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'x-fa fa-search'
                            ,text: ''
                            ,margin: '10 0 10 0'
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'x-fa fa-times'
                            ,text: ''
                            ,margin: '10 10 10 0'
                        }

                    ]
                }
                ,{
                    xtype:'container'
                    ,defaults: {
                        frame: true,
                    }
                    ,layout: {
                        type: 'column'
                    }
                    ,items: [
                        {
                            xtype: 'container',
                            columnWidth: 0.66,
                            itemId:'column01',
                            reference: 'appContainer',
                            layout: {
                                type: 'anchor'
                            }
                            ,defaults:{
                                margin: '0 10 10 0'
                                ,width:100
                                ,height:100
                                ,iconAlign: 'top'
                            }
                            ,items:
								[
                                	//{xtype: 'button',scale: 'small',iconCls: 'x-fa fa-eye',text: '区域病理' ,href:'http://118.31.115.201:8090/'}
                            	]
                        }

                        ,{
                            xtype: 'container',
                            columnWidth: 0.33,
                            itemId:'column03',
                            layout: {
                                type: 'anchor'
                            },defaults:{
                                margin: '0 10 10 0'
                            }
                            ,items:[
                                {
                                    xtype:'form-register'
                                }
                            ]
                        }


                    ]
                }
            ]

		});

		me.callParent(arguments);
        console.log(333)
        me.loadApp();

	}
	,loadApp:function(){
        var me = this;
	    var appStore = Ext.create('AM.store.application.application.AppStore', {asynchronousLoad:false, autoLoad:false, pageSize:500})
        appStore.load({
            callback: function(records, operation, success) {
                if(success){
                    for(var i in records){

                        me.lookupReference('appContainer').add({
                            xtype: 'button'
                            ,scale: 'small'
                            ,iconCls: 'x-fa fa-eye'
                            ,text: records[i].get("name")
                            ,href: records[i].get("url")
                        })
                    }
                }

            }
        })
    }
})