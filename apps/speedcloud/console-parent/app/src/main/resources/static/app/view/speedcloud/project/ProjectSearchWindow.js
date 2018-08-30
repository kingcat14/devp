Ext.define('AM.view.speedcloud.project.ProjectSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.project.ProjectSearchWindow'
    ,alias: 'widget.speedcloudprojectProjectSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '项目高级查询'
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
                            ,itemId: 'typeField'
                            ,fieldLabel: '类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'scopeField'
                            ,fieldLabel: '公开性'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'descriptionField'
                            ,fieldLabel: '描述'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'parentField'
                            ,fieldLabel: '上级项目'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.project.ProjectSetStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'projectSetField'
                            ,fieldLabel: '所属项目集'
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
        var nameField = me.down("#nameField");
        var typeField = me.down("#typeField");
        var scopeField = me.down("#scopeField");
        var descriptionField = me.down("#descriptionField");
        var parentField = me.down("#parentField");
        var projectSetField = me.down("#projectSetField");
        var projectSetMaxField = me.down("#projectSetMaxField");
        var projectSetMinField = me.down("#projectSetMinField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,scope:Ext.isEmpty(scopeField.getValue())?null:scopeField.getValue()
            ,description:Ext.isEmpty(descriptionField.getValue())?null:descriptionField.getValue()
            ,parent:Ext.isEmpty(parentField.getValue())?null:parentField.getValue()
            ,projectSet:Ext.isEmpty(projectSetField.getValue())?null:projectSetField.getValue()
        };

        return condition;
    }

});