Ext.define('AM.view.speedcloud.pipeline.PipelineSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineSearchWindow'
    ,alias: 'widget.speedcloudpipelinePipelineSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '流水线高级查询'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;
        var pipelineTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineTypeStore.proxy.isSynchronous = true;
        pipelineTypeStore.proxy.extraParams={searchCondition:{configType:'PIPELINE-TYPE'}};
        pipelineTypeStore.load();

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
                            ,fieldLabel: '流水线名称'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: pipelineTypeStore
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'displayName'
                            ,valueField:'value'
                            ,itemId: 'typeField'
                            ,fieldLabel: '类型'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.project.ProjectStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'projectField'
                            ,fieldLabel: '所属产品'
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
        var projectField = me.down("#projectField");

        var condition = {
            name:Ext.isEmpty(nameField.getValue())?null:nameField.getValue()
            ,type:Ext.isEmpty(typeField.getValue())?null:typeField.getValue()
            ,project:Ext.isEmpty(projectField.getValue())?null:projectField.getValue()
        };

        return condition;
    }

});