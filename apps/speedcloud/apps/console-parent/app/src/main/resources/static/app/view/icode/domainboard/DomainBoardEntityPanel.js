Ext.define('AM.view.icode.domainboard.DomainBoardEntityPanel', {
    extend: 'Ext.panel.Panel'
	,xtype: 'icode.domainboard.DomainBoardEntityPanel'
    ,requires:[
	    'AM.view.icode.domainboard.DomainBoardEntityPropertyPanel'
	    ,'AM.store.icode.domain.EntityPropertyStore'
    ]
    ,layout: {
	    type: 'vbox'
	    ,pack: 'start'
	    ,align: 'stretch'
    }
    ,title: '领域对象详细信息'
	,referenceHolder: true
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
            	data:{
            		component: null
					,entity: null
				}
            }
        }, cfg)])
    }
	,initComponent: function () {
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
							            fieldLabel: '对象代码'
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
	            ,{
            		xtype:'tabpanel'
                    ,flex:1
					,items:[
                        {
                            xtype:'icode.domainboard.DomainBoardEntityPropertyPanel'
                            ,title: '持久化属性'
							,reference: 'domainBoardEntityPropertyPanel'
                            ,store:Ext.create('AM.store.icode.domain.EntityPropertyStore', {pageSize:10000, autoLoad:false})
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
                        },
                        {
                            xtype: 'button',
                            iconCls: 'fas fa-save',
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

        var record = this.down('form').getForm().getRecord();

        //将form中得数据刷进record
        this.down('form').getForm().updateRecord(record);


        var propertyAllValid = true, propertyErrorMsg='';
        var domainBoardEntityPropertyPanel = me.lookupReference('domainBoardEntityPropertyPanel');
        domainBoardEntityPropertyPanel.store.each(function(model){
            if(model.get('type').length > 15 && Ext.isEmpty(model.get('relatedEntityPropertyId'))){
                propertyAllValid = false;
                propertyErrorMsg = "属性["+model.get('name')+"]引用了其他对象, 必须设置引用对象属性";
            }
        });

        if(!propertyAllValid){
        	Ext.MessageBox.alert('属性异常', propertyErrorMsg);
        	return ;
		}

        record.save({
			success: function (newRecord) {
				me.down('form').getForm().loadRecord(newRecord);
                var domainBoardEntityPropertyPanel = me.lookupReference('domainBoardEntityPropertyPanel');
                // var domainBoardEntityPropertyPanel = me.down('icode\\.domainboard\\.DomainBoardEntityPropertyPanel');

				domainBoardEntityPropertyPanel.store.each(function(model){
					model.set('entity', newRecord.get('id'));
				});
                domainBoardEntityPropertyPanel.store.sync({
				// me.down('icode\\.domainboard\\.DomainBoardEntityPropertyPanel').store.sync({
					success:function () {

						me.down('icode\\.domainboard\\.DomainBoardEntityPropertyPanel').setEntity(newRecord);

						Ext.MsgUtil.show('操作成功', '同步领域对象属性成功!');
					}
					,failure:function () {
                        Ext.MessageBox.show({title: '操作失败', msg: '同步领域对象属性失败', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
					}
				});
                me.fireEvent('saved', {record:newRecord});
				Ext.MsgUtil.show('操作成功', '保存领域对象成功!');
			}
		});

    }
    ,setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        //this.down('#moduleField').store.load({asynchronousLoad:false});
        this.setTitle("修改领域对象信息");

        if(model.phantom){
            this.setTitle("新增领域对象信息");
        }

        this.down('form').getForm().loadRecord(model);

        var component = this.getViewModel().get('component');
        if(!component){
        	Ext.MessageBox.alert('模型设置异常', "必须先设置所属组件,请检查代码")
		}
        this.lookupReference('domainBoardEntityPropertyPanel').getViewModel().set('component', component);
        this.lookupReference('domainBoardEntityPropertyPanel').setEntity(model);
        // this.down('icode\\.domainboard\\.DomainBoardEntityPropertyPanel').setEntity(model);
    }

});