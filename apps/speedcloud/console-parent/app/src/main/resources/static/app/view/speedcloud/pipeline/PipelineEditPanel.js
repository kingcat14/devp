Ext.define('AM.view.speedcloud.pipeline.PipelineEditPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.pipeline.PipelineEditPanel'
    , title: '流水线'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.pipeline.PipelineEditController'
        ,'AM.view.speedcloud.pipeline.PipelineEditStageWindow'
        ,'AM.view.speedcloud.pipeline.PipelineEditStageNodeWindow'
    ]
    ,bodyPadding:10
    ,bodyCls: 'app-dashboard'
    ,controller: 'speedcloud.pipeline.PipelineEditController'
    ,viewModel:true
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            // controller: 'speedcloud.pipeline.PipelineEditController'
            items: [
                {
                    xtype:'form'
                    ,region:'north'
                    // ,frame:true
                    // ,title:'基本信息'
                    ,split: false
                    ,bodyPadding:10
                    ,fieldDefaults: {
                        labelStyle: 'font-weight: bold;'
                        ,fieldStyle: 'font-weight: bold;'
                    }
                    ,items:[
                        {
                            xtype:'container'
                            ,style:{
                                'height': 'auto'
                                ,'line-height': '32px'
                                ,'font-size': '18px'
                                ,'color': '#3a3e55'
                                ,'font-weight': 'bold'
                                ,'max-width': '90%'
                            }

                            //,tpl:'<div><tpl if="project">编辑<tpl else>新建</tpl>流水线:{name}</div>'
                            // ,bind:'{record}'
                            ,bind:{html:'<div>{!phantom?"编辑":"新建"}流水线</div>'}
                        }
                        ,{
                            xtype: 'textfield'
                            ,hidden: false
                            ,readOnly:false
                            ,allowBlank:false
                            ,forceSelection:true
                            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
                            ,itemId: 'nameField'
                            ,reference:'nameField'
                            ,name: 'name'
                            ,emptyText:'输入流水线名称'
                            ,fieldLabel: '流水线名称'
                            ,bind:'{record.name}'
                        }
                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.project.ProjectStore").load()
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,hidden: false
                            // ,readOnly:true
                            ,allowBlank:false
                            ,afterLabelTextTpl: []
                            ,itemId: 'projectField'
                            ,name: 'project'
                            // ,bind:'{record.project}'
                            ,fieldLabel: '所属产品'
                            ,bind:{readOnly:'{!phantom}',value:'{record.project}'}
                        }
                        ,{
                            xtype:'container'
                            ,style:{
                                'height': 'auto'
                            }
                            ,width:'100%'
                            ,html:'<hr style=" height:1px;border:none;border-top:1px solid #185598;" /> '
                        }
                    ]
                    ,dockedItems: [
                        {
                            xtype:'toolbar'
                            // ,layout:'center'
                            ,dock: 'bottom'

                            //ui: 'footer',
                            // layout:'center',

                            ,items: [
                                {
                                    xtype: 'button'
                                    // ,iconCls: 'accept'
                                    //,iconCls: 'x-fa fa-plus-circle'
                                    ,text: '确定'
                                    ,scale: 'large'
                                    ,listeners: {
                                        click: 'onPipelineSaveButtonClick'
                                    }
                                }
                                ,{
                                    xtype:'button',text:'取消',scale: 'large'
                                }
                            ]
                        }

                    ]
                }
                ,{xtype:'container',html:'　'}
                ,{
                    xtype:'panel'
                    ,region:'center'
                    ,layout:'hbox'
                    ,bodyPadding:10
                    ,reference:'pipelineStageListPanel'
                    ,scrollable:true
                    ,items:[
                        {
                            xtype:'panel',frame:true,width:300
                            ,title:'开始'
                            ,layout:'vbox'
                            // ,bodyPadding:10
                            ,items:[
                                {
                                    xtype:'container'
                                    ,padding:'0 0 0 10'
                                    ,style:{
                                        'height': 'auto'
                                        ,'line-height': '32px'
                                        ,'font-size': '18px'
                                        ,'color': '#3a3e55'
                                        ,'font-weight': 'bold'
                                        ,'max-width': '90%'
                                    }
                                    ,html:'<div>参数列表</div>'
                                }
                                ,{
                                    xtype:'grid',collapsed: false
                                    ,width:'100%'
                                    ,frame:false
                                    ,bind:{store:"{paramStore}"}
                                    ,reference:'pipelineParamGrid'
                                    ,columns:[
                                        {
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'name'
                                            // ,text: '参数列表'
                                            ,flex:1
                                            ,editor: {
                                                xtype: 'textfield'
                                            }
                                        }
                                        , {
                                            xtype: 'actioncolumn'
                                            ,menuDisabled: true
                                            ,width:60
                                            , items: [
                                                {
                                                    iconCls: 'x-fa fa-pencil'
                                                    ,margin:'0 10 0 0'
                                                    , tooltip: '修改'
                                                    , handler: 'onEditPipelineParamClick'

                                                }
                                                ,'-'
                                                ,{
                                                    iconCls: 'x-fa fa-trash'
                                                    , tooltip: '删除'
                                                    , handler: 'onDeletePipelineParamClick'
                                                }
                                            ]
                                        }
                                    ]
                                    ,dockedItems: [
                                        {
                                            xtype: 'toolbar'
                                            ,dock: 'bottom'
                                            , layout:'fit'
                                            ,items:{
                                                xtype: 'button'
                                                ,iconCls: 'x-fa fa-plus darkgreen'
                                                ,text: '添加参数'
                                                ,listeners: {
                                                    click: 'onAddPipelineParamClick'
                                                }
                                            }
                                        }]
                                    ,plugins: [
                                        {
                                            ptype: 'cellediting'
                                            ,clicksToEdit: 1
                                        }
                                    ]

                                }
                            ]
                        }
                        ,{
                            xtype:'panel'
                            ,width:50
                            ,dockedItems: [
                                {
                                    xtype: 'toolbar'
                                    ,dock: 'bottom'
                                    , layout:'fit'

                                    ,items:{
                                        xtype: 'button'
                                        // ,scale:'large'
                                        // ,iconCls: 'x-fa fa-plus-circle orange'
                                        ,iconCls: 'fas fa-plus-circle orange'
                                        ,handler:'onAddStageButtonClick'
                                    }
                                }]

                        }
                        ,{
                            xtype:'panel',frame:true,width:300
                            ,title:'结束'
                            ,items:[
                                {
                                    xtype:'container'
                                    ,padding:'0 0 0 10'
                                    ,viewModel:{data:{stageCount:0}}
                                    ,reference:'endStageContainer'
                                    ,style:{
                                        'height': 'auto'
                                        ,'line-height': '32px'
                                        ,'font-size': '18px'
                                        ,'color': '#3a3e55'
                                        ,'font-weight': 'bold'
                                        ,'max-width': '90%'
                                    }
                                    ,bind:{html:'流水线现有{stageCount}个阶段'}
                                    // ,bind:{html:'流水线现有{stageCount}个阶段', store:'stageStore'}
                                    // ,listeners:{
                                    //     datachanged:function(){alert(1)}
                                    // }

                                }
                            ]
                        }
                    ]
                }
            ]

            ,listeners:{
                added:function(panel, container){
                    console.log("panel.id:"+panel.getId())
                    console.log(panel)
                    console.log("container.id:"+container.getId())
                    console.log(container)
                    me.getController().initPipelinePanel()
                }
            }

        });
        me.add({xtype:'speedcloud.pipeline.PipelineEditStageWindow',reference:'stageWindow'})
        me.add({xtype:'speedcloud.pipeline.PipelineEditStageNodeWindow',reference:'stageNodeWindow'})

        me.callParent(arguments);
    }

});