Ext.define('AM.view.speedcloud.deploy.DevpSysDpySchemeEditResourcePropertyPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.deploy.DevpSysDpySchemeEditResourcePropertyPanel'
    , title: '属性'
    , layout: 'border'
    , requires: [
        'AM.store.speedcloud.deploy.DevpSysDpyResourcePropertyStore'
    ]
    ,viewModel:{
        data:{phantom:false}
    }
    ,referenceHolder:true

    ,initComponent: function() {

        var me = this;
        me.getViewModel().setStores({
            propertyStore : Ext.create('AM.store.speedcloud.deploy.DevpSysDpyResourcePropertyStore').applyCondition({resource:-999}).load()
        })
        Ext.apply(me, {
            bind:{title:'{phantom}'}
            ,items: [
                {
                    xtype: 'grid'
                    ,region:'center'
                    ,bind:{store:'{propertyStore}'}
                    ,columnLines: true
                    ,columns: [
                        {
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '属性名称'
                            ,flex:1
                            ,editor: {
                                xtype: 'textfield'
                            }
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '属性代码'
                            ,hidden:true
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'value'
                            ,text: '属性值'
                            ,flex:1
                            ,editor: {
                                xtype: 'textfield'
                            }
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'delete'
                                ,tooltip: '删除'
                                ,handler: function(value, rowIndex, colIndex, item, event, record) {
                                    console.log(record);
                                    record.erase({});
                                }
                            }]
                        }
                    ]
                    ,viewConfig: {

                    }
                    ,dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button'
                                    ,iconCls: 'fas fa-sync-alt'
                                    ,text: '刷新'
                                    ,listeners: {
                                        click: 'onSimpleSearchButtonClick'
                                    }
                                }
                                ,{
                                    xtype: 'button'
                                    ,iconCls: 'x-fa fa-plus-circle'
                                    ,text: '新增'
                                    ,listeners: {
                                        click: me.onAddButtonClick
                                        ,scope:me
                                    }
                                }

                            ]
                        }
                    ]
                    ,plugins: [
                        {
                            ptype: 'cellediting'
                            ,clicksToEdit: 1
                        }
                    ]
                    ,listeners: {
                        edit:{
                            fn: function(editor, e) {
                                //e.record.commit();
                                e.record.save();
                            }
                        }
                    }
                }
            ]
            ,listeners: {
            	beforeshow: {
                    fn: me.onBeforeShow
                    ,scope: me
                }
              	,beforehide: {
                	fn: me.onPanelBeforeHide
                  	,scope: me
				}
			}
        });


        me.callParent(arguments);
    }

    ,onAddButtonClick: function(button, e, options) {

        var me = this;
        console.log(me.getViewModel())
        console.log("phantom:"+me.getViewModel().get('phantom'))

        var resource = me.getViewModel().get('resource');

        // Create a model instance
        var r = Ext.create('AM.model.speedcloud.deploy.DevpSysDpyResourceProperty', {
            resource:resource.get('id')
        });

        var propertyStore = me.getViewModel().getStore('propertyStore');

        propertyStore.insert(propertyStore.getCount(), r);

        me.down('grid').findPlugin('cellediting').startEdit(r, 0);


    }
    ,onBeforeShow:function(abstractcomponent, options) {

	    this.getViewModel().getStore('propertyStore').reload({scope: this,callback: function(){}});
    }
});