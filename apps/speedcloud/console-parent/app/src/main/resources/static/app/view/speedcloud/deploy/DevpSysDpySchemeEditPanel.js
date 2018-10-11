Ext.define('AM.view.speedcloud.deploy.DevpSysDpySchemeEditPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.deploy.DevpSysDpySchemeEditPanel'
    , title: '部署方案'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.deploy.DevpSysDpySchemeEditController'
        ,'AM.view.speedcloud.deploy.DevpSysDpyResourcesPanel'
        ,'AM.model.speedcloud.deploy.DevpSysDpyResourcesTreeNode'

    ]
    ,controller: 'speedcloud.deploy.DevpSysDpySchemeEditController'
    ,viewModel:{
        stores:{
            // schemeResourceTreeStore : Ext.create('Ext.data.TreeStore', {autoLoad:false, model:'AM.model.speedcloud.deploy.DevpSysDpyResourcesTreeNode', nodeParam:'id'})
            schemeResourceTreeStore : {type:'tree', autoLoad:false, model:'AM.model.speedcloud.deploy.DevpSysDpyResourcesTreeNode', nodeParam:'id'}
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
                    ,width: '30%'
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
                            ,menuDisabled: true
                            ,width:30
                            ,items: [{
                                // iconCls: 'x-fa fa-minus-circle'
                                tooltip: '删除'
                                ,handler: 'deleteRelation'
                                ,getClass:function(value, metadata, record){
                                    // metadata.attr = 'style="color:red;"'
                                    if(record.get('relationId'))
                                        return 'fas fa-cut';
                                }
                            }]

                        }
                    ]
                    ,listeners: {
                        itemclick: 'onTreepanelItemClick'
                        ,afterrender: 'loadTypeTree'
                    }
                }
                ,{
                    xtype: 'panel'
                    ,region:'center'
                    ,title:'aaa'
                    ,scrollable:true
                    ,html:'<div id="paper"></div>'
                    ,listeners:{
                        afterrender:me.aaa
                    }
                }
            ]
        });


        me.callParent();
    }

    ,onBeforeShow:function(abstractcomponent, options) {
	    this.lookupReference('mainGridPanel').getStore().reload({scope: this,callback: function(){}});
    }
    ,aaa:function () {
        var me = this;
        console.log(11111111)



        var graph = new joint.dia.Graph;

        var paper = new joint.dia.Paper({

            el: document.getElementById('paper'),
            width: 800,
            height: 400,
            gridSize: 1,
            model: graph,
            snapLinks: true,
            linkPinning: false,
            embeddingMode: true,
            highlighting: {
                'default': {
                    name: 'stroke',
                    options: {
                        padding: 6
                    }
                },
                'embedding': {
                    name: 'addClass',
                    options: {
                        className: 'highlighted-parent'
                    }
                }
            },

            validateEmbedding: function(childView, parentView) {

                return parentView.model instanceof joint.shapes.devs.Coupled;
            },

            validateConnection: function(sourceView, sourceMagnet, targetView, targetMagnet) {

                return sourceMagnet != targetMagnet;
            }
        });

        var connect = function(source, sourcePort, target, targetPort) {

            var link = new joint.shapes.devs.Link({
                source: {
                    id: source.id,
                    port: sourcePort
                },
                target: {
                    id: target.id,
                    port: targetPort
                }
            });

            link.addTo(graph).reparent();
        };

        var c1 = new joint.shapes.devs.Coupled({

            position: {
                x: 230,
                y: 50
            },
            size: {
                width: 300,
                height: 300
            }
            ,attrs: {
                text: {
                    fill: '#ffffff',
                    text: 'Employee',
                    'letter-spacing': 0,
                    style: { 'text-shadow': '1px 0 1px #333333' }
                },
                '.outer, .inner': {
                    fill: '#31d0c6',
                    stroke: 'none',
                    filter: { name: 'dropShadow',  args: { dx: 0.5, dy: 2, blur: 2, color: '#333333' }}
                }
            }
        });

        c1.set('inPorts', ['in']);
        c1.set('outPorts', ['out 1', 'out 2']);

        var a1 = new joint.shapes.devs.Atomic({

            position: {
                x: 360,
                y: 260
            },
            inPorts: ['xy'],
            outPorts: ['x', 'y']
        });

        var a2 = new joint.shapes.devs.Atomic({

            position: {
                x: 50,
                y: 160
            },
            outPorts: ['out']
        });

        var a3 = new joint.shapes.devs.Atomic({

            position: {
                x: 650,
                y: 50
            },
            size: {
                width: 100,
                height: 300
            },
            inPorts: ['a', 'b']
        });

        graph.addCells([c1, a1, a2, a3]);

        c1.embed(a1);

        connect(a2, 'out', c1, 'in');
        connect(c1, 'in', a1, 'xy');
        connect(a1, 'x', c1, 'out 1');
        connect(a1, 'y', c1, 'out 2');
        connect(c1, 'out 1', a3, 'a');
        connect(c1, 'out 2', a3, 'b');

        /* rounded corners */

        _.each([c1, a1, a2, a3], function(element) {

            element.attr({
                '.body': {
                    'rx': 6,
                    'ry': 6
                }
            });
        });
        console.log(22222222)

    }
});