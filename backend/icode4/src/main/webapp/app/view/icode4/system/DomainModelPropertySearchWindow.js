Ext.define('AM.view.icode4.system.DomainModelPropertySearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.icode4systemDomainModelPropertySearchWindow',

    autoScroll: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: '领域对象属性高级查询',
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
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.icode4.system.DomainModelStore", {autoLoad:true, asynchronousLoad:false}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            itemId: 'domainModelField',
                                            name: 'domainModelId',
                                            fieldLabel: '所属领域对象'
                                        }
                                        ,{
                                            itemId: 'codeField',
                                            name: 'code',
                                            fieldLabel: '属性代码'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            itemId: 'persistField',
                                            name: 'persist',
                                            fieldLabel: '持久化'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            itemId: 'nullableField',
                                            name: 'nullable',
                                            fieldLabel: '可为空'
                                        }
                                        ,{
                                            itemId: 'propertyGroupField',
                                            name: 'propertyGroup',
                                            fieldLabel: '属性组'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.icode4.system.DomainModelPropertyStore", {autoLoad:true, asynchronousLoad:false}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            itemId: 'relatedDomainModelPropertyField',
                                            name: 'relatedDomainModelPropertyId',
                                            fieldLabel: '关联对象属性'
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
                                            itemId: 'nameField',
                                            padding: '5 0 0 5',
                                            name: 'name',
                                            fieldLabel: '属性名',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'typeField',
                                            padding: '5 0 0 5',
                                            name: 'type',
                                            fieldLabel: '属性类型',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            anchor: '96%',
                                            itemId: 'editableField',
                                            padding: '5 0 0 5',
                                            name: 'editable',
                                            fieldLabel: '可修改',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'memoField',
                                            padding: '5 0 0 5',
                                            name: 'memo',
                                            fieldLabel: '备注',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.icode4.system.DomainModelStore", {autoLoad:true, asynchronousLoad:false}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            anchor: '96%',
                                            itemId: 'relatedDomainModelField',
                                            padding: '5 0 0 5',
                                            name: 'relatedDomainModelId',
                                            fieldLabel: '关联对象',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'viewIndexField',
                                            padding: '5 0 0 5',
                                            name: 'viewIndex',
                                            fieldLabel: '展现顺序',
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

        var domainModelField = me.down("#domainModelField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var typeField = me.down("#typeField");
        var persistField = me.down("#persistField");
        var editableField = me.down("#editableField");
        var nullableField = me.down("#nullableField");
        var memoField = me.down("#memoField");
        var propertyGroupField = me.down("#propertyGroupField");
        var relatedDomainModelField = me.down("#relatedDomainModelField");
        var relatedDomainModelPropertyField = me.down("#relatedDomainModelPropertyField");
        var viewIndexField = me.down("#viewIndexField");

        var condition = {
            domainModel:Ext.isEmpty(domainModelField.getValue())?null:domainModelField.getValue()
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,persist:Ext.isEmpty(persistField.getValue())?null:persistField.getValue()
            ,editable:Ext.isEmpty(editableField.getValue())?null:editableField.getValue()
            ,nullable:Ext.isEmpty(nullableField.getValue())?null:nullableField.getValue()
            ,memo:Ext.isEmpty(memoField.getValue())?null:memoField.getValue()
            ,propertyGroup:Ext.isEmpty(propertyGroupField.getValue())?null:propertyGroupField.getValue()
            ,relatedDomainModel:Ext.isEmpty(relatedDomainModelField.getValue())?null:relatedDomainModelField.getValue()
            ,relatedDomainModelProperty:Ext.isEmpty(relatedDomainModelPropertyField.getValue())?null:relatedDomainModelPropertyField.getValue()
            ,viewIndex:Ext.isNumber(viewIndexField.getValue())?viewIndexField.getValue():null
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