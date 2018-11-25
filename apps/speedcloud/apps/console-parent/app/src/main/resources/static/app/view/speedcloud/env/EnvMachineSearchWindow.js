Ext.define('AM.view.speedcloud.env.EnvMachineSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.env.EnvMachineSearchWindow'
    ,alias: 'widget.speedcloudenvEnvMachineSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '环境设备关联高级查询'
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
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.env.AppEnvConfigStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'evnField'
                            ,fieldLabel: '环境'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.env.MachineStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'machineField'
                            ,fieldLabel: '机器'
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
                            iconCls: 'fas fa-search',
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
        var tidField = me.down("#tidField");
        var tidMaxField = me.down("#tidMaxField");
        var tidMinField = me.down("#tidMinField");
        var evnField = me.down("#evnField");
        var evnMaxField = me.down("#evnMaxField");
        var evnMinField = me.down("#evnMinField");
        var machineField = me.down("#machineField");
        var machineMaxField = me.down("#machineMaxField");
        var machineMinField = me.down("#machineMinField");

        var condition = {
            tid:Ext.isNumber(tidField.getValue())?tidField.getValue():null
            ,tidMax:Ext.isNumber(tidMaxField.getValue())?tidMaxField.getValue():null
            ,tidMin:Ext.isNumber(tidMinField.getValue())?tidMinField.getValue():null
            ,evn:Ext.isEmpty(evnField.getValue())?null:evnField.getValue()
            ,machine:Ext.isEmpty(machineField.getValue())?null:machineField.getValue()
        };

        return condition;
    }

});