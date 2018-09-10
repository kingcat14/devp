Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskDetailPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskDetailPanel'
    ,requires:[
        'AM.store.common.SimpleConfigStore'
        ,'AM.store.speedcloud.project.ProjectStore'
        ,'AM.model.speedcloud.pipeline.task.PipelineTaskAction'
        ,'AM.view.speedcloud.pipeline.task.PipelineTaskActionEditPanel'
        ,'AM.view.speedcloud.pipeline.task.PipelineTaskEditController'
        ,'AM.model.speedcloud.pipeline.task.PipelineTaskParam'
    ]
    ,layout: 'fit'
    // ,title: '修改新任务'
    ,maximizable: true
    ,closeAction: 'hide'
    ,referenceHolder:true
    ,bodyPadding:10
    ,bind:{title:'任务{record.name}'}
    // ,bind:{title:'任务名称:{title}'}
    ,bodyCls: 'app-dashboard'
    ,controller: 'speedcloud.pipeline.task.PipelineTaskEditController'
    // ,viewModel:{data:{}}
    ,initComponent: function () {

        var me = this;

        Ext.apply(me, {
            items:[
                {
                    xtype:'tabpanel'
                     ,plain: true
                    // , frame:true
                    //,tabBarHeaderPosition: 1
                    , border:true
                    ,tabPosition:'left'
                    ,tabRotation:0

                    ,items: [
                        {
                            xtype:'panel', title:'工作空间', html:'工作空间'
                        }
                        ,{
                            xtype:'grid'
                            ,title:'构建历史', border :true, frame:false
                            //, margin:10
                            ,bind:{store:'{execInstanceStore}'}
                            ,columns: [
                                {
                                    xtype: 'gridcolumn'
                                    ,dataIndex: 'code'
                                    ,text: '编号'
                                }
                                ,{
                                    xtype: 'gridcolumn'
                                    ,dataIndex: 'create_uname'
                                    ,text: '执行人'

                                }
                                ,{
                                    xtype: 'gridcolumn'
                                    ,dataIndex: 'status'
                                    ,text: '任务状态'
                                },{
                                    xtype: 'datecolumn'
                                    ,format: 'Y-m-d H:i:s'
                                    ,dataIndex: 'startTime'
                                    ,text: '运行时点'
                                    ,flex:1
                                }
                                ,{
                                    xtype: 'datecolumn'
                                    ,text: '运行时长'
                                    ,flex:1
                                }
                                ,{
                                    xtype: 'gridcolumn'
                                    ,text: '日志'
                                    ,flex:1
                                }
                            ]
                            ,dockedItems: [
                                {
                                    xtype: 'pagingtoolbar'
                                    ,dock: 'bottom'
                                    ,displayInfo: true
                                }
                            ]
                        },{
                            xtype:'panel', title:'通知', html:'通知', bodyBorder:true
                        }

                    ]

                }
            ]

        });

        me.callParent(arguments);
    }



});