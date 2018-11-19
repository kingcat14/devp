Ext.define('AM.view.speedcloud.app.CodeBaseInfoSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.app.CodeBaseInfoSearchWindow'
    ,alias: 'widget.speedcloudappCodeBaseInfoSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '代码库详细信息高级查询'
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
                            ,store: Ext.create("AM.store.speedcloud.app.CodeRepositoryStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'url'
                            ,valueField:'id'
                            ,itemId: 'codeRepertoryField'
                            ,fieldLabel: '代码库'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'languageField'
                            ,fieldLabel: '开发语言'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'languageLevelField'
                            ,fieldLabel: '语言级别'
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
        var codeRepertoryField = me.down("#codeRepertoryField");
        var languageField = me.down("#languageField");
        var languageLevelField = me.down("#languageLevelField");

        var condition = {
            codeRepertory:Ext.isEmpty(codeRepertoryField.getValue())?null:codeRepertoryField.getValue()
            ,language:Ext.isEmpty(languageField.getValue())?null:languageField.getValue()
            ,languageLevel:Ext.isEmpty(languageLevelField.getValue())?null:languageLevelField.getValue()
        };

        return condition;
    }

});