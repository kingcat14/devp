Ext.define('AM.view.speedcloud.app.AppBaseInfoEditPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.app.AppBaseInfoEditPanel'
    ,requires:[
        'AM.store.speedcloud.project.ProjectStore'
        ,'AM.store.speedcloud.app.ApplicationTypeStore'
    ]
    ,autoScroll: true
    ,layout: {
        type: 'vbox'
        ,pack: 'start'
        ,align: 'stretch'
    }
    ,title: '应用（组件）基础信息'
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
                ,formulas: {
                    phantom: function (get) { return !get('record') || get('record').phantom; }
                }
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
                    ,width:'100%'
                    ,flex:1
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    },
                    items: [
                        {
                            xtype:'container'
                            // ,anchor: '96%'
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
                                    xtype: 'combobox'
                                    ,store: Ext.create("AM.store.speedcloud.app.ApplicationTypeStore").load()
                                    ,typeAhead:false
                                    ,editable:false
                                    ,displayField:'name'
                                    ,valueField:'id'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'typeField'
                                    ,name: 'type'
                                    ,fieldLabel: '应用类型'
                                }
                                ,{
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
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'codeField'
                                    ,name: 'code'
                                    ,fieldLabel: '代码'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'statusField'
                                    ,name: 'status'
                                    ,fieldLabel: '状态'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'registTimeField'
                                    ,name: 'registTime'
                                    ,fieldLabel: '注册时间'
                                }
                            ]

                        }
                        ,{

                            xtype: 'textarea',
                            // anchor: '96% 70%',
                            itemId: 'descriptionField',
                            padding: '5 0 0 5',
                            name: 'description',
                            fieldLabel: '描述',
                            labelAlign: 'top',
                            flex:1
                        }
                    ]
                }
            ]
            ,dockedItems: [
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
                            ,text: '保存详情'
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
                Ext.MsgUtil.notification('操作成功', '保存应用（系统）成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved', newRecord);
            }
        });

    }
    ,setModel: function (model) {
        if(!model){
            this.down('form').getForm().reset();
        }else{
            console.log(2)
            this.down('form').getForm().loadRecord(model);
        }

        this.getViewModel().set('record', model);


    }
});