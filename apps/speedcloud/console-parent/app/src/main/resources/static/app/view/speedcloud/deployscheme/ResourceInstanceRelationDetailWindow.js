Ext.define('AM.view.speedcloud.deployscheme.ResourceInstanceRelationDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.deployscheme.ResourceInstanceRelationDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '方案资源关联实例详细信息'
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
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '类型'
                        }
                        ,{
                            itemId: 'statusField'
                            ,padding: '5 0 0 5'
                            ,name: 'status'
                            ,fieldLabel: '状态'
                        }
                        ,{
                            itemId: 'notesField'
                            ,padding: '5 0 0 5'
                            ,name: 'notes'
                            ,fieldLabel: '备注'
                        }
                        ,{
                            itemId: 'seqField'
                            ,padding: '5 0 0 5'
                            ,name: 'seq'
                            ,fieldLabel: '顺序号'
                        }
                        ,{
                            itemId: 'prdRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'prdRid'
                            ,fieldLabel: '产品编号'
                        }
                        ,{
                            itemId: 'schemeField'
                            ,padding: '5 0 0 5'
                            ,name: 'scheme'
                            ,fieldLabel: '部署方案编号'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('schemeVO')?record.get('schemeVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'resourceField'
                            ,padding: '5 0 0 5'
                            ,name: 'resource'
                            ,fieldLabel: '关联资源编号'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('resourceVO')?record.get('resourceVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'assetField'
                            ,padding: '5 0 0 5'
                            ,name: 'asset'
                            ,fieldLabel: '关联实例编号'
                        }
                        ,{
                            itemId: 'assetCategoryCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetCategoryCode'
                            ,fieldLabel: '关联实例类别代码'
                        }
                        ,{
                            itemId: 'assetTypeCodeField'
                            ,padding: '5 0 0 5'
                            ,name: 'assetTypeCode'
                            ,fieldLabel: '关联实例类型代码'
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