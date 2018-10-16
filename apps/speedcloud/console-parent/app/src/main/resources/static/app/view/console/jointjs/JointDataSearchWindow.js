Ext.define('AM.view.console.jointjs.JointDataSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'console.jointjs.JointDataSearchWindow'
    ,alias: 'widget.consolejointjsJointDataSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '图形数据高级查询'
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
                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,layout: {
                        type: 'table'
                        ,columns: 2
                        ,tableAttrs: {style: {width: '100%'}}
                    }
                    ,defaults:{width:'100%'}
                    ,items:[
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'schemeField'
                            ,fieldLabel: '方案ID'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'schemeMaxField'
                            ,fieldLabel: '方案ID'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'schemeMinField'
                            ,fieldLabel: '方案ID'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'viewJsonField'
                            ,fieldLabel: 'json'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'typeField'
                            ,fieldLabel: '类型'
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

        me.fireEvent('saved');
        me.hide();
    }
    ,onRestButtonClick: function (button, e, options) {
        var me = this;
        me.down('form').getForm().reset();

        me.fireEvent('saved');


    }
    ,getCondition: function(){

        var me = this;
        var schemeField = me.down("#schemeField");
        var schemeMaxField = me.down("#schemeMaxField");
        var schemeMinField = me.down("#schemeMinField");
        var viewJsonField = me.down("#viewJsonField");
        var typeField = me.down("#typeField");

        var condition = {
            scheme:Ext.isNumber(schemeField.getValue())?schemeField.getValue():null
            ,schemeMax:Ext.isNumber(schemeMaxField.getValue())?schemeMaxField.getValue():null
            ,schemeMin:Ext.isNumber(schemeMinField.getValue())?schemeMinField.getValue():null
            ,viewJson:Ext.isEmpty(viewJsonField.getValue())?null:viewJsonField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
        };

        return condition;
    }

});