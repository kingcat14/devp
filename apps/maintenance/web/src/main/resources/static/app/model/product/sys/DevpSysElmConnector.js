Ext.define('AM.model.product.sys.DevpSysElmConnector', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'sys/devpSysElmConnector'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'tid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'code'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'name'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'alias'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'description'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'sprdRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'selmRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'dprdRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'delmRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'sinstRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'dinstRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'seq'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'type'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'subType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'stereotype'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'scope'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'direction'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'srcMulti'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'srcRole'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'srcRoleType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'destMulti'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'destRole'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'destRoleType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'attrRelation'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'recordState'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'createUcode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'modifyUcode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});