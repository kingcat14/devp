Ext.define('AM.view.icode.project.ComponentSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'icode.project.ComponentSearchWindow'
    ,alias: 'widget.icodeprojectComponentSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '组件高级查询'
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
                            ,fieldLabel: '组件名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'codeField'
                            ,fieldLabel: '组件代码'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'basePackageField'
                            ,fieldLabel: '基础包'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'descriptionField'
                            ,fieldLabel: '描述'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.icode.tplfile.TplSetStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'tplSetField'
                            ,fieldLabel: '代码模板'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'numberField'
                            ,fieldLabel: '组件编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'numberMaxField'
                            ,fieldLabel: '组件编号'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'numberMinField'
                            ,fieldLabel: '组件编号'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'groupCodeField'
                            ,fieldLabel: '分组代码'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.icode.project.ProductStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'productName'
                            ,valueField:'id'
                            ,itemId: 'productField'
                            ,fieldLabel: '所属产品'
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
        var nameField = me.down("#nameField");
        var codeField = me.down("#codeField");
        var basePackageField = me.down("#basePackageField");
        var descriptionField = me.down("#descriptionField");
        var tplSetField = me.down("#tplSetField");
        var numberField = me.down("#numberField");
        var numberMaxField = me.down("#numberMaxField");
        var numberMinField = me.down("#numberMinField");
        var groupCodeField = me.down("#groupCodeField");
        var productField = me.down("#productField");

        var condition = {
            name:Ext.valueFrom(nameField.getValue(), null)
            ,code:Ext.valueFrom(codeField.getValue(), null)
            ,basePackage:Ext.valueFrom(basePackageField.getValue(), null)
            ,description:Ext.valueFrom(descriptionField.getValue(), null)
            ,tplSet:Ext.valueFrom(tplSetField.getValue(), null)
            ,number:Ext.isNumber(numberField.getValue())?numberField.getValue():null
            ,numberMax:Ext.isNumber(numberMaxField.getValue())?numberMaxField.getValue():null
            ,numberMin:Ext.isNumber(numberMinField.getValue())?numberMinField.getValue():null
            ,groupCode:Ext.valueFrom(groupCodeField.getValue(), null)
            ,product:Ext.valueFrom(productField.getValue(), null)
        };

        return condition;
    }

});