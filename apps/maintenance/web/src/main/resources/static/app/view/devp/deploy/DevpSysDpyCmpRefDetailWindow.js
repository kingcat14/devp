Ext.define('AM.view.devp.deploy.DevpSysDpyCmpRefDetailWindow', {
    extend: 'Ext.window.Window'
    ,xtype: 'devp.deploy.DevpSysDpyCmpRefDetailWindow'
    ,autoScroll: true
    ,height: 350
    ,width: 700
    ,layout: {
        type: 'fit'
    }
    ,title: '系统元素间关系定义详细信息'
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
                    ,layout: {
                        type: 'anchor'
                    }
                    ,defaults: {
                        labelAlign: 'right'
                        ,xtype: 'displayfield'
                        ,padding: '5 0 0 5'
                        ,anchor: '100%'
                        ,labelWidth:150
                    }
                    ,items: [
                        ,{
                            itemId: 'etypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'etype'
                            ,fieldLabel: '元素类型'
                        }
                        ,{
                            itemId: 'codeField'
                            ,padding: '5 0 0 5'
                            ,name: 'code'
                            ,fieldLabel: '对应关系代码'
                        }
                        ,{
                            itemId: 'nameField'
                            ,padding: '5 0 0 5'
                            ,name: 'name'
                            ,fieldLabel: '对应关系名称'
                        }
                        ,{
                            itemId: 'aliasField'
                            ,padding: '5 0 0 5'
                            ,name: 'alias'
                            ,fieldLabel: '对应关系别名'
                        }
                        ,{
                            itemId: 'descriptionField'
                            ,padding: '5 0 0 5'
                            ,name: 'description'
                            ,fieldLabel: '对应关系描述'
                        }
                        ,{
                            itemId: 'recordStateField'
                            ,padding: '5 0 0 5'
                            ,name: 'recordState'
                            ,fieldLabel: '记录状态'
                        }
                        ,{
                            itemId: 'prdRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'prdRid'
                            ,fieldLabel: '产品编号'
                        }
                        ,{
                            itemId: 'schemeRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'schemeRid'
                            ,fieldLabel: '部署方案编号'
                        }
                        ,{
                            itemId: 'cmpRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'cmpRid'
                            ,fieldLabel: '组件编号'
                        }
                        ,{
                            itemId: 'refEtypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'refEtype'
                            ,fieldLabel: '关联元素类型'
                        }
                        ,{
                            itemId: 'refPrdRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'refPrdRid'
                            ,fieldLabel: '关联产品编号'
                        }
                        ,{
                            itemId: 'refElmRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'refElmRid'
                            ,fieldLabel: '关联元素编号'
                        }
                        ,{
                            itemId: 'refInstRidField'
                            ,padding: '5 0 0 5'
                            ,name: 'refInstRid'
                            ,fieldLabel: '关联元素实例编号'
                        }
                        ,{
                            itemId: 'seqField'
                            ,padding: '5 0 0 5'
                            ,name: 'seq'
                            ,fieldLabel: '顺序号'
                        }
                        ,{
                            itemId: 'typeField'
                            ,padding: '5 0 0 5'
                            ,name: 'type'
                            ,fieldLabel: '类型'
                        }
                        ,{
                            itemId: 'subTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'subType'
                            ,fieldLabel: '子类型'
                        }
                        ,{
                            itemId: 'stereotypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'stereotype'
                            ,fieldLabel: '构造型'
                        }
                        ,{
                            itemId: 'scopeField'
                            ,padding: '5 0 0 5'
                            ,name: 'scope'
                            ,fieldLabel: '范围'
                        }
                        ,{
                            itemId: 'directionField'
                            ,padding: '5 0 0 5'
                            ,name: 'direction'
                            ,fieldLabel: '方向'
                        }
                        ,{
                            itemId: 'srcMultiField'
                            ,padding: '5 0 0 5'
                            ,name: 'srcMulti'
                            ,fieldLabel: '来源对应数量'
                        }
                        ,{
                            itemId: 'srcRoleField'
                            ,padding: '5 0 0 5'
                            ,name: 'srcRole'
                            ,fieldLabel: '来源角色'
                        }
                        ,{
                            itemId: 'srcRoleTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'srcRoleType'
                            ,fieldLabel: '来源角色类型'
                        }
                        ,{
                            itemId: 'destMultiField'
                            ,padding: '5 0 0 5'
                            ,name: 'destMulti'
                            ,fieldLabel: '目标对应数量'
                        }
                        ,{
                            itemId: 'destRoleField'
                            ,padding: '5 0 0 5'
                            ,name: 'destRole'
                            ,fieldLabel: '目标角色'
                        }
                        ,{
                            itemId: 'destRoleTypeField'
                            ,padding: '5 0 0 5'
                            ,name: 'destRoleType'
                            ,fieldLabel: '目标角色类型'
                        }
                        ,{
                            itemId: 'attrRelationField'
                            ,padding: '5 0 0 5'
                            ,name: 'attrRelation'
                            ,fieldLabel: '属性对应关系'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

    ,setModel: function (model) {
        if (model && model.get('id')) {
            this.down('form').getForm().loadRecord(model);

        } else {
            this.down('form').getForm().reset();

        }
    }

});