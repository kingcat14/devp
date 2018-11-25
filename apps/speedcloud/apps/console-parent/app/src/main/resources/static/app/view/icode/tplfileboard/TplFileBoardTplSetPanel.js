Ext.define('AM.view.icode.tplfileboard.TplFileBoardTplSetPanel', {
    extend: 'Ext.panel.Panel',
    requires:[
    'AM.store.icode.tplfile.TplSetStore'
    ,'AM.store.icode.tplfile.TplCodeStore'
    ],
    autoScroll: true,
    layout: {
        type: 'fit'
    },
    title: '模板集合信息',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
	            {
		            xtype: 'form'
		            ,autoScroll: true
		            ,bodyPadding: 10
		            ,fieldDefaults: {
		            labelAlign: 'top'
		            ,msgTarget: 'side'
		            ,padding: '5 0 0 5'
		            ,blankText:'该字段为必填项'
		            ,anchor: '96%'
	            }
		            ,items: [
		            {
			            xtype: 'container',
			            layout: {
				            type: 'column'
			            },
			            items: [
				            {
					            xtype: 'container',
					            columnWidth: 0.5,
					            layout: {
						            type: 'anchor'
					            },
					            items: [
						            {
							            xtype: 'hiddenfield',
							            anchor: '100%',
							            itemId: 'idField',
							            name: 'id',
							            fieldLabel: 'Label'
						            }
						            ,{
							            xtype: 'textfield',
							            allowBlank:false,
							            afterLabelTextTpl: [
								            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
							            ],
							            itemId: 'nameField',
							            name: 'name',
							            fieldLabel: '集合名称'
						            }
						            ,{
							            xtype: 'textfield',
							            allowBlank:true,
							            itemId: 'typeField',
							            name: 'type',
							            fieldLabel: '集合类型'
						            }

					            ]
				            },
				            {
					            xtype: 'container',
					            columnWidth: 0.5,
					            layout: {
						            type: 'anchor'
					            },
					            items: [
						            {
							            xtype: 'hiddenfield',
							            anchor: '100%',
							            itemId: 'versionField',
							            name: 'version',
							            fieldLabel: 'Label'
						            }
						            ,{
							            xtype: 'textfield',
							            allowBlank:false,
							            afterLabelTextTpl: [
								            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
							            ],
							            itemId: 'codeField',
							            name: 'code',
							            fieldLabel: '集合代码'
						            }
						            ,{
							            xtype: 'combobox'
										,hidden:true
							            ,store: Ext.create("AM.store.icode.tplfile.TplSetStore")
							            ,typeAhead:false
							            ,editable:false
							            ,displayField:'name'
							            ,valueField:'id'
							            ,allowBlank:true
							            ,itemId: 'parentField'
							            ,name: 'parentId'
							            ,fieldLabel: '上级集合'
						            }
					            ]
				            }
			            ]
		            }
		            ,{

			            xtype: 'textarea',
			            anchor: '98% 70%',
			            itemId: 'descriptionField',
			            padding: '5 0 0 5',
			            name: 'description',
			            fieldLabel: '描述',
			            labelAlign: 'top'
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
                        // {
                        //     xtype: 'tbfill'
                        // },
                        {
                            xtype: 'button'
                            ,iconCls: 'fas fa-save'
                            ,scale: 'medium'
                            ,text: '确定'
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

        var id = this.down("#idField").getValue();

        var record = this.down('form').getForm().getRecord();


        //将form中得数据刷进record
        this.down('form').getForm().updateRecord(record);
        record.save({
            success: function (newRecord) {
                Ext.MsgUtil.show('操作成功', '保存模块成功!');
                me.down('form').getForm().loadRecord(newRecord);
                me.fireEvent('saved');
            }
        });


    },

    setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.setTitle("修改模板集合信息");
        if(model.phantom){
            this.setTitle("新增模板集合信息");
        }
        this.down('form').getForm().loadRecord(model);
    }

});