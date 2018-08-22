Ext.define('AM.view.devp.sys.DevpSysCmpDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.sys.DevpSysCmpDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '系统组件详细信息'
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
                            itemId: 'etypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'etype'
                            ,fieldLabel: '元素类型'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '系统元素名称'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '系统元素代码'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '系统元素别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '系统元素描述'
                        }
                        ,{
                            itemId: 'recordStateField'
                            ,padding: '5 0 0 5'
                            ,name: 'recordState'
                            ,fieldLabel: '记录状态'
                        }
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '类型'
                        }
                        ,{
                            itemId: 'subTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'subType'
                            ,fieldLabel: '子类型'
                        }
                        ,{
                            itemId: 'stereotypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'stereotype'
                            ,fieldLabel: '构造型'
                        }
                        ,{
                            itemId: 'scopeField'
                            ,padding: '5 0 0 5'
                            ,name: 'scope'
                            ,fieldLabel: '范围'
                        }
                        ,{
                            itemId: 'versionField'
                            ,padding: '5 0 0 5'
                            ,name: 'version'
                            ,fieldLabel: '版本'
                        }
                        ,{
                            itemId: 'phaseField'
                            ,padding: '5 0 0 5'
                            ,name: 'phase'
                            ,fieldLabel: '阶段'
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
                            itemId: 'installableField'
                            ,padding: '5 0 0 5'
                            ,name: 'installable'
                            ,fieldLabel: '可安装组件'
                        }
                        ,{
                            itemId: 'sharedComponentField'
                            ,padding: '5 0 0 5'
                            ,name: 'sharedComponent'
                            ,fieldLabel: '共享组件'
                        }
                        ,{
                            itemId: 'sharedServiceField'
                            ,padding: '5 0 0 5'
                            ,name: 'sharedService'
                            ,fieldLabel: '共享服务'
                        }
                        ,{
                            itemId: 'prdRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'prdRid'
                            ,fieldLabel: '产品编号'
                        }
                        ,{
                            itemId: 'parentRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'parentRid'
                            ,fieldLabel: '父包编号'
                        }
                        ,{
                            itemId: 'seqField'
                            ,padding: '5 0 0 5'
                            ,name: 'seq'
                            ,fieldLabel: '顺序号'
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