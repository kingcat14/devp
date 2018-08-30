Ext.define('AM.view.icode4.systemboard.SystemBoardDomainModelViewPropertyPanel', {
    extend: 'Ext.grid.Panel'
    ,xtype: 'icode4.systemboard.SystemBoardDomainModelViewPropertyPanel'
    ,title: '属性展现配置'
    ,requires: [

    ]
    ,initComponent: function() {

        var me = this;

        Ext.apply(me, {
            columnLines: true
            ,columns: [
                {
                    xtype: 'numbercolumn'
                    ,dataIndex: 'viewIndex'
                    ,format:'0,000'
                    ,width:80
                    ,text: '展现顺序'
                    ,editor: {
                        xtype: 'numberfield'
                    }
                }
                , {
                    xtype: 'gridcolumn'
                    ,dataIndex: 'name'

                    ,text: '属性名'
                    ,editor: {
                        xtype: 'textfield'
                    }
                }
                ,{
                    xtype: 'gridcolumn'
                    ,dataIndex: 'code'
                    ,text: '属性代码'
                    ,editor: {
                        xtype: 'textfield'
                    }
                }
                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'addViewable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,text: '新增页可见'
                    ,editor: new Ext.form.field.ComboBox({
                        editable:false
                        ,store: [
                            [true,'是']
                            ,[false,'否']
                        ]
                        ,value : true
                    })
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'addable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,text: '新增页可填写'
                    ,editor: new Ext.form.field.ComboBox({
                        editable: false
                        ,store: [
                            [true,'是']
                            ,[false,'否']
                        ]
                        ,value : true
                    })
                }
                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'addRequired'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,text: '新增必填'
                    ,editor: new Ext.form.field.ComboBox({
                        editable:false
                        ,store: [
                            [true,'是']
                            ,[false,'否']
                        ]
                        ,value : true
                    })
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'editViewable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,text: '修改页可见'
                    ,editor: new Ext.form.field.ComboBox({
                        editable:false
                        ,store: [
                            [true,'是']
                            ,[false,'否']
                        ]
                        ,value : true
                    })
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'editable'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,text: '修改页可修改'
                    ,editor: new Ext.form.field.ComboBox({
                        editable:false
                        ,store: [
                            [true,'是']
                            ,[false,'否']
                        ]
                        ,value : true
                    })
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'editRequired'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,text: '修改页必填'
                    ,editor: new Ext.form.field.ComboBox({
                        editable:false
                        ,store: [
                            [true,'是']
                            ,[false,'否']
                        ]
                        ,value : true
                    })
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'searchCondition'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,text: '查询可见'
                    ,editor: new Ext.form.field.ComboBox({
                        editable:false
                        ,store: [
                            [true,'是']
                            ,[false,'否']
                        ]
                        ,value : true
                    })
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'girdColumn'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,text: '列表可见'
                    ,editor: new Ext.form.field.ComboBox({
                        editable:false
                        ,store: [
                            [true,'是']
                            ,[false,'否']
                        ]
                        ,value : true
                    })
                }

                ,{
                    xtype: 'booleancolumn'
                    ,dataIndex: 'simpleSearchCondition'
                    ,trueText: '是'
                    ,falseText: '否'
                    ,emptyCellText :'不确定'
                    ,text: '快速查询条件'
                    ,editor: new Ext.form.field.ComboBox({
                        editable:false
                        ,store: [
                            [true,'是']
                            ,[false,'否']
                        ]
                        ,value : true
                    })
                }


            ]
            ,viewConfig: {

            }
            ,dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'top',
                    items: [
                        '->'
                        ,'-'
                        ,{
                            xtype:'button'
                            ,listeners: {
                                click: function () {
                                    me.saveDocumentAs({
                                        type: 'xlsx'
                                        ,title: 'My export'
                                        ,fileName: 'myExport.xlsx'
                                    });
                                 }
                            }
                        }

                    ]
                }
                ,{
                    xtype: 'pagingtoolbar'
                    ,dock: 'bottom'
                    ,displayInfo: true
                }
            ]
            ,selModel: 'checkboxmodel'
            ,plugins: [
                {
                    ptype: 'cellediting'
                    ,clicksToEdit: 1
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
                ,itemdblclick: {
                    fn: me.onItemDblClick
                    ,scope: me
                }
                ,edit:{
                    fn: function(editor, e) {
                        // commit the changes right after editing finished
                        e.record.commit();
                        e.record.save();
                    }
                }
            }
        });

        me.callParent(arguments);
    }

	,onAddButtonClick: function(button, e, options) {

        var modelConfig = {}

        var record = Ext.create('AM.model.icode4.system.DomainModelViewProperty', modelConfig);

        options.src = button;
        var addWindow = this.showAddWindow(record, button);
        addWindow.setTitle('添加新属性展现配置');
    }
    ,onEditButtonClick: function(button, e, options) {

        var selections = this.getSelectionModel( ).getSelection( );

        if(selections.length <= 0){
            Ext.Msg.show({title: '操作失败', msg: '未选择数据', buttons: Ext.Msg.OK, icon: Ext.Msg.WARNING});
            return;
        }

        var record = selections[0];
        var detailWindow = this.showEditWindow(record, button);
        detailWindow.setTitle('修改属性展现配置信息');
    }
    ,showAddWindow: function(model, targetComponent) {
        var me = this;

        var addWindow = me.addWindow;

        if(!addWindow||addWindow.isHidden()){

            addWindow = Ext.create('AM.view.icode4.system.DomainModelViewPropertyAddWindow',{store:me.getStore()});
            me.addWindow = addWindow;
        }

        addWindow.setModel(model);

        addWindow.show(targetComponent);
        addWindow.setStore(this.store);
        return addWindow;
    }
    ,showEditWindow: function(model, targetComponent) {
        var me = this;

        var editWindow = me.editWindow;

        if(!editWindow||editWindow.isHidden()){

            editWindow = Ext.create('AM.view.icode4.system.DomainModelViewPropertyEditWindow',{store:me.getStore()});
            me.editWindow = editWindow;
        }

        editWindow.setModel(model);

        editWindow.show(targetComponent);
        editWindow.setStore(this.store);
        return editWindow;
    }
    ,setStore: function(store) {
        this.reconfigure(store);
        this.down('pagingtoolbar').bindStore(store);

        this.store=store;
    }

    ,setDomainModel: function(domainModel) {
        // var searchCondition = Ext.JSON.encode({
        // 	domainModelId : domainModel.get('id')
        // });
        var me = this;
        this.domainModel = domainModel;

        me.enable()
        if(domainModel.phantom){
            me.disable();
        }

        var searchCondition = {
            domainModelId : domainModel.get('id')
        };

        //如果是新对象,则默认添加一个逐渐属性
        console.log(domainModel.phantom)


        console.log('处理老对象')
        //这里这样处理，是为了避免新增属性的时候把searchConditon带上了
        this.store.on('beforeload', function(){
            me.store.proxy.extraParams = {searchCondition: searchCondition};
        })
        console.log('处理老对象：1')
        this.store.on('load', function(){
            me.store.proxy.extraParams = null;
        })
        console.log('处理老对象：2')
        this.store.proxy.isSynchronous = true;
        // this.store.proxy.extraParams = {searchCondition: searchCondition};
        console.log('处理老对象：3')
        this.store.load({
            params: {
                start: 0
                ,page: 0
            }
        });

    }
    ,onBeforeShow:function(abstractcomponent, options) {

        this.store.reload({
            scope: this, callback: function () {
            }
        });
    }

});