Ext.define('AM.view.speedcloud.deployscheme.ResourceRelationDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.deployscheme.ResourceRelationDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '方案资源间关系详细信息'
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
                            itemId: 'resourceField'
                            ,padding: '5 0 0 5'
                            ,name: 'resource'
                            ,fieldLabel: '主资源'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('resourceVO')?record.get('resourceVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'refResourceField'
                            ,padding: '5 0 0 5'
                            ,name: 'refResource'
                            ,fieldLabel: '关联资源'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('refResourceVO')?record.get('refResourceVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '对应关系类型'
                            ,renderer: function (value, field) {
                                var record = me.down('form').getForm().getRecord();
                                return record.get('typeVO')?record.get('typeVO').name:'';
                            }
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '对应关系代码'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '对应关系名称'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '对应关系别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '对应关系描述'
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
                            itemId: 'seqField'
                            ,padding: '5 0 0 5'
                            ,name: 'seq'
                            ,fieldLabel: '顺序号'
                        }
                        ,{
                            itemId: 'directionField'
                            ,padding: '5 0 0 5'
                            ,name: 'direction'
                            ,fieldLabel: '对应关系方向'
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