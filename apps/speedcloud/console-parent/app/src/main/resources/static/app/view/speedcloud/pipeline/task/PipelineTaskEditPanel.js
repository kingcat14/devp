Ext.define('AM.view.speedcloud.pipeline.task.PipelineTaskEditPanel', {
    extend: 'Ext.panel.Panel'
    ,xtype: 'speedcloud.pipeline.task.PipelineTaskEditPanel'
    ,requires:[
        'AM.store.common.SimpleConfigStore'
        ,'AM.store.speedcloud.project.ProjectStore'
        ,'AM.model.speedcloud.pipeline.task.PipelineTaskAction'
        ,'AM.view.speedcloud.pipeline.task.PipelineTaskActionEditPanel'
        ,'AM.view.speedcloud.pipeline.task.PipelineTaskEditController'
        ,'AM.model.speedcloud.pipeline.task.PipelineTaskParam'
    ]
    ,layout: 'border'
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

        var pipelineTaskTaskTypeStore = Ext.create("AM.store.common.SimpleConfigStore")
        pipelineTaskTaskTypeStore.proxy.isSynchronous = true;
        pipelineTaskTaskTypeStore.proxy.extraParams={searchCondition:{configType:'PIPELINETASK-TASKTYPE'}};
        pipelineTaskTaskTypeStore.load();

        Ext.apply(me, {
            items: [
                {
                    xtype:'panel'
                    , region:'west'
                    , width:'30%'
                    ,bind:{title:'任务名称:{record.name}'}
                    ,autoScroll:true
                    , collapsible:true
                    , split: true
                    ,frame:true
                    ,items:[
                        {
                            xtype:'grid', title:''

                            ,bind:{store:"{actionStore}"}
                            ,reference:'actionGrid'
                            ,columnLines:true
                            ,columns:[
                                {
                                    xtype: 'gridcolumn'
                                    ,width:'30px'
                                    ,dataIndex: 'execIndex1'
                                }
                                ,{
                                    xtype: 'gridcolumn'
                                    ,dataIndex: 'name'
                                    // ,text: '操作名称'
                                    ,flex:1
                                }
                                ,{
                                    xtype: 'actioncolumn'
                                    ,menuDisabled: true
                                    ,width:'30px'
                                    ,items: [{
                                        iconCls: 'delete'
                                        ,tooltip: '删除'
                                        ,handler: function(grid, rowIndex, colIndex) {

                                            var selectedRecord = grid.getSelection()[0];

                                            var record = grid.getStore().getAt(rowIndex);


                                            record.panel.destroy();
                                            record.drop();

                                            var contentCardPanel = me.down('#contentCardPanel');

                                            if(!selectedRecord){
                                                return;
                                            }

                                            if(selectedRecord != record){
                                                grid.getSelectionModel().select(selectedRecord)
                                                contentCardPanel.getLayout().setActiveItem(selectedRecord.panel)
                                                return;
                                            }
                                            var nextRecord = grid.getStore().getAt(rowIndex)

                                            if(nextRecord){
                                                grid.getSelectionModel().select(nextRecord)
                                                contentCardPanel.getLayout().setActiveItem(nextRecord.panel)
                                            }
                                        }
                                    }]
                                }]
                            ,dockedItems:[
                                {
                                    xtype:'toolbar', layout:'fit',dock:'bottom'
                                    ,items:{
                                        xtype:'button', iconCls: 'x-fa fa-plus',text: '新增任务操作',scale: 'medium'

                                        ,handler:function(){

                                            var action = Ext.create('AM.model.speedcloud.pipeline.task.PipelineTaskAction', {name:'执行Shell命令', memo:'执行Shell命令'});

                                            var grid = me.down('grid');
                                            grid.getStore().add(action);

                                            var contentCardPanel = me.down('#contentCardPanel');

                                            var panel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionEditPanel', {viewModel:{data:{record:action}}, frame:true});
                                            action.panel = panel;

                                            contentCardPanel.add(panel);
                                            contentCardPanel.getLayout().setActiveItem(panel)

                                        }
                                    }
                                }
                            ]
                            ,listeners:{
                                rowclick:function(table, record, element, rowIndex, e, eOpts){
                                    var contentCardPanel = me.lookup('contentCardPanel');

                                    if(!record.dropped) {
                                        if(!record.panel){
                                            record.panel = Ext.create('AM.view.speedcloud.pipeline.task.PipelineTaskActionEditPanel', {viewModel:{data:{record:record}}, frame:true});
                                        }
                                        contentCardPanel.getLayout().setActiveItem(record.panel)

                                    }
                                }
                            }
                            ,viewConfig: {
                                plugins: {
                                    ptype: 'gridviewdragdrop'
                                    ,dragText: '拖放排序'
                                }
                            }
                        }
                    ]
                    ,tools: [
                        {
                            iconCls: 'x-fa fa-wrench'
                            ,callback:function(){
                                var contentCardPanel = me.lookup('contentCardPanel');
                                contentCardPanel.getLayout().setActiveItem(0)
                            }
                        }
                    ]
                }
                ,{
                    xtype:'panel', region:'center', layout: 'card', collapsible:false
                    ,reference:'contentCardPanel'
                    ,itemId:'contentCardPanel'
                    ,items:[
                        {
                            xtype:'panel'
                            ,frame: true
                            // ,scrollable :true
                            ,layout:'vbox'
                            ,items:[
                                {
                                    xtype: 'form'
                                    ,bodyPadding: 10
                                    ,width:'100%'
                                    ,title:''
                                    ,reference:'taskFormPanel'
                                    // ,layout:'vbox'
                                    ,bind:{title:'{record.name}'}
                                    ,fieldDefaults: {
                                        labelAlign: 'right'
                                        ,msgTarget: 'side'
                                        ,blankText:'该字段为必填项'
                                        //,anchor: '96%'
                                        ,padding: '20 0 0 0'
                                        ,maxWidth: 600
                                        ,border:1
                                        ,width:'100%'
                                    }
                                    ,items:[
                                        {
                                            xtype: 'textfield'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:false
                                            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
                                            ,itemId: 'nameField'
                                            ,reference:'nameField'
                                            ,name: 'name'
                                            ,fieldLabel: '任务名称'
                                            ,bind:'{record.name}'
                                        }
                                        ,{
                                            xtype: 'combobox'
                                            ,store: Ext.create("AM.store.speedcloud.project.ProjectStore")
                                            ,typeAhead:false
                                            ,editable:false
                                            ,displayField:'name'
                                            ,valueField:'id'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:false
                                            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
                                            ,itemId: 'projectField'
                                            ,name: 'project'
                                            ,reference:'project'
                                            ,fieldLabel: '所属产品'
                                            ,bind:'{record.project}'
                                        }
                                        ,{
                                            xtype: 'textfield'
                                            ,itemId: 'descriptionField'
                                            ,padding: '20 0 0 0'
                                            ,name: 'description'
                                            ,fieldLabel: '任务描述'
                                            ,flex:1
                                            ,bind:'{record.description}'
                                        }
                                        ,{
                                            xtype: 'combobox'
                                            ,store: pipelineTaskTaskTypeStore
                                            ,typeAhead:false
                                            ,editable:false
                                            ,displayField:'displayName'
                                            ,valueField:'code'
                                            ,hidden: false
                                            ,readOnly:false
                                            ,allowBlank:false
                                            ,forceSelection:true
                                            ,afterLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="Required">*</span>']
                                            ,itemId: 'taskTypeField'
                                            ,name: 'taskType'
                                            ,fieldLabel: '任务类型'
                                        }
                                        ,{
                                            xtype:'fieldset'
                                            ,collapsible:true
                                            ,title: '执行计划'
                                            ,items:[
                                                ,{
                                                    xtype: 'radiogroup'
                                                    ,hidden: false
                                                    ,readOnly:false
                                                    ,allowBlank:false
                                                    ,itemId: 'execTypeField'
                                                    ,name: 'execType'
                                                    ,reference:'execType'
                                                    ,simpleValue: true
                                                    ,fieldLabel: '执行方式'
                                                    ,defaults:{padding: '0 0 0 0'}
                                                    // ,publishes: 'value'
                                                    ,bind:'{record.execType}'
                                                    ,items: [
                                                        {boxLabel: '手工', inputValue: 'MANUAL', checked: true,}
                                                        ,{boxLabel: '每日定时执行', inputValue: 'DAILY'}
                                                        ,{boxLabel: '每周定时执行', inputValue: 'WEEKLY'}
                                                    ]
                                                }
                                                ,{
                                                    xtype: 'checkboxgroup'
                                                    ,bind:{hidden: '{record.execType!="WEEKLY"}', value:'{taskDayOfWeeksArray}'}
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'taskDayOfWeeksField'
                                                    ,reference:'taskDayOfWeeksField'
                                                    ,name: 'taskDayOfWeeks'
                                                    ,fieldLabel: '执行日'
                                                    ,defaults:{padding: '0 0 0 0'}
                                                    //,cls: 'x-check-group-alt'
                                                    ,items: [
                                                        {boxLabel: '周一', name: 'taskDayOfWeeks', inputValue:1}
                                                        ,{boxLabel: '周二', name: 'taskDayOfWeeks', inputValue:2}
                                                        ,{boxLabel: '周三', name: 'taskDayOfWeeks', inputValue:3}
                                                        ,{boxLabel: '周四', name: 'taskDayOfWeeks', inputValue:4}
                                                        ,{boxLabel: '周五', name: 'taskDayOfWeeks', inputValue:5}
                                                        ,{boxLabel: '周六', name: 'taskDayOfWeeks', inputValue:6}
                                                        ,{boxLabel: '周日', name: 'taskDayOfWeeks', inputValue:7}
                                                    ]
                                                }
                                                ,{
                                                    xtype: 'timefield'
                                                    ,format: 'H:i'
                                                    ,bind:{hidden: '{record.execType=="MANUAL" || !record.execType}', value:'{taskStartTime}'}
                                                    ,increment: 15
                                                    ,hidden: true
                                                    ,readOnly:false
                                                    ,allowBlank:true
                                                    ,editable:false
                                                    ,afterLabelTextTpl: []
                                                    ,itemId: 'taskStartTimeField'
                                                    ,reference:'taskStartTimeField'
                                                    ,name: 'taskStartTime'
                                                    ,fieldLabel: '执行开始时间'
                                                }
                                            ]
                                        }


                                    ]
                                }
                                ,{
                                    xtype:'grid',collapsed: false, collapsible:true, title:'执行参数'
                                    ,width:'100%'
                                    ,scrollable :true
                                    ,margin:10
                                    ,frame:true
                                    ,flex:1
                                    ,bind:{store:"{paramStore}"}
                                    ,reference:'taskParamGrid'
                                    ,columns:[
                                        {
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'name'
                                            ,text: '参数名称'
                                            ,editor: {
                                                xtype: 'textfield'
                                            }

                                        }
                                        ,{
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'type'
                                            ,text: '参数类型'
                                            ,editor: {
                                                xtype: 'combo'
                                                // ,value:'字符类型'
                                                ,forceSelection:true
                                                ,allowBlank:false
                                                ,editable:false
                                                ,displayField:'name'
                                                ,valueField:'value'
                                                ,store:{data:[
                                                    {value:'String',name:'字符类型'}
                                                    ,{value:'enum',name:'枚举类型'}
                                                ]}
                                            }
                                            ,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                                                if(value == 'String') return '字符类型'
                                                if(value == 'enum') return '枚举类型'
                                                return value
                                            }
                                        }
                                        ,{
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'defaultValue'
                                            ,text: '默认值'
                                            ,editor: {
                                                xtype: 'textfield'
                                            }
                                            ,comboEditor:Ext.create({
                                                xtype: 'combo'
                                            })
                                            ,textEditor:Ext.create({
                                                xtype: 'textfield'
                                            })

                                        }
                                        ,{
                                            xtype: 'gridcolumn'
                                            ,dataIndex: 'description'
                                            ,text: '参数描述'
                                            ,flex: 1
                                            ,editor: {
                                                xtype: 'textfield'
                                            }
                                        }
                                        , {
                                            xtype: 'actioncolumn'
                                            , menuDisabled: true
                                            , items: [{
                                                iconCls: 'delete'
                                                , tooltip: '删除'
                                                , handler: 'onDeleteTaskParamClick'
                                            }]
                                        }
                                    ]
                                    ,dockedItems: [
                                        {
                                            xtype: 'toolbar',
                                            dock: 'top'
                                            ,items:[{
                                                xtype: 'button'
                                                ,iconCls: 'add'
                                                ,text: '新增'
                                                ,listeners: {
                                                    click: 'onAddTaskParamClick'
                                                }
                                            }]
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

                    ]
                }

            ],
            dockedItems: [
                {
                    xtype:'container'
                    ,layout:'center'
                    ,dock: 'bottom'
                    ,items: {
                        xtype: 'toolbar',

                        //ui: 'footer',
                        // layout:'center',
                        align:'center'
                        ,items: [
                        '->',{
                            xtype: 'button'
                            // ,iconCls: 'accept'
                            //,iconCls: 'x-fa fa-plus-circle'
                            ,text: '确定'
                            ,scale: 'large'
                            ,listeners: {
                                click: 'onTaskSaveButtonClick'
                            }
                        }
                        ,{
                            xtype:'button',text:'取消',scale: 'large'
                        }
                    ]
                    }
                }
            ]
            ,listeners: {
                beforeshow: me.onBeforeShow
            }
        });

        me.callParent(arguments);
    }

    ,setModel: function (model) {
        if(!model){
            Ext.Msg.show({title: '操作失败', msg: "未设置模型", buttons: Ext.Msg.OK, icon: Ext.Msg.ERROR});
            return;
        }

        this.down('form').getForm().loadRecord(model);
    }
    ,onBeforeShow:function() {

        this.down('#taskTypeField').getStore().reload();

        this.down('#projectField').getStore().reload();

    }

});