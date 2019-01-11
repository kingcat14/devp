Ext.define('AM.view.monitor.supporter.SupporterAppRelationSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'monitor.supporter.SupporterAppRelationSearchWindow'
    ,alias: 'widget.monitorsupporterSupporterAppRelationSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '支持应用高级查询'
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
                            ,store: Ext.create("AM.store.monitor.supporter.SupporterStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'supporterField'
                            ,fieldLabel: '运维人员'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.monitor.app.ApplicationStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'applicationField'
                            ,fieldLabel: '支持程序'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'notificationTypeField'
                            ,fieldLabel: '接收通知方式'
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
        var supporterField = me.down("#supporterField");
        var applicationField = me.down("#applicationField");
        var notificationTypeField = me.down("#notificationTypeField");

        var condition = {
            supporter:Ext.valueFrom(supporterField.getValue(), null)
            ,application:Ext.valueFrom(applicationField.getValue(), null)
            ,notificationType:Ext.valueFrom(notificationTypeField.getValue(), null)
        };

        return condition;
    }

});