Ext.define('AM.view.speedcloud.pipeline.jenkins.JenkinsAdapterSearchWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'speedcloud.pipeline.jenkins.JenkinsAdapterSearchWindow'
    ,alias: 'widget.speedcloudpipeline.jenkinsJenkinsAdapterSearchWindow'
    ,autoScroll: true
    ,height: '60%'
    ,width: '60%'
    ,layout: {
        type: 'fit'
    }
    ,title: 'JenkinsAdapter高级查询'
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
                            xtype: 'combobox'
                            ,store: Ext.create("AM.store.speedcloud.env.AppEnvConfigStore")
                            ,typeAhead:false
                            ,editable:false
                            ,displayField:'name'
                            ,valueField:'id'
                            ,itemId: 'envField'
                            ,fieldLabel: '所属环境'
                        }

                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'portField'
                            ,fieldLabel: '端口'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'portMaxField'
                            ,fieldLabel: '端口'
                        }
                        ,{
                            xtype: 'numberfield'
                            ,allowDecimals:false
                            ,itemId: 'portMinField'
                            ,fieldLabel: '端口'
                        }

                        ,{
                            xtype: 'textfield'
                            ,itemId: 'hostField'
                            ,fieldLabel: 'IP'
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
        var envField = me.down("#envField");
        var portField = me.down("#portField");
        var portMaxField = me.down("#portMaxField");
        var portMinField = me.down("#portMinField");
        var hostField = me.down("#hostField");

        var condition = {
            project:Ext.valueFrom(projectField.getValue(), null)
            ,env:Ext.valueFrom(envField.getValue(), null)
            ,port:Ext.isNumber(portField.getValue())?portField.getValue():null
            ,portMax:Ext.isNumber(portMaxField.getValue())?portMaxField.getValue():null
            ,portMin:Ext.isNumber(portMinField.getValue())?portMinField.getValue():null
            ,host:Ext.valueFrom(hostField.getValue(), null)
        };

        return condition;
    }

});