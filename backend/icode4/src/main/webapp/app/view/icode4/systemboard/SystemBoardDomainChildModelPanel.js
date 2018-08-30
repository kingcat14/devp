Ext.define('AM.view.icode4.systemboard.SystemBoardDomainChildModelPanel', {
    extend: 'Ext.panel.Panel',
    requires:[
	    'AM.store.icode4.system.ModelTypeStore'
	    ,'AM.store.icode4.system.ModuleStore'
	    ,'AM.store.icode4.system.SystemStore'
	    ,'AM.view.icode4.systemboard.SystemBoardDomainModelPropertyPanel'
	    ,'AM.store.icode4.system.DomainModelPropertyStore'
    ],
    autoScroll: true,
    height: 350,
    width: 600,
    layout: {
	    type: 'vbox',
	    pack: 'start',
	    align: 'stretch'
    },
    title: '子对象详细信息',
    maximizable: true,
    closeAction:'hide',
    initComponent: function () {
        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'form',
                    autoScroll: true,
                    bodyPadding: 10,
                    fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    },
                    items: [
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
                                            fieldLabel: '对象名称'
                                        }
                                        ,{
		                                    xtype: 'numberfield',
		                                    allowDecimals:false,
		                                    allowBlank:true,
		                                    itemId: 'viewIndexField',
		                                    name: 'viewIndex',
		                                    fieldLabel: '排序'
	                                    }
                                        ,{
	                                        xtype: 'textfield',
                                            allowBlank:false,
                                            afterLabelTextTpl: [
                                            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
                                            ],
                                            itemId: 'descriptionField',
                                            name: 'description',
                                            fieldLabel: '描述'
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
		                                    xtype: 'textfield',
		                                    allowBlank:false,
		                                    afterLabelTextTpl: [
			                                    '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
		                                    ],
		                                    itemId: 'codeField',
		                                    name: 'code',
		                                    fieldLabel: '对象代码'
	                                    }
                                        ,{
                                            xtype: 'combobox',
                                            store: Ext.create("AM.store.icode4.system.ModelTypeStore", {asynchronousLoad:false, autoLoad:true}),
                                            typeAhead:false,
                                            editable:false,
                                            displayField:'name',
                                            valueField:'id',
	                                        allowBlank:false,
	                                        afterLabelTextTpl: [
	                                        '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
	                                        ],
                                            itemId: 'modelTypeField',
                                            name: 'modelTypeId',
                                            fieldLabel: '对象类型'
                                        }
                                        ,{
                                            xtype: 'combobox',
                                            store: [
                                            [true,'是'],
                                            [false,'否']
                                            ],
                                            value:true,
                                            typeAhead:false,
                                            editable:false,
	                                        allowBlank:false,
	                                        afterLabelTextTpl: [
	                                        '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
	                                        ],
                                            itemId: 'persistField',
                                            name: 'persist',
                                            fieldLabel: '是否持久化'
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
                ,{
            	    xtype:'icode4.systemboard.SystemBoardDomainModelPropertyPanel'
		            ,flex:1
		            ,store:Ext.create('AM.store.icode4.system.DomainModelPropertyStore', {pageSize:10000, autoLoad:false})
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
                        },
                        {
                            xtype: 'button',
                            iconCls: 'accept',
                            text: '确定',
                            listeners: {
                                click: {
                                    fn: me.onSaveButtonClick,
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
    ,onSaveButtonClick: function (button, e, options) {
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
			        me.down('form').getForm().loadRecord(newRecord);
			        me.down('icode4\\.systemboard\\.SystemBoardDomainModelPropertyPanel').store.each(function(model){
				        model.set('domainModelId', newRecord.get('id'));
			        });
			        me.down('icode4\\.systemboard\\.SystemBoardDomainModelPropertyPanel').store.sync({
				        success:function () {
					        me.fireEvent('saved');
					        me.down('icode4\\.systemboard\\.SystemBoardDomainModelPropertyPanel').setDomainChildModel(newRecord);
					        Ext.MsgUtil.show('操作成功', '保存领域对象成功!');
				        }
				        ,failure:function () {

				        }
			        });
		        }
	        });

    }
    ,setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        //this.down('#moduleField').store.load({asynchronousLoad:false});
        this.setTitle("修改领域子对象信息");
        if(model.phantom){
            this.setTitle("新增子对象信息");
        }
        this.down('form').getForm().loadRecord(model);

        this.down('icode4\\.systemboard\\.SystemBoardDomainModelPropertyPanel').setDomainChildModel(model);
    }

});