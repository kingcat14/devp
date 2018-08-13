Ext.define('AM.view.platform.security.RoleResourceTabWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.platformSecurityRoleResourceTabWindow',
	requires: [
        'AM.store.platform.security.RoleResourceRelationTreeStore'
        ,'AM.store.platform.platform.application.AppStore'
	],
    height: 400,
    width: 254,
    layout: {
        type: 'fit'
    },
    title: '选择功能'
    ,closeAction:'hide'
    ,initComponent: function() {
        var me = this;

        me.roleResourceRelationStore = Ext.create('AM.store.platform.security.RoleResourceRelationTreeStore');

        Ext.apply(me, {
            items: [
                {
                    xtype: 'tabpanel'


                }
            ]
            ,listeners:{

            }
        });

        me.callParent(arguments);
    }
    ,setRole : function(role){
        var me = this;
        var myMask = new Ext.LoadMask(this, {msg:"Please wait..."});

        Ext.Ajax.request({
            url: 'platform/application/app/list'
            ,method: 'POST'
            ,params: {searchCondition:{}}
            ,jsonData: {searchCondition:{}}
            ,scope:this
            ,success:function (response, opts) {

                console.log(response)
                var resultSet = Ext.decode(response.responseText);

                var appList = resultSet.content;
                for(var i in appList){

                    var store = Ext.create('Ext.data.TreeStore', {
                        root : appList[i]
                    })
                    //this.getView().add(Ext.create("AM.view.main.FunctionTreePanel" ,{title:resourceList[i].get("name"), store:store }))
                    me.down('tabpanel').add(Ext.create("Ext.panel.Panel" ,{title:appList[i].name, store:store }))
                }
                myMask.hide();
            }
            //,failure: this.onFailure,
            ,mask:myMask
        });

    }


});