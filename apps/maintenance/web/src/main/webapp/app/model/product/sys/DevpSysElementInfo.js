Ext.define('AM.model.product.sys.DevpSysElementInfo', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'sys/devpSysElementInfo'
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
            ,allowNull:false
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
            name: 'prdRid'
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
            name: 'instRid'
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
            name: 'infoValue1'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoValue2'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoValue3'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoValue4'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'infoValue5'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'notes'
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