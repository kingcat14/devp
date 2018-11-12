Ext.define('AM.view.monitor.app.ApplicationSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.app.ApplicationSearchWindow'
    ,alias: 'widget.monitorappApplicationSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '程序高级查询'
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
                            ,itemId: 'nameField'
                            ,fieldLabel: '名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'codeField'
                            ,fieldLabel: '代码'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'totalCountField'
                            ,fieldLabel: '配置实例数量'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'totalCountMaxField'
                            ,fieldLabel: '配置实例数量'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'totalCountMinField'
                            ,fieldLabel: '配置实例数量'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'aliveCountField'
                            ,fieldLabel: '当前实例数量'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'aliveCountMaxField'
                            ,fieldLabel: '当前实例数量'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'aliveCountMinField'
                            ,fieldLabel: '当前实例数量'
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
                            ,fieldLabel: '低实例告警'
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
                            ,itemId: 'enableField'
                            ,fieldLabel: '启动监控'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'thresholdValueField'
                            ,fieldLabel: '告警数量'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'thresholdValueMaxField'
                            ,fieldLabel: '告警数量'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'thresholdValueMinField'
                            ,fieldLabel: '告警数量'
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
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var totalCountField = me.down("#totalCountField");
        var totalCountMaxField = me.down("#totalCountMaxField");
        var totalCountMinField = me.down("#totalCountMinField");
        var aliveCountField = me.down("#aliveCountField");
        var aliveCountMaxField = me.down("#aliveCountMaxField");
        var aliveCountMinField = me.down("#aliveCountMinField");
        var alarmField = me.down("#alarmField");
        var enableField = me.down("#enableField");
        var thresholdValueField = me.down("#thresholdValueField");
        var thresholdValueMaxField = me.down("#thresholdValueMaxField");
        var thresholdValueMinField = me.down("#thresholdValueMinField");

        var condition = {
            name:Ext.valueFrom(nameField.getValue(), null)
            ,code:Ext.valueFrom(codeField.getValue(), null)
            ,totalCount:Ext.isNumber(totalCountField.getValue())?totalCountField.getValue():null
            ,totalCountMax:Ext.isNumber(totalCountMaxField.getValue())?totalCountMaxField.getValue():null
            ,totalCountMin:Ext.isNumber(totalCountMinField.getValue())?totalCountMinField.getValue():null
            ,aliveCount:Ext.isNumber(aliveCountField.getValue())?aliveCountField.getValue():null
            ,aliveCountMax:Ext.isNumber(aliveCountMaxField.getValue())?aliveCountMaxField.getValue():null
            ,aliveCountMin:Ext.isNumber(aliveCountMinField.getValue())?aliveCountMinField.getValue():null
            ,alarm:Ext.valueFrom(alarmField.getValue(), null)
            ,enable:Ext.valueFrom(enableField.getValue(), null)
            ,thresholdValue:Ext.isNumber(thresholdValueField.getValue())?thresholdValueField.getValue():null
            ,thresholdValueMax:Ext.isNumber(thresholdValueMaxField.getValue())?thresholdValueMaxField.getValue():null
            ,thresholdValueMin:Ext.isNumber(thresholdValueMinField.getValue())?thresholdValueMinField.getValue():null
        };

        return condition;
    }

});