Ext.define('AM.view.application.dashboard.portlet.ApplicationPortlet', {
    extend: 'Ext.panel.Panel'
    , xtype: 'portlet.Application'
    , title: '程序'
    , layout: 'fit'
    , height: 200
    , requires: [
        'AM.view.monitor.app.ApplicationController'
        ,'AM.store.monitor.app.ApplicationStore'
    ]

    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                stores:{
                    store:Ext.create('AM.store.monitor.app.ApplicationStore').applyCondition({status:'UNUSUAL', enable:true}).load()
                }
            }
        }, cfg)])
    }
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,bind:{store: '{store}'}
                    ,columnLines: true
                    ,columns: [
                        {
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '名称'
                            ,flex:1
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '代码'
                            ,flex:1
                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'totalCount'
                            ,format:'0,000'
                            ,text: '配置实例数量'

                        }
                        ,{
                            xtype: 'numbercolumn'
                            ,dataIndex: 'aliveCount'
                            ,format:'0,000'
                            ,text: '当前实例数量'

                        }
                    ]

                }
            ]

        });


        var runner = new Ext.util.TaskRunner();
        runner.newTask({
            run: function() {
                me.getViewModel().getStore('store').reload();
            }
            ,interval: 5000
        }).start();


        me.callParent(arguments);
    }


});