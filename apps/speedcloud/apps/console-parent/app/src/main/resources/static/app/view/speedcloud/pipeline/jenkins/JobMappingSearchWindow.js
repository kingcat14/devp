Ext.define('AM.view.speedcloud.pipeline.jenkins.JobMappingSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.jenkins.JobMappingSearchWindow'
    ,alias: 'widget.speedcloudpipeline.jenkinsJobMappingSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: '任务映射高级查询'
    ,maximizable: true
    ,closeAction:'hide'
    ,initComponent: function () {
        var me = this;
        Ext.apply(me, {
            items: [
                {
                    xtype: 'form'
                    ,autoScroll: true
                    ,bodyPadding: 10
                    ,width:'100%'
                    ,fieldDefaults: {
                        labelAlign: 'top'
                        ,msgTarget: 'side'
                        ,padding: '5 0 0 5'
                        ,blankText:'该字段为必填项'
                        ,anchor: '96%'
                    }
                    ,layout: {
                        type: 'table'
                        ,columns: 2
                        ,tableAttrs: {style: {width: '100%'}}
                    }
                    ,defaults:{width:'100%'}
                    ,items:[
                        ,{
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.project.ProjectStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'projectField'
                            ,fieldLabel: '所属产品'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'taskTypeField'
                            ,fieldLabel: '类型'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'jobInPlatformField'
                            ,fieldLabel: '任务或流水线'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'jobInPlatformNameField'
                            ,fieldLabel: '任务或流水线名称'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'jobInJenkinsNameField'
                            ,fieldLabel: 'Jenkins中任务名称'
                        }

                            ]
                }
            ]
            ,dockedItems: [
                {
                    xtype: 'toolbar'
                    ,dock: 'bottom'
                    ,ui: 'footer'
                    ,items: [
                        {
                            xtype: 'tbfill'
                        }

                        ,{
                            xtype: 'button'
                            ,iconCls: 'page_white'
                            ,text: '重置'
                            ,listeners: {
                                click: {fn: me.onRestButtonClick,scope: me}
                            }
                        }
                        ,{
                            xtype: 'button'
                            ,iconCls: 'search'
                            ,text: '查询'
                            ,listeners: {
                                click: {fn: me.onSearchButtonClick,scope: me}
                            }
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,onSearchButtonClick: function (button, e, options) {

        var me = this;
        if (!this.down('form').getForm().isValid()) {
            Ext.MessageBox.alert('提交失败', '请检查数据项');
            return;
        }

        me.fireEvent('saved');
        me.hide();
    }
    ,onRestButtonClick: function (button, e, options) {
        var me = this;
        me.down('form').getForm().reset();

        me.fireEvent('saved');


    }
    ,getCondition: function(){

        var me = this;
        var projectField = me.down("#projectField");
        var taskTypeField = me.down("#taskTypeField");
        var jobInPlatformField = me.down("#jobInPlatformField");
        var jobInPlatformNameField = me.down("#jobInPlatformNameField");
        var jobInJenkinsNameField = me.down("#jobInJenkinsNameField");

        var condition = {
            project:Ext.valueFrom(projectField.getValue(), null)
            ,taskType:Ext.valueFrom(taskTypeField.getValue(), null)
            ,jobInPlatform:Ext.valueFrom(jobInPlatformField.getValue(), null)
            ,jobInPlatformName:Ext.valueFrom(jobInPlatformNameField.getValue(), null)
            ,jobInJenkinsName:Ext.valueFrom(jobInJenkinsNameField.getValue(), null)
        };

        return condition;
    }

});