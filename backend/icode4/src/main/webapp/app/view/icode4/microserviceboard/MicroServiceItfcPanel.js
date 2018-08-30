Ext.define('AM.view.icode4.microserviceboard.MicroServiceItfcPanel', {
    extend: 'Ext.panel.Panel',
    requires:[
	    'AM.store.icode4.system.ModelTypeStore'
	    ,'AM.store.icode4.system.ModuleStore'
	    ,'AM.store.icode4.system.SystemStore'
	    ,'AM.view.icode4.microserviceboard.MicroServiceItfcParametersPanel'
	    ,'AM.store.icode4.system.DomainModelPropertyStore'

    ],
    autoScroll: true,
    layout: {
	    type: 'vbox',
	    pack: 'start',
	    align: 'stretch'
    },
    title: '微服务接口详细信息',
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
							            fieldLabel: '接口名称'
						            }
						            ,{
							            xtype: 'textfield',
							            allowBlank:false,
							            afterLabelTextTpl: [
								            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
							            ],
							            itemId: 'urlField',
							            name: 'url',
							            fieldLabel: '接口地址'
						            }
						            ,{
							            xtype: 'combobox',
							            store: Ext.create("AM.store.icode4.microservice.TransModelStore"),
							            typeAhead:false,
							            reference:'requestField',
							            editable:false,
							            displayField:'name',
							            valueField:'id',
							            allowBlank:true,
							            itemId: 'requestField',
							            name: 'requestId',
							            fieldLabel: '请求对象'
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
							            fieldLabel: '接口代码'
						            }
						            ,{
							            xtype: 'combobox',
							            store: [
								            ['POST','POST']
								            ,['GET','GET']
								            ,['PUT','PUT']
								            ,['DELETE','DELETE']
							            ],
							            allowBlank:false,
							            afterLabelTextTpl: [
								            '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'
							            ],
							            itemId: 'methodField',
							            name: 'method',
							            fieldLabel: '接口方法'
						            }
						            ,{
							            xtype: 'combobox',
							            store: Ext.create("AM.store.icode4.microservice.TransModelStore", {pageSize:10000}),
							            typeAhead:false,
							            editable:false,
							            displayField:'name',
							            valueField:'id',
							            allowBlank:true,
							            itemId: 'responseField',
							            name: 'responseId',
							            fieldLabel: '返回对象'
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
			            fieldLabel: '接口描述',
			            labelAlign: 'top'
		            }
	            ]
	            }
	            ,{
		            xtype:'icode4.microserviceboard.MicroServiceItfcParametersPanel'
		            ,flex:1
		            ,store:Ext.create('AM.store.icode4.microservice.MicroServiceItfcParametersStore', {pageSize:10000, autoLoad:false})
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


			        me.down('form').getForm().loadRecord(newRecord);
			        me.down('icode4\\.microserviceboard\\.MicroServiceItfcParametersPanel').store.each(function(model){
				        model.set('domainModelId', newRecord.get('id'));
			        });
			        me.down('icode4\\.microserviceboard\\.MicroServiceItfcParametersPanel').store.sync({
				        success:function () {
					        me.fireEvent('saved');
					        me.down('icode4\\.microserviceboard\\.MicroServiceItfcParametersPanel').setModel(newRecord);
					        Ext.MsgUtil.show('操作成功', '保存服务接口成功!');
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
        this.setTitle("修改微服务接口信息");
        if(model.phantom){
            this.setTitle("新增微服务接口信息");
        }
        this.down('form').getForm().loadRecord(model);

        console.log('moduleId:'+model.get('moduleId'))

        var searchCondition = {moduleId:model.get('moduleId')}

        var responseStore = this.down('#responseField').getStore();

		//这里这样处理，是为了避免新增属性的时候把searchConditon带上了
		responseStore.on('beforeload', function(){
			responseStore.proxy.extraParams = {searchCondition: searchCondition};
		})
		responseStore.on('load', function(){
			responseStore.proxy.extraParams = null;
		})
		responseStore.proxy.isSynchronous = true;
		// this.store.proxy.extraParams = {searchCondition: searchCondition};
		responseStore.load({
			params: {
				start: 0
				,page: 0
			}
		});

		var requestTransModelStore = this.down('#requestField').getStore();

		//这里这样处理，是为了避免新增属性的时候把searchConditon带上了
		requestTransModelStore.on('beforeload', function(){
			requestTransModelStore.proxy.extraParams = {searchCondition: searchCondition};
		})
		requestTransModelStore.on('load', function(){
			requestTransModelStore.proxy.extraParams = null;
		})
		requestTransModelStore.proxy.isSynchronous = true;
		// this.store.proxy.extraParams = {searchCondition: searchCondition};
		requestTransModelStore.load({
			params: {
				start: 0
				,page: 0
			}
		});


		this.down('icode4\\.microserviceboard\\.MicroServiceItfcParametersPanel').setModel(model);
	}

});