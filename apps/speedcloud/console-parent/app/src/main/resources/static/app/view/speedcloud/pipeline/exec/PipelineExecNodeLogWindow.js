Ext.define('AM.view.speedcloud.pipeline.exec.PipelineExecNodeLogWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.exec.PipelineExecNodeLogWindow'
    ,requires:[
        'AM.store.speedcloud.pipeline.exec.PipelineExecInstanceStore'
        ,'AM.store.speedcloud.pipeline.task.PipelineTaskStore'
        ,'AM.ux.form.field.CodeMirror'
    ]
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'vbox'
    }
    ,title: '运行日志'
    ,maximizable: true
    ,closeAction:'hide'
    ,viewModel:true
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10

                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    },
                    items: [
                        {
                            xtype: 'codemirrorfield'
                            // xtype: 'textarea',
                            ,itemId: 'contentField'
                            ,padding: '5 0 0 5'
                            ,flex:1
                            ,name: 'content'
                            ,fieldLabel: '脚本内容'
                            ,labelAlign: 'right'
                            ,mode:'text/x-sh'
                            ,readOnly:true
                            ,reference:'contentField'
                            ,bind:'{record.log}'
                            //,theme:'dracula'
                            // ,flex:1
                            ,listeners: {
                                // focusleave: {
                                //     fn:me.updateContent
                                //     ,scope:me
                                // }
                            }
                        }
                    ]
                }
            ]
            ,listeners: {
                beforeshow: me.onBeforeShow
            }
        });

        me.callParent(arguments);
    },


    setModel: function (model) {

        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改运行实例节点信息");


    }

    ,onBeforeShow:function() {


    }
});