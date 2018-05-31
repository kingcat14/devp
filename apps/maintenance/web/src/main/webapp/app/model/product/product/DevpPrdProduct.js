Ext.define('AM.model.product.product.DevpPrdProduct', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'product/devpPrdProduct'
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
            ,allowNull:false
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
            name: 'type'
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
            name: 'prdDept'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'prdOwner'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'devManager'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'opsManager'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'bizLine'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'bizManager'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'golive'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'majorCust'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'custManager'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'custUsage'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'acquisitionMode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'acquisitionDesc'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'version'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'phase'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'status'
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
            name: 'cmodifyUcode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});