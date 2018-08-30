Ext.define('AM.view.icode4.tplfile.TplCodeSearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.icode4tplfileTplCodeSearchWindow',

    autoScroll: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: '文件模板高级查询',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10,
                    items: [
                        {
                            xtype: 'container',
                            layout: {
                                type: 'column'
                            }
                            ,items: [
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    }
                                    ,defaults:{
                                        xtype: 'textfield'
                                        ,labelAlign: 'top'
                                        ,padding: '5 0 0 5'
                                        ,anchor: '96%',
                                    }
                                    ,items: [

                                        ,{
                                            itemId: 'nameField',
                                            name: 'name',
                                            fieldLabel: '模板名称'
                                        }
                                        ,{
                                            itemId: 'typeField',
                                            name: 'type',
                                            fieldLabel: '模板类型'
                                        }
                                        ,{
                                            itemId: 'filePathField',
                                            name: 'filePath',
                                            fieldLabel: '文件路径'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.icode4.tplfile.TplSetStore", {autoLoad:true, asynchronousLoad:false}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            itemId: 'tplSetField',
                                            name: 'tplSetId',
                                            fieldLabel: '所属集合'
                                        }
                                        ,{
                                            itemId: 'contentField',
                                            name: 'content',
                                            fieldLabel: '文件内容'
                                        }

                                    ]
                                },
                                {
                                    xtype: 'container',
                                    columnWidth: 0.5,
                                    layout: {
                                        type: 'anchor'
                                    }
                                    ,defaults:{
                                        xtype: 'textfield'
                                        ,labelAlign: 'top'
                                        ,padding: '5 0 0 5'
                                        ,anchor: '96%',
                                    }
                                    ,items: [
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'codeField',
                                            padding: '5 0 0 5',
                                            name: 'code',
                                            fieldLabel: '模板代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.icode4.system.ModelTypeStore", {autoLoad:true, asynchronousLoad:false}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            anchor: '96%',
                                            itemId: 'acceptModelTypeField',
                                            padding: '5 0 0 5',
                                            name: 'acceptModelTypeId',
                                            fieldLabel: '接收模型类型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'fileNameField',
                                            padding: '5 0 0 5',
                                            name: 'fileName',
                                            fieldLabel: '文件名',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            anchor: '96%',
                                            itemId: 'overridableField',
                                            padding: '5 0 0 5',
                                            name: 'overridable',
                                            fieldLabel: '可覆盖',
                                            labelAlign: 'top'
                                        }
                                    ]
                                }
                            ]
                        }

                    ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    ui: 'footer',
                    items: [
                        {
                            xtype: 'tbfill'
                        }

                        ,{
                            xtype: 'button',
                            iconCls: 'page_white',
                            text: '重置',
                            listeners: {
                                click: {
                                    fn: me.onRestButtonClick,
                                    scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button',
                            iconCls: 'search',
                            text: '查询',
                            listeners: {
                                click: {
                                    fn: me.onSearchButtonClick,
                                    scope: me
                                }
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,onSearchButtonClick: function (button, e, options) {

        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var typeField = me.down("#typeField");
        var acceptModelTypeField = me.down("#acceptModelTypeField");
        var filePathField = me.down("#filePathField");
        var fileNameField = me.down("#fileNameField");
        var tplSetField = me.down("#tplSetField");
        var overridableField = me.down("#overridableField");
        var contentField = me.down("#contentField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,acceptModelType:Ext.isEmpty(acceptModelTypeField.getValue())?null:acceptModelTypeField.getValue()
            ,filePath:Ext.isEmpty(filePathField.getValue())?null:filePathField.getValue()
            ,fileName:Ext.isEmpty(fileNameField.getValue())?null:fileNameField.getValue()
            ,tplSet:Ext.isEmpty(tplSetField.getValue())?null:tplSetField.getValue()
            ,overridable:Ext.isEmpty(overridableField.getValue())?null:overridableField.getValue()
            ,content:Ext.isEmpty(contentField.getValue())?null:contentField.getValue()
        };

        this.store.proxy.extraParams={searchCondition:condition};
        this.store.load({
            params:{
                start:0,
                page:0
            }
        });


        this.hide();
    }
    ,onRestButtonClick: function (button, e, options) {

        this.down('form').getForm().reset();

        this.store.proxy.extraParams={searchCondition:{}};
            this.store.load({
            params:{
                start:0,
                page:0
            }
        });


        this.hide();
    }

    ,setStore: function (store) {
        this.store = store;
    }

});