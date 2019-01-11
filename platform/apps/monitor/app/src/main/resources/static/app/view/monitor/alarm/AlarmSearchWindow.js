Ext.define('AM.view.monitor.alarm.AlarmSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.alarm.AlarmSearchWindow'
    ,alias: 'widget.monitoralarmAlarmSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '告警高级查询'
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
                            xtype: 'textfield'
                            ,itemId: 'codeField'
                            ,fieldLabel: '代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'nameField'
                            ,fieldLabel: '名称'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.monitor.alarm.AlarmTypeStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'typeField'
                            ,fieldLabel: '类型'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.monitor.app.ApplicationStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'appField'
                            ,fieldLabel: '程序'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.monitor.indicator.IndicatorStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'counterField'
                            ,fieldLabel: '指标'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'valueField'
                            ,fieldLabel: '指标值'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'statusField'
                            ,fieldLabel: '状态'
                        }

                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'occurTimeField'
                            ,fieldLabel: '发生时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'occurTimeStartField'
                            ,fieldLabel: '起始发生时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'occurTimeEndField'
                            ,fieldLabel: '结束发生时间'
                        }

                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'lastOccurTimeField'
                            ,fieldLabel: '最后发生时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'lastOccurTimeStartField'
                            ,fieldLabel: '起始最后发生时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'lastOccurTimeEndField'
                            ,fieldLabel: '结束最后发生时间'
                        }

                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'disappearTimeField'
                            ,fieldLabel: '消失时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'disappearTimeStartField'
                            ,fieldLabel: '起始消失时间'
                        }
                        ,{
                            xtype: 'datefield'
                            ,format: 'Y-m-d'
                            ,itemId: 'disappearTimeEndField'
                            ,fieldLabel: '结束消失时间'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'occurCountField'
                            ,fieldLabel: '发生次数'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'occurCountMaxField'
                            ,fieldLabel: '发生次数'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'occurCountMinField'
                            ,fieldLabel: '发生次数'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'contentField'
                            ,fieldLabel: '内容'
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
        var codeField = me.down("#codeField");
        var nameField = me.down("#nameField");
        var typeField = me.down("#typeField");
        var appField = me.down("#appField");
        var counterField = me.down("#counterField");
        var valueField = me.down("#valueField");
        var statusField = me.down("#statusField");
        var occurTimeStartField = me.down("#occurTimeStartField");
        var occurTimeEndField = me.down("#occurTimeEndField");
        var occurTimeField = me.down("#occurTimeField");
        var lastOccurTimeStartField = me.down("#lastOccurTimeStartField");
        var lastOccurTimeEndField = me.down("#lastOccurTimeEndField");
        var lastOccurTimeField = me.down("#lastOccurTimeField");
        var disappearTimeStartField = me.down("#disappearTimeStartField");
        var disappearTimeEndField = me.down("#disappearTimeEndField");
        var disappearTimeField = me.down("#disappearTimeField");
        var occurCountField = me.down("#occurCountField");
        var occurCountMaxField = me.down("#occurCountMaxField");
        var occurCountMinField = me.down("#occurCountMinField");
        var contentField = me.down("#contentField");

        var condition = {
            code:Ext.valueFrom(codeField.getValue(), null)
            ,name:Ext.valueFrom(nameField.getValue(), null)
            ,type:Ext.valueFrom(typeField.getValue(), null)
            ,app:Ext.valueFrom(appField.getValue(), null)
            ,counter:Ext.valueFrom(counterField.getValue(), null)
            ,value:Ext.valueFrom(valueField.getValue(), null)
            ,status:Ext.valueFrom(statusField.getValue(), null)
            ,occurTime:Ext.valueFrom(Ext.Date.format(occurTimeField.getValue(),'Y-m-d H:i:s'), null)
            ,occurTimeStart:Ext.valueFrom(Ext.Date.format(occurTimeStartField.getValue(),'Y-m-d H:i:s'), null)
            ,occurTimeEnd:Ext.valueFrom(Ext.Date.format(occurTimeEndField.getValue(),'Y-m-d H:i:s'), null)
            ,lastOccurTime:Ext.valueFrom(Ext.Date.format(lastOccurTimeField.getValue(),'Y-m-d H:i:s'), null)
            ,lastOccurTimeStart:Ext.valueFrom(Ext.Date.format(lastOccurTimeStartField.getValue(),'Y-m-d H:i:s'), null)
            ,lastOccurTimeEnd:Ext.valueFrom(Ext.Date.format(lastOccurTimeEndField.getValue(),'Y-m-d H:i:s'), null)
            ,disappearTime:Ext.valueFrom(Ext.Date.format(disappearTimeField.getValue(),'Y-m-d H:i:s'), null)
            ,disappearTimeStart:Ext.valueFrom(Ext.Date.format(disappearTimeStartField.getValue(),'Y-m-d H:i:s'), null)
            ,disappearTimeEnd:Ext.valueFrom(Ext.Date.format(disappearTimeEndField.getValue(),'Y-m-d H:i:s'), null)
            ,occurCount:Ext.isNumber(occurCountField.getValue())?occurCountField.getValue():null
            ,occurCountMax:Ext.isNumber(occurCountMaxField.getValue())?occurCountMaxField.getValue():null
            ,occurCountMin:Ext.isNumber(occurCountMinField.getValue())?occurCountMinField.getValue():null
            ,content:Ext.valueFrom(contentField.getValue(), null)
        };

        return condition;
    }

});