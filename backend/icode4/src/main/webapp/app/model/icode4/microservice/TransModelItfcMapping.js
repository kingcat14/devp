Ext.define('AM.model.icode4.microservice.TransModelItfcMapping', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'microservice/transModelItfcMapping'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'transModelId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'microServiceItfcId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'relationType'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    ]
});