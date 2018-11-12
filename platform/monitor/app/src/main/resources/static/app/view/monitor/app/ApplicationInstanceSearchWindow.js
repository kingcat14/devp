Ext.define('AM.view.monitor.app.ApplicationInstanceSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.app.ApplicationInstanceSearchWindow'
    ,alias: 'widget.monitorappApplicationInstanceSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '程序实例高级查询'
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
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.monitor.app.ApplicationStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'appField'
                            ,fieldLabel: 'app'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'ipField'
                            ,fieldLabel: 'ip'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'portField'
                            ,fieldLabel: 'port'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'portMaxField'
                            ,fieldLabel: 'port'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'portMinField'
                            ,fieldLabel: 'port'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: [
                                [true,'是']
                                ,[false,'否']
                            ]
                            ,value:true
                            ,typeAhead:false
                            ,editable:false
                            ,itemId: 'aliveField'
                            ,fieldLabel: '运行中'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: [
                                [true,'是']
                                ,[false,'否']
                            ]
                            ,value:true
                            ,typeAhead:false
                            ,editable:false
                            ,itemId: 'alarmField'
                            ,fieldLabel: '停运告警'
                        }

                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'stopTimeField'
                            ,fieldLabel: '最近停运时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'stopTimeStartField'
                            ,fieldLabel: '起始最近停运时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'stopTimeEndField'
                            ,fieldLabel: '结束最近停运时间'
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
        var appField = me.down("#appField");
        var ipField = me.down("#ipField");
        var portField = me.down("#portField");
        var portMaxField = me.down("#portMaxField");
        var portMinField = me.down("#portMinField");
        var aliveField = me.down("#aliveField");
        var alarmField = me.down("#alarmField");
        var stopTimeStartField = me.down("#stopTimeStartField");
        var stopTimeEndField = me.down("#stopTimeEndField");
        var stopTimeField = me.down("#stopTimeField");

        var condition = {
            app:Ext.valueFrom(appField.getValue(), null)
            ,ip:Ext.valueFrom(ipField.getValue(), null)
            ,port:Ext.isNumber(portField.getValue())?portField.getValue():null
            ,portMax:Ext.isNumber(portMaxField.getValue())?portMaxField.getValue():null
            ,portMin:Ext.isNumber(portMinField.getValue())?portMinField.getValue():null
            ,alive:Ext.valueFrom(aliveField.getValue(), null)
            ,alarm:Ext.valueFrom(alarmField.getValue(), null)
            ,stopTime:Ext.valueFrom(Ext.Date.format(stopTimeField.getValue(),'Y-m-d H:i:s'), null)
            ,stopTimeStart:Ext.valueFrom(Ext.Date.format(stopTimeStartField.getValue(),'Y-m-d H:i:s'), null)
            ,stopTimeEnd:Ext.valueFrom(Ext.Date.format(stopTimeEndField.getValue(),'Y-m-d H:i:s'), null)
        };

        return condition;
    }

});