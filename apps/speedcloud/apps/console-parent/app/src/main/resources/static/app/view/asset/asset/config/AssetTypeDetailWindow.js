Ext.define('AM.view.asset.asset.config.AssetTypeDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'asset.asset.config.AssetTypeDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '资产分类详细信息'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,layout: {
                        type: 'anchor'
                    }
                    ,defaults: {
                        labelAlign: 'right'
                        ,xtype: 'displayfield'
                        ,padding: '5 0 0 5'
                        ,anchor: '100%'
                        ,labelWidth:150
                    }
                    ,items: [
                        ,{
                            itemId: 'tidField'
                            ,padding: '5 0 0 5'
                            ,name: 'tid'
                            ,fieldLabel: '租户id'
                        }
                        ,{
                            itemId: 'numField'
                            ,padding: '5 0 0 5'
                            ,name: 'num'
                            ,fieldLabel: '编号'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '名称'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '代码'
                        }
                        ,{
                            itemId: 'useMonthField'
                            ,padding: '5 0 0 5'
                            ,name: 'useMonth'
                            ,fieldLabel: '年限(月)'
                        }
                        ,{
                            itemId: 'viewIndexField'
                            ,padding: '5 0 0 5'
                            ,name: 'viewIndex'
                            ,fieldLabel: '展现顺序'
                        }
                        ,{
                            itemId: 'parentCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'parentCode'
                            ,fieldLabel: '上级代码'
                        }
                        ,{
                            anchor: '98% 70%'
                            ,itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '说明'
                            ,labelAlign: 'top'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,setModel: function (model) {
        if (model && model.get('id')) {
            this.down('form').getForm().loadRecord(model);

        } else {
            this.down('form').getForm().reset();

        }
    }

});