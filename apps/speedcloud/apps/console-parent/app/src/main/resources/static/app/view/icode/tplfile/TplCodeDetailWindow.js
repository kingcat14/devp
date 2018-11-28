Ext.define('AM.view.icode.tplfile.TplCodeDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'icode.tplfile.TplCodeDetailWindow'
    ,requires:[
        'AM.ux.form.field.CodeMirror'
    ]
    ,autoScroll: true
    ,height: '70%'
    ,width: '70%'
    ,layout: {type: 'fit'}
    ,title: '代码模板信息'
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    // ,bodyPadding: 10
                    ,layout:'border'
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 5 0 5'
                    },
                    items: [
                        {
                            xtype:'panel'
                            ,region:'west'
                            ,ui: 'light'
                            ,collapsible: true
                            ,width:'25%'
                            ,title:'基本信息'
                            ,split:true
                            ,layout: {
                                type: 'table',
                                columns: 1,
                                tableAttrs: {
                                    style: {
                                        width: '100%'
                                    }
                                }
                            }
                            ,defaults:{width:'100%'}
                            ,items:[
                                {
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'codeField'
                                    ,name: 'code'
                                    ,editable:false
                                    ,fieldLabel: '模板代码'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '模板名称'
                                    ,editable:false
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'typeField'
                                    ,name: 'type'
                                    ,fieldLabel: '模板类型'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'fileNameField'
                                    ,name: 'fileName'
                                    ,fieldLabel: '文件名称'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'filePathField'
                                    ,name: 'filePath'
                                    ,fieldLabel: '文件路径'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'acceptModelTypeField'
                                    ,name: 'acceptModelType'
                                    ,fieldLabel: '接受的模型类型'
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
                                    ,hidden: false
                                    ,readOnly:true
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'overridableField'
                                    ,name: 'overridable'
                                    ,fieldLabel: '是否可覆盖'
                                }
                            ]

                        }
                        ,{
                            panel: 'container'
                            ,title:'模板内容'
                            ,region:'center'
                            ,layout:'fit'
                            ,ui: 'light'
                            ,items:[
                                {
                                    xtype: 'codemirrorfield'
                                    ,region:'center'
                                    ,itemId: 'contentField'
                                    ,name: 'content'
                                }
                            ]
                        }

                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("代码模板信息");

        this.down('form').getForm().loadRecord(model);
        this.down('#contentField').setModeByFileName(model.get('fileName'));

    }
});