Ext.define('AM.view.maintenance.asset.info.AssetTypeMainPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'maintenance.asset.info.AssetTypeMainPanel'
    ,requires: [
        'AM.view.maintenance.asset.info.AssetTypePanel'
		,'AM.view.maintenance.asset.info.AssetTypeController'
        ,'AM.view.maintenance.asset.info.AssetTypeMainController'
		,'AM.model.maintenance.asset.info.AssetTypeTreeNode'
        ,'AM.view.maintenance.asset.info.AssetTypeAddWindow'
    ]
	,layout: {
		type: 'border'
	}
    ,bodyCls: 'app-dashboard'
    ,bodyPadding: '10 10'
    ,title: '资产分类'
	,store:null
	,controller: 'asset.info_AssetTypeMainController'
    ,initComponent: function() {
        var me = this;

		var assetTypeStore = Ext.create('AM.store.maintenance.asset.info.AssetTypeStore');
		assetTypeStore.proxy.extraParams={searchCondition:{}};
		assetTypeStore.load();

		var assetTreeStore = Ext.create('Ext.data.TreeStore', {autoLoad:false, model:'AM.model.maintenance.asset.info.AssetTypeTreeNode', nodeParam:'id'});

	    Ext.apply(me, {
		    items: [
			    {
		    		xtype: 'treepanel'
					,title: '资产分类'
                    ,collapsible:true
					,region: 'west'
					,width: '30%'
                    ,frame: true
					,split: true
                    ,reference: 'assetTypeTree'
					,displayField: 'name'
                    ,rootVisible: false
                	,store: assetTreeStore
					,tbar:[
                        {
                            xtype:'button',
                            text:'刷新',
                            iconCls: 'arrow_refresh',
                            handler: 'loadTypeTree'
                        }
					]
                    ,columns: [{
                        xtype: 'treecolumn',
                        text: 'Name',
                        dataIndex: 'name',
                        flex: 1,
                        sortable: true,
                        renderer: function(v, metaData, record) {
                            metaData.glyph = record.glyph;
                            return v;
                        }
                    },{
                        xtype: 'actioncolumn'
                        ,menuDisabled: true
                        ,width:30
                        ,items: [{
                            iconCls: 'x-fa fa-minus-circle'
                            ,tooltip: '删除'
                            ,handler: 'deleteRecord'
                        }]
                    },{
                        xtype: 'actioncolumn'
                        ,menuDisabled: true
                        ,width:30
                        ,items: [{
                            iconCls: 'x-fa fa-arrow-circle-down'
                            ,tooltip: '添加节点'
                            ,handler: 'addChildRecord'
                        }]
                    }]
                    ,listeners: {
                        itemclick: 'onTreepanelItemClick'
                        ,afterrender: 'loadTypeTree'
                    }

				}
                ,{
                    xtype:'container'
                    ,region: 'center'
                    ,title:'节点详情'
                    // ,bodyCls:'login-view'

                    ,layout: 'center'
                    , items :[
                        {
                            xtype: 'form'
                            ,reference: 'detailForm'
                            ,autoScroll: true
                            ,bodyPadding: 10

                            ,title: '新增节点'
                            ,width:'50%'
                            ,frame: true
                            ,fieldDefaults: {
                                labelAlign: 'right'
                                ,msgTarget: 'side'
                                ,padding: '5 0 0 5'
                                ,blankText:'该字段为必填项'
                                ,anchor: '96%'
                            }
                            ,items:[
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'numField'
                                    ,name: 'num'
                                    ,fieldLabel: '编号'
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
                                    ,allowBlank:false
                                    ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
                                    ,itemId: 'codeField'
                                    ,name: 'code'
                                    ,fieldLabel: '代码'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:true
                                    ,disabled:true
                                    ,allowBlank:false
                                    ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
                                    ,itemId: 'parentCodeField'
                                    ,name: 'parentCode'
                                    ,fieldLabel: '上级代码'
                                }
                                ,{
                                    xtype: 'numberfield'
                                    ,allowDecimals:false
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'useMonthField'
                                    ,name: 'useMonth'
                                    ,fieldLabel: '年限(月)'
                                }
                                ,{
                                    xtype: 'textfield'
                                    ,hidden: false
                                    ,readOnly:false
                                    ,allowBlank:true
                                    ,afterLabelTextTpl: []
                                    ,itemId: 'viewIndexField'
                                    ,name: 'viewIndex'
                                    ,fieldLabel: '展现顺序'
                                }
                                ,{

                                    xtype: 'textarea',
                                    anchor: '96% 70%',
                                    itemId: 'descriptionField',
                                    padding: '5 0 0 5',
                                    name: 'description',
                                    fieldLabel: '说明',
                                    labelAlign: 'top'
                                }
                            ]
                            ,fbar: [
                                ,'->',{ xtype: 'button', text: '确定'
                                    ,reference: 'submitButton'
                                    ,listeners: {
                                        click: 'onNodeSaveClick'
                                    } }
                            ]
                        }

                    ]

                }
			]

	    });
        me.add({xtype:'maintenance.asset.info.AssetTypeAddWindow',reference:'assetTypeAddWindow',listeners:{saved:'reloadStore'}})
        me.callParent(arguments);
    }



});