Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskActionEditPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskActionEditPanel'
    ,requires:[
        'AM.store.common.SimpleConfigStore'
        ,'AM.store.speedcloud.pipeline.task.PipelineTaskActionTypeStore'
        ,'AM.ux.form.field.CodeMirror'

    ]
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '添加新操作'
    ,maximizable: true
    ,closeAction: 'hide'
    ,referenceHolder:true
    ,viewModel:true
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,width:'100%'
                    ,layout:'vbox'
                    ,fieldDefaults: {
                        labelAlign: 'right'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,width:'100%'
                        ,maxWidth:600
                    }
                    ,defaults:{width:'100%'}
                    ,items: [
                        {
                            xtype:'container'
                            // ,width: '100%'
                            ,defaults:{width:'100%',maxWidth:600}
                            ,fieldDefaults: {maxWidth:600}
                            ,items:[
                                {
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '操作名称'
                                    ,bind:'{record.name}'
                                    //,listeners:{change:{fn:me.updateRecord, scope: me}}
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'memoField'
                                    ,name: 'memo'
                                    ,bind:'{record.memo}'
                                    ,fieldLabel: '操作说明'
                                }
                            ]
                        }
                        ,{
                            xtype: 'codemirrorfield',
                            // xtype: 'textarea',
                            itemId: 'contentField',
                            padding: '5 0 0 5',
                            flex:1,
                            name: 'content',
                            fieldLabel: '脚本内容',
                            labelAlign: 'right',
                            mode:'text/x-sh'
                            ,reference:'contentField'
                            ,bind:'{record.content}'
                            //,theme:'dracula'
                            // ,flex:1
                            ,listeners: {
                                focusleave: {
                                    fn:me.updateContent
                                    ,scope:me
                                }
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
    ,updateContent: function(){

        var contentField =  this.lookup('contentField')

        this.viewModel.data.record.set("content", contentField.getValue())

    }

});