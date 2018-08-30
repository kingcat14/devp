Ext.define('AM.view.icode4.microservice.TransModelPropertySearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.icode4microserviceTransModelPropertySearchWindow',

    autoScroll: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: '传输对象属性高级查询',
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
                                            fieldLabel: '属性名'
                                        }
                                        ,{
                                            itemId: 'typeField',
                                            name: 'type',
                                            fieldLabel: '属性类型'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            itemId: 'editableField',
                                            name: 'editable',
                                            fieldLabel: '可修改'
                                        }
                                        ,{
                                            itemId: 'memoField',
                                            name: 'memo',
                                            fieldLabel: '备注'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.icode4.system.DomainModelStore", {autoLoad:true, asynchronousLoad:false}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            itemId: 'domainModelField',
                                            name: 'domainModelId',
                                            fieldLabel: '关联对象'
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
                                            fieldLabel: '属性代码',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.icode4.microservice.TransModelStore", {autoLoad:true, asynchronousLoad:false}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            anchor: '96%',
                                            itemId: 'transModelField',
                                            padding: '5 0 0 5',
                                            name: 'transModelId',
                                            fieldLabel: '所属传输对象',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            anchor: '96%',
                                            itemId: 'nullableField',
                                            padding: '5 0 0 5',
                                            name: 'nullable',
                                            fieldLabel: '可为空',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            anchor: '96%',
                                            itemId: 'coreRelationField',
                                            padding: '5 0 0 5',
                                            name: 'coreRelation',
                                            fieldLabel: '核心对象属性',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.icode4.system.DomainModelPropertyStore", {autoLoad:true, asynchronousLoad:false}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            anchor: '96%',
                                            itemId: 'domainModelPropertyField',
                                            padding: '5 0 0 5',
                                            name: 'domainModelPropertyId',
                                            fieldLabel: '关联对象属性',
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
        var transModelField = me.down("#transModelField");
        var editableField = me.down("#editableField");
        var nullableField = me.down("#nullableField");
        var memoField = me.down("#memoField");
        var coreRelationField = me.down("#coreRelationField");
        var domainModelField = me.down("#domainModelField");
        var domainModelPropertyField = me.down("#domainModelPropertyField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,transModelId:Ext.isEmpty(transModelField.getValue())?null:transModelField.getValue()
            ,editable:Ext.isEmpty(editableField.getValue())?null:editableField.getValue()
            ,nullable:Ext.isEmpty(nullableField.getValue())?null:nullableField.getValue()
            ,memo:Ext.isEmpty(memoField.getValue())?null:memoField.getValue()
            ,coreRelation:Ext.isEmpty(coreRelationField.getValue())?null:coreRelationField.getValue()
            ,domainModel:Ext.isEmpty(domainModelField.getValue())?null:domainModelField.getValue()
            ,domainModelProperty:Ext.isEmpty(domainModelPropertyField.getValue())?null:domainModelPropertyField.getValue()
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