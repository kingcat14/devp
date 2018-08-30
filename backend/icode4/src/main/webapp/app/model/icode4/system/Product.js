Ext.define('AM.model.icode4.system.Product', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'system/product'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'productName'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'productCode'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'description'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});