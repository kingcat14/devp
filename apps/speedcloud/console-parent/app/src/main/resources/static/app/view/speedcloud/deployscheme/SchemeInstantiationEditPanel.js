Ext.define('AM.view.speedcloud.deployscheme.SchemeInstantiationEditPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.deployscheme.SchemeInstantiationEditPanel'
    , title: '部署方案'
    , layout: 'border'
    , requires: [
         'AM.model.speedcloud.deployscheme.ResourceTreeNode'
        ,'AM.view.speedcloud.deployscheme.SchemeInstantiationEditController'
        ,'AM.view.speedcloud.deployscheme.ResourcePanel'
        ,'AM.view.speedcloud.deployscheme.ResourceInstanceWindow'
        ,'AM.view.speedcloud.deployscheme.SchemeEditResourcePanel'
        ,'AM.view.speedcloud.deployscheme.SchemeEditResourceRelationPanel'
        ,'AM.store.speedcloud.deployscheme.ResourceStore'
        ,'AM.store.speedcloud.deployscheme.ResourceRelationStore'
    ]
    ,controller: 'speedcloud.deployscheme.SchemeInstantiationEditController'
    ,viewModel:{
        stores:{

            schemeResourceTreeStore : {type:'tree', autoLoad:false, model:'AM.model.speedcloud.deployscheme.ResourceTreeNode', nodeParam:'id'}
            ,schemeResourceStore : {type:'speedcloud.deployscheme.ResourceStore', autoLoad:false}
            ,schemeRelationStore : {type:'speedcloud.deployscheme.ResourceRelationStore', autoLoad:false}

        }
    }

    ,initComponent: function() {
        var me = this;

        me.enableBubble('createMainTabPanel');

        Ext.apply(me, {
            items: [
                {
                    xtype: 'treepanel'
                    ,title: '资源'
                    ,collapsible:true
                    ,region: 'west'
                    ,width: '20%'
                    ,frame: true
                    ,split: true
                    ,reference: 'schemeResourceTree'
                    ,displayField: 'name'
                    ,rootVisible: false
                    ,bind:{store: '{schemeResourceTreeStore}'}
                    ,tbar:[
                        {
                            xtype:'button',
                            text:'刷新',
                            iconCls: 'fas fa-sync-alt',
                            handler: 'loadResourcesTree'
                        }
                    ]
                    ,columns: [
                        {
                            xtype: 'treecolumn',
                            text: 'Name',
                            dataIndex: 'name',
                            flex: 1,
                            sortable: true,
                            renderer: function(v, metaData, record) {
                                metaData.glyph = record.glyph;
                                return v;
                            }
                        }

                        ,{
                            xtype: 'actioncolumn'
                            , menuDisabled: true
                            , width:30
                            , items: [{
                                // iconCls: 'x-fa fa-minus-circle'
                                tooltip: '实例化'
                                ,handler: 'showInstancePanel'
                                ,getClass:function(value, metadata, record){
                                    if(!record.get('relationId')) {
                                        return 'fas fa-link';
                                    }
                                }
                                ,isDisabled:function (value, rowIndex, colIndex, item, record) {
                                    if(!record.get('relationId')) {
                                        return false;
                                    }
                                    return true;
                                }
                            }]
                        }
                    ]
                    ,listeners: {
                        cellclick: 'onResourceNameClick'
                        ,afterrender: 'loadResourcesTree'
                    }
                }
                ,{
                    xtype: 'panel'
                    ,region:'center'
                    ,title:'aaa'
                    ,bind:{title:'方案【{record.name}】'}
                    ,scrollable:true
                    ,html:'<div id="paper"></div>'
                    ,tbar:[
                        {
                            xtype:'button',
                            text:'刷新',
                            iconCls: 'fas fa-sync-alt',
                            handler: 'loadResourceGraph'
                        }
                    ]
                    ,reference:'graphPaper'
                    ,listeners:{
                        afterrender:{fn:me.initGraph, scope:me}
                    }
                }
                ,{
                    xtype: 'panel'
                    ,region:'east'
                    // ,title:'属性'
                    ,reference:'detailEditPanel'
                    ,scrollable:true
                    ,split: true
                    ,width: '20%'
                    ,layout:'card'
                    ,items:[
                        {
                            xtype:'tabpanel'
                            ,reference:'resourceEditPanel'
                            ,viewModel:true
                            ,title:'资源信息'
                            ,items: [
                                {
                                    xtype: 'form'
                                    ,autoScroll: true
                                    ,bodyPadding: 10
                                    ,title: '基础信息'
                                    ,width:'100%'
                                    ,fieldDefaults: {
                                        labelAlign: 'left'
                                        ,msgTarget: 'side'
                                        ,padding: '5 0 0 5'
                                        ,blankText:'该字段为必填项'
                                        ,anchor: '96%'
                                    }
                                    ,items: [
                                        {
                                            xtype:'container'
                                            ,anchor: '96% 70%'
                                            ,layout: {
                                                type: 'table',
                                                columns: 1,
                                                tableAttrs: {
                                                    style: {
                                                        width: '100%'
                                                    }
                                                }
                                            }
                                            ,defaults:{width:'100%'}
                                            ,items:[


                                                ,{
                                                    xtype: 'textfield'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'nameField'
                                                    ,name: 'name'
                                                    ,fieldLabel: '资源名称'
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'codeField'
                                                    ,name: 'code'
                                                    ,fieldLabel: '资源代码'
                                                }


                                                ,{
                                                    xtype: 'combobox'
                                                    ,store: Ext.create("AM.store.speedcloud.deployscheme.ResourceCategoryStore")
                                                    ,typeAhead:false
                                                    ,editable:false
                                                    ,displayField:'name'
                                                    ,valueField:'id'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'categoryField'
                                                    ,name: 'category'
                                                    ,fieldLabel: '资源类别'
                                                }
                                                ,{
                                                    xtype: 'combobox'
                                                    ,store: Ext.create("AM.store.speedcloud.deployscheme.ResourceTypeStore")
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
                                                    ,fieldLabel: '资源类型'
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'notesField'
                                                    ,name: 'notes'
                                                    ,fieldLabel: '备注'
                                                }
                                                , {
                                                    xtype: 'fieldset'
                                                    ,title: '其他信息'
                                                    ,collapsible: true
                                                    ,collapsed:true
                                                    ,items:[
                                                        ,{
                                                            xtype: 'textfield'
                                                            ,hidden: false
                                                            ,readOnly:false
                                                            ,allowBlank:true
                                                            ,afterLabelTextTpl: []
                                                            ,itemId: 'aliasField'
                                                            ,name: 'alias'
                                                            ,fieldLabel: '资源别名'
                                                        }
                                                        ,{
                                                            xtype: 'combobox'
                                                            ,store: [
                                                                [true,'是']
                                                                ,[false,'否']
                                                            ]
                                                            ,value:true
                                                            ,typeAhead:false
                                                            ,editable:false
                                                            ,hidden: false
                                                            ,readOnly:false
                                                            ,allowBlank:true
                                                            ,afterLabelTextTpl: []
                                                            ,itemId: 'outerResourceField'
                                                            ,name: 'outerResource'
                                                            ,fieldLabel: '外部资源'
                                                        }
                                                        ,{
                                                            xtype: 'numberfield'
                                                            ,allowDecimals:false
                                                            ,hidden: false
                                                            ,readOnly:false
                                                            ,allowBlank:true
                                                            ,afterLabelTextTpl: []
                                                            ,itemId: 'seqField'
                                                            ,name: 'seq'
                                                            ,fieldLabel: '顺序号'
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
                                                            ,itemId: 'versionField'
                                                            ,name: 'version'
                                                            ,fieldLabel: '版本'
                                                        }
                                                        ,{

                                                            xtype: 'textarea',
                                                            anchor: '96% 70%',
                                                            itemId: 'descriptionField',
                                                            padding: '5 0 0 5',
                                                            name: 'description',
                                                            fieldLabel: '资源描述',
                                                            labelAlign: 'top'
                                                        }
                                                    ]
                                                }



                                            ]

                                        }

                                    ]
                                }
                                ,{
                                    xtype:'grid'
                                    ,title:'属性'
                                    ,reference:'resourcePropertyPanel'
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
                                    ,viewModel:{
                                        data:{}
                                        ,stores:{
                                            propertyStore: Ext.create('AM.store.speedcloud.deployscheme.ResourcePropertyStore').applyCondition({resource:-999}).load()
                                        }
                                    }
                                }
                            ]
                            ,setModel: function (model) {

                                if(!model){
                                    Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
                                    return;
                                }
                                this.getViewModel().set('phantom', model.phantom);

                                this.getViewModel().set('record', model);

                                this.down('form').getForm().loadRecord(model);

                                me.lookup('resourcePropertyPanel').getViewModel().set('resource', model);
                                me.lookup('resourcePropertyPanel').getViewModel().set('phantom', model.phantom);
                                me.lookup('resourcePropertyPanel').setDisabled(model.phantom)
                                me.lookup('resourcePropertyPanel').getViewModel().getStore('propertyStore').applyCondition({resource:model.phantom?-999:model.get('id')}).reload();

                            }
                        }
                        ,{
                            xtype:'form'
                            ,reference:'relationEditPanel'
                            ,viewModel:true
                            ,title:'关系信息'
                            ,autoScroll: true
                            ,bodyPadding: 10
                            ,width:'100%'
                            ,fieldDefaults: {
                                labelAlign: 'right'
                                ,msgTarget: 'side'
                                ,padding: '5 0 0 5'
                                ,blankText:'该字段为必填项'
                                ,anchor: '96%'
                            }
                            ,items: [
                                {
                                    xtype:'container'
                                    ,anchor: '96% 70%'
                                    ,layout: {
                                        type: 'table',
                                        columns: 1,
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
                                            ,store: Ext.create("AM.store.speedcloud.deployscheme.ResourceStore").load()
                                            ,typeAhead:false
                                            ,editable:false
                                            ,displayField:'name'
                                            ,valueField:'id'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:true
                                            ,afterLabelTextTpl: []
                                            ,itemId: 'resourceField'
                                            ,name: 'resource'
                                            ,fieldLabel: '主资源'
                                            ,bind:{readOnly:'{record.resource}'}
                                        }
                                        ,{
                                            xtype: 'combobox'
                                            ,store: Ext.create("AM.store.speedcloud.deployscheme.ResourceRelationTypeStore").load()
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
                                            ,fieldLabel: '关系'
                                        }
                                        ,{
                                            xtype: 'combobox'
                                            ,store: Ext.create("AM.store.speedcloud.deployscheme.ResourceStore").load()
                                            ,typeAhead:false
                                            ,editable:false
                                            ,displayField:'name'
                                            ,valueField:'id'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:true
                                            ,afterLabelTextTpl: []
                                            ,itemId: 'refResourceField'
                                            ,name: 'refResource'
                                            ,fieldLabel: '关联资源'
                                            ,bind:{readOnly:'{record.refResource}'}
                                        }
                                        , {
                                            xtype: 'fieldset'
                                            , title: '其他信息'
                                            , collapsible: true
                                            , collapsed: true
                                            ,items:[
                                                ,{
                                                    xtype: 'combobox'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,editable:false
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'directionField'
                                                    ,name: 'direction'
                                                    ,fieldLabel: '对应关系方向'
                                                    ,value:'EMPTY'
                                                    ,store: [
                                                        ['EMPTY','无']
                                                        ,['FORWARD','正向']
                                                        ,['BACKWARD','反向']
                                                        ,['BOTH','双向']
                                                    ]
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'nameField'
                                                    ,name: 'name'
                                                    ,fieldLabel: '对应关系名称'
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'codeField'
                                                    ,name: 'code'
                                                    ,fieldLabel: '对应关系代码'
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'aliasField'
                                                    ,name: 'alias'
                                                    ,fieldLabel: '对应关系别名'
                                                }
                                                ,{
                                                    xtype: 'textfield'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'descriptionField'
                                                    ,name: 'description'
                                                    ,fieldLabel: '对应关系描述'
                                                }
                                                ,{
                                                    xtype: 'numberfield'
                                                    ,allowDecimals:false
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'seqField'
                                                    ,name: 'seq'
                                                    ,fieldLabel: '顺序号'
                                                }


                                            ]
                                        }



                                    ]
                                }
                            ]

                        }
                    ]
                }
            ]
        });

        me.add({xtype:'speedcloud.deployscheme.ResourceInstanceWindow',reference:'resourceInstanceWindow',listeners:{saved:'reloadStore'}})

        me.callParent();
    }

    ,onBeforeShow:function(abstractcomponent, options) {
	    // this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }

    ,initGraph:function () {

        var me = this;

        console.log(11111111)

        var record = me.getViewModel().get('record');


        var paperId = 'paper_'+record.getId();

        me.lookup('graphPaper').setHtml('<div id="'+paperId+'"></div>')

        var graph = new joint.dia.Graph;
        graph.on('remove', function(cell, P2, options) {

            if(options.ignoreRemove){
                return;
            }
            console.log('cell remove:'+cell.id)
            console.log(cell)
            var relation = me.getViewModel().getStore('schemeRelationStore').getById(cell.id);
            if(relation){
                me.getController().deleteRelation(cell.id)
            }
        })


        me.getViewModel().set('graph', graph);

        var paper = new joint.dia.Paper({
            el: $('#'+paperId),
            width: 1800,
            height: 1600,
            gridSize: 1,
            model: graph
        });


        var start = new joint.shapes.fsa.StartState({ position: { x: 50, y: 530 } });
        graph.addCell(start);

        // var code  = me.graphState(180, 390, 'code');
        // var slash = me.graphState(340, 220, 'slash');
        // var star  = me.graphState(600, 400, 'star');
        // var line  = me.graphState(190, 100, 'line');
        // var block = me.graphState(560, 140, 'block');

        // me.graphLink(start.id, code.id,  'start');
        // me.graphLink(code.id,  slash.id, '/');
        // me.graphLink(slash.id, code.id,  'other', [{x: 270, y: 300}]);
        // me.graphLink(slash.id, line.id,  '/');
        // me.graphLink(line.id,  code.id,  'new\n line');
        // me.graphLink(slash.id, block.id, '*');
        // me.graphLink(block.id, star.id,  '*');
        // me.graphLink(star.id,  block.id, 'other', [{x: 650, y: 290}]);
        // me.graphLink(star.id,  code.id,  '/',     [{x: 490, y: 310}]);
        // me.graphLink(line.id,  line.id,  'other', [{x: 115,y: 100}, {x: 250, y: 50}]);
        // me.graphLink(block.id, block.id, 'other', [{x: 485,y: 140}, {x: 620, y: 90}]);
        // me.graphLink(code.id,  code.id,  'other', [{x: 180,y: 500}, {x: 305, y: 450}]);

        console.log(22222222)

        me.getController().loadResourceGraph();

    }
    ,graphState:function(x, y, label, id){

        var me = this;
        var graph = me.getViewModel().get('graph');
        var cell = new joint.shapes.fsa.State({
            id:id,
            position: { x: x, y: y },
            size: { width: 60, height: 60 },
            attrs: { text : { text: label }}
        });
        if(id){
            // cell.id = id;
        }
        graph.addCell(cell);
        return cell;
    }
    ,graphLink:function(sourceId, targetId, label, vertices, id){
        var me = this;
        var graph = me.getViewModel().get('graph');
        var cell = new joint.shapes.fsa.Arrow({
            id:id,
            source: { id: sourceId },
            target: { id: targetId },
            labels: [{ position: .5, attrs: { text: { text: label || '', 'font-weight': 'bold' } } }],
            vertices: vertices || []
        });
        graph.addCell(cell);
        return cell;
    }
    ,graphDelLink:function(id){
        var me = this;
        var graph = me.getViewModel().get('graph');

        var cell = graph.getCell(id);
        graph.removeCells(cell)
        // graph.removeLinks(state)
    }
    ,graphDelState:function(id){
        var me = this;
        var graph = me.getViewModel().get('graph');

        var cell = graph.getCell(id);
        graph.removeCells(cell)

    }
    ,graphUpdateCell:function(id, text){
        console.log('graphUpdateCell')
        var me = this;
        var graph = me.getViewModel().get('graph');

        var cell = graph.getCell(id);
        if(cell){
            console.log('find cell, try update')
            cell.attr({
                text: { 'font-size': 15, text:text }
            });
        }

    }
});