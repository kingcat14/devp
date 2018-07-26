Ext.define('AM.model.product.sys.DevpSysDgmElement', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'sys/devpSysDgmElement'
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
            name: 'name'
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
            name: 'prdRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'dgmRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'elmRid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'elmInstRid'
            ,type:'string'
            ,allowNull:true
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