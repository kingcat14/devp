Ext.define('AM.view.icode.project.ComponentLocalLocationSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'icode.project.ComponentLocalLocationSearchWindow'
    ,alias: 'widget.icodeprojectComponentLocalLocationSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '组件本地路径高级查询'
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
                            ,itemId: 'accountIdField'
                            ,fieldLabel: '用户id'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'accountIdMaxField'
                            ,fieldLabel: '用户id'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'accountIdMinField'
                            ,fieldLabel: '用户id'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.icode.project.ComponentStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'code'
                            ,valueField:'id'
                            ,itemId: 'componentField'
                            ,fieldLabel: '组件'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'locationField'
                            ,fieldLabel: '本地路径'
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
                            ,iconCls: 'fas fa-search'
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
        var accountIdField = me.down("#accountIdField");
        var accountIdMaxField = me.down("#accountIdMaxField");
        var accountIdMinField = me.down("#accountIdMinField");
        var componentField = me.down("#componentField");
        var locationField = me.down("#locationField");

        var condition = {
            accountId:Ext.isNumber(accountIdField.getValue())?accountIdField.getValue():null
            ,accountIdMax:Ext.isNumber(accountIdMaxField.getValue())?accountIdMaxField.getValue():null
            ,accountIdMin:Ext.isNumber(accountIdMinField.getValue())?accountIdMinField.getValue():null
            ,component:Ext.valueFrom(componentField.getValue(), null)
            ,location:Ext.valueFrom(locationField.getValue(), null)
        };

        return condition;
    }

});