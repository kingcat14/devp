Ext.define('AM.model.product.sys.DevpSysElmInstInfo', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'sys/devpSysElmInstInfo'
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
            name: 'contRid'
            ,type:'string'
            ,allowNull:false
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