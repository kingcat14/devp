Ext.define('AM.view.speedcloud.env.MachineSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.env.MachineSearchWindow'
    ,alias: 'widget.speedcloudenvMachineSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '服务器高级查询'
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
                            ,itemId: 'tidField'
                            ,fieldLabel: '租户id'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'tidMaxField'
                            ,fieldLabel: '租户id'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'tidMinField'
                            ,fieldLabel: '租户id'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'ipAddressField'
                            ,fieldLabel: 'IP地址'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'portField'
                            ,fieldLabel: '端口'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'portMaxField'
                            ,fieldLabel: '端口'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'portMinField'
                            ,fieldLabel: '端口'
                        }

                            ]
                }
            ]
            ,dockedItems: [
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
                                click: {fn: me.onRestButtonClick,scope: me}
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'search'
                            ,text: '查询'
                            ,listeners: {
                                click: {fn: me.onSearchButtonClick,scope: me}
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
        var tidField = me.down("#tidField");
        var tidMaxField = me.down("#tidMaxField");
        var tidMinField = me.down("#tidMinField");
        var nameField = me.down("#nameField");
        var ipAddressField = me.down("#ipAddressField");
        var portField = me.down("#portField");
        var portMaxField = me.down("#portMaxField");
        var portMinField = me.down("#portMinField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,tidMax:Ext.isNumber(tidMaxField.getValue())?tidMaxField.getValue():null
            ,tidMin:Ext.isNumber(tidMinField.getValue())?tidMinField.getValue():null
            ,name:Ext.valueFrom(nameField.getValue(), null)
            ,ipAddress:Ext.valueFrom(ipAddressField.getValue(), null)
            ,port:Ext.isNumber(portField.getValue())?portField.getValue():null
            ,portMax:Ext.isNumber(portMaxField.getValue())?portMaxField.getValue():null
            ,portMin:Ext.isNumber(portMinField.getValue())?portMinField.getValue():null
        };

        return condition;
    }

});