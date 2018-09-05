Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskActionAddPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskActionAddPanel'
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
                                    ,listeners:{change:{fn:me.updateRecord, scope: me}}
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'memoField'
                                    ,name: 'memo'
                                    ,fieldLabel: '操作说明'
                                }
                            ]
                        }
                        ,{
                            xtype: 'codemirrorfield',
                            itemId: 'contentField',
                            padding: '5 0 0 5',
                            flex:1,
                            name: 'content',
                            fieldLabel: '脚本内容',
                            labelAlign: 'right',
                            mode:'text/x-sh'
                            //,theme:'dracula'
                            // ,flex:1
                        }
                    ]
                }
            ]
            ,listeners: {
                beforeshow: me.onBeforeShow
                ,beforehide: me.updateRecord
                ,focusleave:me.updateRecord
            }
        });

        me.callParent(arguments);
    }
    ,setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }
        this.down('form').getForm().loadRecord(model);

    }
    ,onBeforeShow:function() {
        // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
    ,updateRecord: function(){
        this.down('form').getForm().updateRecord();
        console.log("contentField:"+this.down('#contentField').getValue())
        this.down('form').getRecord().set("content", this.down('#contentField').getValue())
        this.down('form').getRecord().commit();
    }
});