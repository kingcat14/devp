Ext.define('AM.view.speedcloud.pipeline.PipelineCodeRepositoryRelationSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.PipelineCodeRepositoryRelationSearchWindow'
    ,alias: 'widget.speedcloudpipelinePipelineCodeRepositoryRelationSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '流水线代码库关联高级查询'
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
                            ,itemId: 'codeRepositoryField'
                            ,fieldLabel: '代码库'
                        }

                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.pipeline.PipelineStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'pipelineField'
                            ,fieldLabel: '流水线'
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
                            ,itemId: 'autoStartField'
                            ,fieldLabel: '提交代码触发流水线'
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
        var codeRepositoryField = me.down("#codeRepositoryField");
        var pipelineField = me.down("#pipelineField");
        var autoStartField = me.down("#autoStartField");

        var condition = {
            codeRepository:Ext.isEmpty(codeRepositoryField.getValue())?null:codeRepositoryField.getValue()
            ,pipeline:Ext.isEmpty(pipelineField.getValue())?null:pipelineField.getValue()
            ,autoStart:Ext.isEmpty(autoStartField.getValue())?null:autoStartField.getValue()
        };

        return condition;
    }

});