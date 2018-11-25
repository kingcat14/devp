Ext.define('AM.view.icode.tplfileboard.TplFileBoardTplCodePanel', {
    extend: 'Ext.panel.Panel',
    requires:[
    	'AM.store.icode.tplfile.TplSetStore'
    	,'AM.store.icode.tplfile.TplCodeStore'
		,'AM.ux.form.field.CodeMirror'
    ],
    autoScroll: true,
    layout: {
        type: 'fit'
    },
    title: '模板信息',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
	            {
		            xtype: 'form'
		            ,autoScroll: true
		            ,bodyPadding: '10 10 0 10'
		            ,fieldDefaults: {
						labelAlign: 'top'
						,msgTarget: 'side'
						,padding: '5 0 0 5'
						,blankText:'该字段为必填项'
						,anchor: '96%'
					}
					,layout:'vbox'
		            ,items: [
		            {
			            xtype: 'fieldset'
						,title:'基础信息'
						,collapsible:true
                        ,width:'100%'
			            ,layout: {type: 'column'}
			            ,items: [
				            {
					            xtype: 'container'
					            ,columnWidth: 0.5
					            ,layout: {type: 'anchor'}
					            ,items: [
						            {
							            xtype: 'hiddenfield'
							            ,anchor: '100%'
							            ,itemId: 'idField'
							            ,name: 'id'
							            ,fieldLabel: 'Label'
						            }
						            ,{
							            xtype: 'textfield'
							            ,allowBlank:false
							            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
							            ,itemId: 'nameField'
							            ,name: 'name'
							            ,fieldLabel: '模板名称'
						            }
						            ,{
							            xtype: 'textfield'
							            ,value:true
							            ,typeAhead:false
							            ,editable:true
							            ,allowBlank:false
							            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
							            ,itemId: 'typeField'
							            ,name: 'type'
							            ,fieldLabel: '模板类型'
						            }
						            ,{
							            xtype: 'textfield'
							            ,allowBlank:false
							            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
							            ,itemId: 'filePathField'
							            ,name: 'filePath'
							            ,fieldLabel: '文件路径'
						            }
						            ,{
							            xtype: 'combobox'
							            ,store: [[true,'是'], [false, '否']]
							            ,value:true
							            ,typeAhead:false
							            ,editable:false
							            ,allowBlank:false
							            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
							            ,itemId: 'overridableField'
							            ,name: 'overridable'
							            ,fieldLabel: '可覆盖'
						            }

					            ]
				            },
				            {
					            xtype: 'container'
					            ,columnWidth: 0.5
					            ,layout: {type: 'anchor'}
					            ,items: [
						            {
							            xtype: 'textfield'
							            ,allowBlank:false
							            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
							            ,itemId: 'codeField'
							            ,name: 'code'
							            ,fieldLabel: '模板代码'
						            }

						            ,{
							            xtype: 'combobox'
							            ,store: Ext.create("AM.store.icode.domain.ModelTypeStore" ,{autoLoad:true, asynchronousLoad:false})
							            ,typeAhead:false
							            ,editable:false
							            ,displayField:'name'
							            ,valueField:'id'
							            ,allowBlank:false
							            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
							            ,itemId: 'acceptModelTypeField'
							            ,name: 'acceptModelTypeId'
							            ,fieldLabel: '接收模型类型'
						            }
						            ,{
							            xtype: 'textfield'
							            ,allowBlank:false
							            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
							            ,itemId: 'fileNameField'
							            ,name: 'fileName'
							            ,fieldLabel: '文件名'
						            }
					            ]
				            }
			            ]
		            }
		            ,{

			            xtype: 'codemirrorfield'
						,width:'100%'
			            //anchor: '98% 70%',
						,flex:1
							// ,height:200
			            ,itemId: 'contentField'
			            ,padding: '5 0 0 5'
			            ,name: 'content'
			            ,fieldLabel: '文件内容'
			            ,labelAlign: 'top'
						,fieldStyle:'font-family: monospace;'
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
                            ,text: '确定'
                        	,scale: 'medium'
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

        this.setTitle("修改模板信息");
        if(model.phantom){
            this.setTitle("新增模板信息");
        }
        this.down('form').getForm().loadRecord(model);
        this.down('#contentField').setModeByFileName(model.get('fileName'));



    }

});