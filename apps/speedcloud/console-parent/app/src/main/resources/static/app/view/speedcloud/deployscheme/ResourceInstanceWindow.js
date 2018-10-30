Ext.define('AM.view.speedcloud.deployscheme.ResourceInstanceWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.deployscheme.ResourceInstanceWindow'
    ,requires:[
        'AM.store.common.SimpleConfigStore'
        ,'AM.store.speedcloud.deployscheme.SchemeStore'
        ,'AM.store.speedcloud.deployscheme.ResourceStore'
        ,'AM.model.asset.asset.config.AssetTypeTreeNode'
        ,'AM.store.speedcloud.deployscheme.ResourceInstanceRelationStore'
    ]
    ,autoScroll: true
    ,layout: {
        type: 'border'
    }
    ,title: '添加新方案资源关联实例'
    ,maximizable: true
    ,closeAction: 'destroy'
    ,constructor:function(cfg){
        var me = this;
        cfg = cfg || {}

        me.callParent([Ext.apply({
            viewModel : {
                data:{
                    resource:null
                }
                ,stores:{
                    assetTypeStore:Ext.create('AM.store.asset.asset.config.AssetTypeStore', {pageSize:1000}).applyCondition({assetCategoryCode:'-999'}).load()
                    ,assetCmdbStore:Ext.create('AM.store.asset.asset.info.AssetCmdbStore').load()
                    ,assetCategoryStore:Ext.create('AM.store.asset.asset.config.AssetCategoryStore', {pageSize:1000}).load()
                    ,resourceInstanceRelationStore:Ext.create('AM.store.asset.asset.config.AssetCategoryStore', {pageSize:1000}).applyCondition({resource:'-999'}).load()
                    ,relationAssetStore:{model:'AM.model.asset.asset.info.AssetCmdb'}
                }
            }
        }, cfg)])
    }
    ,initComponent: function () {

        var me = this;

        Ext.apply(me, {
            items: [
                {
                    xtype: 'grid'
                    ,width: '50%'
                    ,region:'west'
                    ,split:true
                    ,reference: 'assetGridPanel'
                    ,displayField: 'name'
                    ,rootVisible: false
                    ,bind:{store:'{assetCmdbStore}'}
                    ,tbar:[
                        {
                            xtype: 'combobox'
                            ,bind:{store: '{assetCategoryStore}'}
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'code'
                            ,itemId: 'categoryCodeField'
                            ,emptyText:'资产大类'
                            ,listeners:{
                                change:function( field, newValue, oldValue){
                                    me.down('#typeCodeField').getStore().applyCondition({assetCategoryCode:newValue}).load()
                                    me.down('#typeCodeField').setValue(null);
                                }
                            }
                        }
                        ,{
                            xtype: 'combobox'
                            ,bind:{store: '{assetTypeStore}'}
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'code'
                            ,blankText:'资产分类'
                            ,itemId: 'typeCodeField'
                            ,emptyText: '资产分类'
                        }
                        ,{
                            xtype:'button',
                            text:'查询',
                            iconCls: 'fas fa-search',
                            handler: function(button, event){
                                var categoryCodeField = me.down('#categoryCodeField')
                                var typeCodeField = me.down('#typeCodeField')
                                me.getViewModel().getStore('assetCmdbStore')
                                    .applyCondition({
                                        categoryCode:categoryCodeField.getValue()
                                        ,typeCode:typeCodeField.getValue()
                                    }).load();
                            }
                        }
                    ]
                    ,columns: [
                        {
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'fas fa-angle-double-right green'
                                ,tooltip: '加入'
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {
                                    var record = grid.getStore().getAt(rowIndex);
                                    var newRecord = record.clone();
                                    var relationAssetStore = me.getViewModel().getStore('relationAssetStore');
                                    relationAssetStore.add(newRecord);
                                }
                            }]
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '资产名称'
                            // ,locked: true

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '资产代码'
                            // ,locked: true

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'category'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("categoryVO")?record.get("categoryVO").name:'';
                            }
                            ,text: '资产大类'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("typeVO")?record.get("typeVO").name:'';
                            }
                            ,text: '资产分类'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
                            ,text: '状态'

                        }
                        ,{
                            xtype: 'datecolumn'
                            ,format: 'Y-m-d'
                            ,dataIndex: 'expireDate'
                            ,text: '到期时间'
                        }
                    ]
                }
                ,{
                    xtype: 'grid'
                    ,width: '50%'
                    ,region:'center'
                    ,reference: 'relationGridPanel'
                    ,displayField: 'name'
                    ,rootVisible: false
                    ,bind:{store:'{relationAssetStore}'}
                    ,tbar:[
                        {
                            xtype:'button',
                            text:'保存',
                            iconCls: 'fas fa-plus-circle',
                            handler: function(button, event){me.onSaveButtonClick()}
                        }
                    ]
                    ,columns: [
                        {
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                iconCls: 'fas fa-minus-circle red'
                                ,tooltip: '删除'
                                ,handler: function(grid, rowIndex, colIndex, item, event, record) {
                                    var relationAssetStore = me.getViewModel().getStore('relationAssetStore');
                                    relationAssetStore.remove(record);
                                }
                            }]
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'name'
                            ,text: '资产名称'
                            // ,locked: true

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'code'
                            ,text: '资产代码'
                            // ,locked: true

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'alias'
                            ,text: '资产别名'
                            ,locked: true
                            ,hidden: true
                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'category'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("categoryVO")?record.get("categoryVO").name:'';
                            }
                            ,text: '资产大类'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'type'
                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                return record.get("typeVO")?record.get("typeVO").name:'';
                            }
                            ,text: '资产分类'

                        }
                        ,{
                            xtype: 'gridcolumn'
                            ,dataIndex: 'status'
                            ,text: '状态'

                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
    ,onSaveButtonClick: function (button, e, options) {

        var me = this;

        // var mainGridPanel = me.down('grid');
        //
        // var selections = mainGridPanel.getSelectionModel( ).getSelection( );
        //
        //
        // if (selections == null || selections.length == 0) {
        //     Ext.MessageBox.alert('提交失败', '请选择IT资产');
        //     return;
        // }
        var relationAssetStore = this.getViewModel().getStore('relationAssetStore');

        var selections = relationAssetStore.getData().items;
        console.log(selections);
        var resource = me.getViewModel().get('resource');

        var relationList = []
        for(var i = 0; i < selections.length; i++){
            var relation = {
                resource:resource.getId()
                ,asset:selections[i].getId()
                ,scheme:resource.get('scheme')
                ,assetCategoryCode:selections[i].get('categoryCode')
                ,assetTypeCode:selections[i].get('typeCode')
            }
            relationList.push(relation);
        }

        Ext.Ajax.request({
            url: 'speedcloud/deployscheme/resourceinstancerelation/updateall/'+resource.getId()
            ,method: 'PUT'
            ,jsonData:relationList
            ,scope:me
            ,success:function(response){
                console.log('success')
            }
        });
    }
    ,setResource: function (resource) {
        if(!resource){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }
        this.getViewModel().set('resource', resource);

        var relationAssetStore = this.getViewModel().getStore('relationAssetStore');

        Ext.Ajax.request({
            url: 'speedcloud/deployscheme/resourceinstancerelation/asset/list'
            ,method: 'POST'
            ,jsonData:{searchCondition:{resource:resource.getId()}, page:0, limit:1000}
            ,scope: this
            ,success:function(response){

                console.log(response)

                var resultSet = Ext.data.schema.Schema.lookupEntity('AM.model.asset.asset.info.AssetCmdb').getProxy().getReader().read(response);
                var nodeList = resultSet.getRecords()
                relationAssetStore.add(nodeList)
            }
        });
    }

});