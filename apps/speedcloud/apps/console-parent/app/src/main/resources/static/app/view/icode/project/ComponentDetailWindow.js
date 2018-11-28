Ext.define('AM.view.icode.project.ComponentDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'icode.project.ComponentDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '组件详细信息'
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
                            itemId: 'productField'
                            ,padding: '5 0 0 5'
                            ,name: 'product'
                            ,fieldLabel: '所属产品'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('productVO')?record.get('productVO').productName:'';
                            }
                        }
                        ,{
                            itemId: 'numberField'
                            ,padding: '5 0 0 5'
                            ,name: 'number'
                            ,fieldLabel: '组件编号'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '组件名称'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '组件代码'
                        }
                        ,{
                            itemId: 'basePackageField'
                            ,padding: '5 0 0 5'
                            ,name: 'basePackage'
                            ,fieldLabel: '基础包'
                        }
                        ,{
                            itemId: 'tplSetField'
                            ,padding: '5 0 0 5'
                            ,name: 'tplSet'
                            ,fieldLabel: '代码模板'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('tplSetVO')?record.get('tplSetVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '类型'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('typeVO')?record.get('typeVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'runnableField'
                            ,padding: '5 0 0 5'
                            ,name: 'runnable'
                            ,fieldLabel: '可运行组件'
                        }
                        ,{
                            anchor: '98% 70%'
                            ,itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '描述'
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