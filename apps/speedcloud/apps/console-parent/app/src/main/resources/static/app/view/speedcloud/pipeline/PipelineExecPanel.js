Ext.define('AM.view.speedcloud.pipeline.PipelineExecPanel', {
    extend: 'Ext.panel.Panel'
    , xtype: 'speedcloud.pipeline.PipelineExecPanel'
    , title: '流水线'
    , layout: 'border'
    , requires: [
        'AM.view.speedcloud.pipeline.PipelineExecController'
    ]
    ,bodyPadding:10
    ,bodyCls: 'app-dashboard'
    ,controller: 'speedcloud.pipeline.PipelineExecController'
    ,viewModel:true
    ,initComponent: function() {
        var me = this;

        Ext.apply(me, {
            // controller: 'speedcloud.pipeline.PipelineExecController'
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
                            xtype: 'displayfield'
                            ,name: 'name'
                            ,fieldLabel: '流水线名称'
                            ,bind:'{pipeline.name}'
                        }
                        ,{
                            xtype: 'displayfield'
                            ,fieldLabel: '所属产品'
                            ,bind:'{pipeline.projectVO.name}'
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
                                    ,text: '全新执行'
                                    ,scale: 'large'
                                    ,reference: 'startNewExecButton'
                                    ,listeners: {
                                        click: 'onPipelineExecButtonClick'
                                    }
                                }
                                ,{
                                    xtype:'button',text:'继续执行',scale: 'large'
                                    ,listeners: {
                                        click: 'onPipelineContinueExecButtonClick'
                                    }
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
                            ,title:'1:开始'
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
                                            ,text: '参数名'
                                            ,flex:1
                                        }
                                        ,{
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'value'
                                            ,text: '参数值'
                                            ,flex:1
                                        }

                                    ]

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
                            xtype:'panel',frame:true,width:300
                            , margin:'0 0 0 20'
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
                    ,tbar: {
                        xtype: 'statusbar'
                        ,reference: 'basic-statusbar'
                        ,autoClear:1500
                        // defaults to use when the status is cleared:
                        ,defaultText: '&nbsp;'
                    }
                }
            ]

            ,listeners:{
                added:function(panel, container){
                    me.getController().initPipelinePanel()
                }
            }

        });
        me.callParent(arguments);
    }

});