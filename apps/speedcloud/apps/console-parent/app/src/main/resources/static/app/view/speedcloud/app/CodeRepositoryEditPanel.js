Ext.define('AM.view.speedcloud.app.CodeRepositoryEditPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.app.CodeRepositoryEditPanel'
    ,requires:[
        'AM.store.speedcloud.config.DevelopTypeStore'

    ]
    ,autoScroll: true
    ,layout: {
        type: 'vbox'
        ,pack: 'start'
        ,align: 'stretch'
    }
    ,title: '代码库'
    ,bind:{disabled:'{!record}'}
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                data:{
                    record: null
                    // ,phantom:true
                }
                ,stores:{
                    codeRepositoryTypeStore : Ext.create("AM.store.common.SimpleConfigStore").applyCondition({configType:'CODEREPOSITORY-TYPE'}).load()

                }
                // ,formulas: {
                //     phantom: function (get) { return !get('record') || get('record').phantom; }
                // }
            }
        }, cfg)])
    }
    ,initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10
                    ,layout: {
                        type: 'vbox'
                        ,pack: 'start'
                        ,align: 'stretch'
                    }
                    ,flex: 1
                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'right'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    },
                    items: [
                        {
                            xtype:'container'
                            ,anchor: '96% 70%'
                            ,layout: {
                                type: 'table',
                                columns: 3,
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
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'nameField'
                                    ,name: 'name'
                                    ,fieldLabel: '名称'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.config.CodeRepositoryTypeStore").load()
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'code'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'typeField'
                                    ,name: 'type'
                                    ,fieldLabel: '代码库类型'
                                }
                                ,{
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.config.DevelopTypeStore").load()
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'developTypeField'
                                    ,name: 'developType'
                                    ,fieldLabel: '开发模式'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'urlField'
                                    ,name: 'url'
                                    ,fieldLabel: 'url'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'usernameField'
                                    ,name: 'username'
                                    ,fieldLabel: '用户名'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'passwordField'
                                    ,name: 'password'
                                    ,inputType: 'password'
                                    ,fieldLabel: '密码'
                                }
                            ]
                        }
                        ,{

                            xtype: 'textarea',
                            anchor: '96% 70%',

                            itemId: 'descriptionField',
                            padding: '5 0 0 5',
                            name: 'description',
                            fieldLabel: '描述',
                            labelAlign: 'top'
                            ,flex: 1
                        }
                    ]
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar'
                    ,dock: 'bottom'
                    ,ui: 'footer'
                    ,layout:'center'
                    ,items: [
                        {
                            xtype: 'button'
                            ,iconCls: 'fas fa-save'
                            ,scale:'medium'
                            ,text: '保存代码库信息'
                            ,listeners: {
                                click: {
                                    fn: me.onSaveButtonClick
                                    ,scope: me
                                }
                            }
                        }
                    ]
                }
            ]

        });

        me.callParent(arguments);
    },

    onSaveButtonClick: function (button, e, options) {
        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        //var id = this.down("#idField").getValue();

        var record = this.down('form').getForm().getRecord();


        //将form中的数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.show('操作成功', '保存代码库信息成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
            }
        });



    },


    setModel: function (model) {
        if(!model){
            this.down('form').getForm().reset();
        }else{
            console.log(2)
            this.down('form').getForm().loadRecord(model);
        }

        this.getViewModel().set('record', model);


    }


});