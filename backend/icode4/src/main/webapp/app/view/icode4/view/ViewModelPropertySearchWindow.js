Ext.define('AM.view.icode4.view.ViewModelPropertySearchWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.icode4viewViewModelPropertySearchWindow',

    autoScroll: true,
    height: 250,
    width: 400,
    layout: {
        type: 'fit'
    },
    title: '展现对象属性高级查询',
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
                                        ,{
                                            itemId: 'propertyGroupField',
                                            name: 'propertyGroup',
                                            fieldLabel: '属性组'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            itemId: 'gridHiddenField',
                                            name: 'gridHidden',
                                            fieldLabel: '列表隐藏'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            itemId: 'searchConditionField',
                                            name: 'searchCondition',
                                            fieldLabel: '是否查询条件'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            itemId: 'formHiddenField',
                                            name: 'formHidden',
                                            fieldLabel: '表单隐藏'
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
                                            store: Ext.create("AM.store.icode4.view.ViewModelStore", {autoLoad:true, asynchronousLoad:false}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
                                            anchor: '96%',
                                            itemId: 'viewModelField',
                                            padding: '5 0 0 5',
                                            name: 'viewModelId',
                                            fieldLabel: '所属展现对象',
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
                                            fieldLabel: '核心关联',
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
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'gridIndexField',
                                            padding: '5 0 0 5',
                                            name: 'gridIndex',
                                            fieldLabel: '列表排序',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            anchor: '96%',
                                            itemId: 'quickSearchConditionField',
                                            padding: '5 0 0 5',
                                            name: 'quickSearchCondition',
                                            fieldLabel: '是否快速查询条件',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'formIndexField',
                                            padding: '5 0 0 5',
                                            name: 'formIndex',
                                            fieldLabel: '表单排序',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            anchor: '96%',
                                            itemId: 'viewIndexField',
                                            padding: '5 0 0 5',
                                            name: 'viewIndex',
                                            fieldLabel: '展现排序',
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
        var viewModelField = me.down("#viewModelField");
        var editableField = me.down("#editableField");
        var nullableField = me.down("#nullableField");
        var memoField = me.down("#memoField");
        var coreRelationField = me.down("#coreRelationField");
        var domainModelField = me.down("#domainModelField");
        var domainModelPropertyField = me.down("#domainModelPropertyField");
        var propertyGroupField = me.down("#propertyGroupField");
        var gridIndexField = me.down("#gridIndexField");
        var gridHiddenField = me.down("#gridHiddenField");
        var quickSearchConditionField = me.down("#quickSearchConditionField");
        var searchConditionField = me.down("#searchConditionField");
        var formIndexField = me.down("#formIndexField");
        var formHiddenField = me.down("#formHiddenField");
        var viewIndexField = me.down("#viewIndexField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,viewModel:Ext.isEmpty(viewModelField.getValue())?null:viewModelField.getValue()
            ,editable:Ext.isEmpty(editableField.getValue())?null:editableField.getValue()
            ,nullable:Ext.isEmpty(nullableField.getValue())?null:nullableField.getValue()
            ,memo:Ext.isEmpty(memoField.getValue())?null:memoField.getValue()
            ,coreRelation:Ext.isEmpty(coreRelationField.getValue())?null:coreRelationField.getValue()
            ,domainModel:Ext.isEmpty(domainModelField.getValue())?null:domainModelField.getValue()
            ,domainModelProperty:Ext.isEmpty(domainModelPropertyField.getValue())?null:domainModelPropertyField.getValue()
            ,propertyGroup:Ext.isEmpty(propertyGroupField.getValue())?null:propertyGroupField.getValue()
            ,gridIndex:Ext.isNumber(gridIndexField.getValue())?gridIndexField.getValue():null
            ,gridHidden:Ext.isEmpty(gridHiddenField.getValue())?null:gridHiddenField.getValue()
            ,quickSearchCondition:Ext.isEmpty(quickSearchConditionField.getValue())?null:quickSearchConditionField.getValue()
            ,searchCondition:Ext.isEmpty(searchConditionField.getValue())?null:searchConditionField.getValue()
            ,formIndex:Ext.isNumber(formIndexField.getValue())?formIndexField.getValue():null
            ,formHidden:Ext.isEmpty(formHiddenField.getValue())?null:formHiddenField.getValue()
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