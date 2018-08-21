Ext.define('AM.view.platform.security.RoleResourceTreeWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.platformSecurityRoleResourceTreeWindow',
	requires: [
        'AM.store.platform.security.RoleResourceRelationTreeStore'
	],
    height: '80%',
    width: 254,
    layout: {
        type: 'fit'
    },
    title: '选择功能'
    ,closeAction:'hide'
    ,initComponent: function() {
        var me = this;

        me.roleResourceRelationStore = Ext.create('AM.store.platform.security.RoleResourceRelationTreeStore');
        // me.roleResourceRelationStore = Ext.create('Ext.data.TreeStore');
        Ext.apply(me, {
            items: [
                {
                    xtype: 'treepanel'
                    //使用CheckTreeNode,生成的是带勾选框的树
                    ,store: me.roleResourceRelationStore
	                ,autoLoad: true
                    ,displayField: 'name'
                    ,rootVisible: false
                    ,checkPropagation:'down'
                    ,viewConfig: {

                    }
                    ,listeners: {
                        checkchange: {
                            fn: me.onTreepanelCheckChange,
                            scope: me
                        }
                    }
                    ,dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    iconCls: 'accept',
                                    text: '确定',
                                    listeners: {
                                        click: {
                                            fn: me.onButtonClick,
                                            scope: me
                                        }
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
            ,listeners:{

            }
        });

        me.callParent(arguments);
    }
    ,onTreepanelCheckChange: function(node, checked, options) {

        if(checked){
            var targetNode  = node;
            while(targetNode.parentNode){
                targetNode = targetNode.parentNode;
                targetNode.set('checked',checked)
            }

        }

        // node.eachChild(function(child){
        //     child.set('checked',checked);
        //     child.eachChild(function(grandson){
        //         grandson.set('checked',checked);
        //     });
        // })
    }
    ,onButtonClick: function(button, e, options) {
        var role = this.role;
        var relationStore = this.roleResourceRelationStore;


        var records = this.down('treepanel').getStore().getModifiedRecords();

	    var removedRecords = [];
	    var addRecords = [];
        for(var i in records){

        	if(records[i].get('checked')){
	            addRecords.push(records[i]);
            }else{
	            removedRecords.push(records[i]);
            }
        }


        Ext.Array.each(addRecords, function(rec){

	        var record = Ext.create('AM.model.platform.security.RoleResourceRelation', {roleId:role.get('id'),resourceId:rec.get('id')});
	        record.save({
		        success:function() {
			        Ext.MsgUtil.show('为' + role.get('name') + '分配资源', '新增资源成功');
		        }
	        });

        });

	    Ext.Array.each(removedRecords, function(rec){

		    var record = Ext.create('AM.model.platform.security.RoleResourceRelation', {
                id:rec.get('relationId')
		    });
		    record.phantom = false;
		    record.erase({
			    success:function() {
				    Ext.MsgUtil.show('为' + role.get('name') + '分配资源', '删除资源成功');
			    }
		    });

	    });



        // relationStore.sync({
        //     success:function(){
        //         Ext.MsgUtil.show('为'+role.get('name')+'分配资源成功', '新增资源'+addRecords.length+"个,删除资源"+removed.length+'个');
        //     }
        // });
        // this.down('treepanel').getStore().sync();
	    this.down('treepanel').getStore().removeAll(true);
	    this.down('treepanel').getStore().setRoot({expanded: true, name:'Root', id: '-1'})


        // this.down('treepanel').expandAll();

    }

    ,setRole : function(role){
        var me = this;me.show();
        me.role = role;
		me.setTitle('为角色' + role.get('name') + '分配资源');

		me.down('treepanel').getStore().proxy.extraParams={roleId:role.get('id'), appCode:role.get('appCode')};
		//me.down('treepanel').expandAll();
		// me.down('treepanel').getStore().reload()
		// me.down('treepanel').getStore().getRoot().reload()
		// me.down('treepanel').expandAll();

		// me.down('treepanel').getStore().reload()
		// me.down('treepanel').expandAll();

		me.down('treepanel').getStore().removeAll(true);
		me.down('treepanel').getStore().setRoot({expanded: true, name:'Root', id: '-1'})
		// me.down('treepanel').expandAll();


    }


});