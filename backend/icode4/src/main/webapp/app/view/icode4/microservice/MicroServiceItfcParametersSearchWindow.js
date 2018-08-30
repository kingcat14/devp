Ext.define('AM.view.icode4.microservice.MicroServiceItfcParametersSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'icode4.microservice.MicroServiceItfcParametersSearchWindow'
    ,autoScroll: true
    ,height: 250
    ,width: 400
    ,layout: {
        type: 'fit'
    }
    ,title: '微服务接口参数高级查询'
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
                    ,items: [
                        {
                            xtype: 'container'
                            ,layout: {
                                type: 'column'
                            }
                            ,items: [
                                {
                                    xtype: 'container'
                                    ,columnWidth: 0.5
                                    ,layout: {
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
                                            xtype: 'numberfield',
                                            allowDecimals:false,
                                            itemId: 'viewIndexField',
                                            name: 'viewIndex',
                                            fieldLabel: '排序'
                                        }
                                        ,{
                                            itemId: 'codeField',
                                            name: 'code',
                                            fieldLabel: '代码'
                                        }
                                        ,{
                                            itemId: 'pathMappingField',
                                            name: 'pathMapping',
                                            fieldLabel: '路径映射'
                                        }
                                        ,{
                                            itemId: 'microServiceItfcField',
                                            name: 'microServiceItfc',
                                            fieldLabel: '所属微服务接口'
                                        }

                                    ]
                                }
                                ,{
                                    xtype: 'container'
                                    ,columnWidth: 0.5
                                    ,layout: {
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
                                            fieldLabel: '参数名称',
                                            labelAlign: 'top'
                                        }
                                        ,{
                                            xtype: 'textfield',
                                            anchor: '96%',
                                            itemId: 'typeField',
                                            padding: '5 0 0 5',
                                            name: 'type',
                                            fieldLabel: '参数类型',
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
                                            store: [
                                                [true,'是'],
                                                [false,'否']
                                            ],
                                            anchor: '96%',
                                            itemId: 'requiredField',
                                            padding: '5 0 0 5',
                                            name: 'required',
                                            fieldLabel: '必填',
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
                    xtype: 'toolbar'
                    ,dock: 'bottom'
                    ,ui: 'footer'
                    ,items: [
                        {
                            xtype: 'tbfill'
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'page_white'
                            ,text: '重置'
                            ,listeners: {
                                click: {
                                    fn: me.onRestButtonClick
                                    ,scope: me
                                }
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'search'
                            ,text: '查询'
                            ,listeners: {
                                click: {
                                    fn: me.onSearchButtonClick
                                    ,scope: me
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

        var viewIndexField = me.down("#viewIndexField");
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var typeField = me.down("#typeField");
        var pathMappingField = me.down("#pathMappingField");
        var memoField = me.down("#memoField");
        var microServiceItfcField = me.down("#microServiceItfcField");
        var requiredField = me.down("#requiredField");

        var searchCondition = {
            viewIndex:Ext.isNumber(viewIndexField.getValue())?viewIndexField.getValue():null
            ,name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,code:Ext.isEmpty(codeField.getValue())?null:codeField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,pathMapping:Ext.isEmpty(pathMappingField.getValue())?null:pathMappingField.getValue()
            ,memo:Ext.isEmpty(memoField.getValue())?null:memoField.getValue()
            ,microServiceItfc:Ext.isEmpty(microServiceItfcField.getValue())?null:microServiceItfcField.getValue()
            ,required:Ext.isEmpty(requiredField.getValue())?null:requiredField.getValue()
        };

        this.store.proxy.extraParams = {searchCondition:searchCondition};
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
                start:0
                ,page:0
            }
        });
        this.hide();
    }
    ,setStore: function (store) {
        this.store = store;
    }

});