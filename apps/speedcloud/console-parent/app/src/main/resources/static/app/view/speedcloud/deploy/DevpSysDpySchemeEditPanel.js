Ext.define('AM.view.speedcloud.deploy.DevpSysDpySchemeEditPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.deploy.DevpSysDpySchemeEditPanel'
    , title: '部署方案'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.deploy.DevpSysDpySchemeEditController'
        ,'AM.view.speedcloud.deploy.DevpSysDpyResourcesPanel'
        ,'AM.model.speedcloud.deploy.DevpSysDpyResourcesTreeNode'
        ,'AM.view.speedcloud.deploy.DevpSysDpySchemeEditResourcePanel'
        ,'AM.view.speedcloud.deploy.DevpSysDpySchemeEditResourceRefPanel'
        ,'AM.store.speedcloud.deploy.DevpSysDpyResourcesStore'
        ,'AM.store.speedcloud.deploy.DevpSysDpyResourceRefStore'

    ]
    ,controller: 'speedcloud.deploy.DevpSysDpySchemeEditController'
    ,viewModel:{
        stores:{
            // schemeResourceTreeStore : Ext.create('Ext.data.TreeStore', {autoLoad:false, model:'AM.model.speedcloud.deploy.DevpSysDpyResourcesTreeNode', nodeParam:'id'})
            schemeResourceTreeStore : {type:'tree', autoLoad:false, model:'AM.model.speedcloud.deploy.DevpSysDpyResourcesTreeNode', nodeParam:'id'}
            ,schemeResourceStore : {type:'devpSysDpyResourcesStore', autoLoad:false}
            ,schemeRelationStore : {type:'devpSysDpyResourceRefStore', autoLoad:false}
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
                        ,{
                            xtype:'button',
                            text:'资源',
                            iconCls: 'fas fa-plus-circle',
                            handler: 'createResource'
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
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                // iconCls: 'x-fa fa-minus-circle'
                                tooltip: '连接'
                                ,handler: 'createRelation'
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
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                // iconCls: 'x-fa fa-minus-circle'
                                tooltip: '删除'
                                ,handler: 'deleteResource'
                                ,getClass:function(value, metadata, record){
                                    // metadata.attr = 'style="color:red;"'
                                    if(!record.get('relationId'))
                                        return 'fas fa-trash-alt';
                                }
                                ,isDisabled:function (value, rowIndex, colIndex, item, record) {
                                    if(!record.get('relationId')){
                                        return false;
                                    }
                                    return true;
                                }
                            }]
                        }
                        ,{
                            xtype: 'actioncolumn'
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                // iconCls: 'x-fa fa-minus-circle'
                                tooltip: '断连'
                                ,handler: 'deleteRelationBtnClick'
                                ,getClass:function(value, metadata, record){
                                    // metadata.attr = 'style="color:red;"'
                                    if(record.get('relationId'))
                                        return 'fas fa-cut';
                                }
                                ,isDisabled:function (value, rowIndex, colIndex, item, record) {
                                    if(record.get('relationId')) {
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
                        ,{
                            xtype:'button',
                            text:'保存',
                            iconCls: 'fas fa-save',
                            handler: 'saveResourceGraph'
                        }
                        ,'->'
                        ,{
                            xtype:'button',
                            text:'重绘',
                            iconCls: 'fas fa-sync-alt',
                            handler: 'redrawResourceGraph'
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
                            xtype:'speedcloud.deploy.DevpSysDpySchemeEditResourcePanel'
                            ,reference:'resourceEditPanel'
                            ,viewModel:true
                            ,title:'资源设置'
                            ,listeners:{
                                saved:'handleResourceSaved'
                            }
                        }
                        ,{
                            xtype:'speedcloud.deploy.DevpSysDpySchemeEditResourceRefPanel'
                            ,reference:'relationEditPanel'
                            ,viewModel:true
                            ,title:'关系设置'

                            ,listeners:{
                                saved:'handleRelationSaved'
                            }
                        }
                    ]
                }
            ]
        });


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