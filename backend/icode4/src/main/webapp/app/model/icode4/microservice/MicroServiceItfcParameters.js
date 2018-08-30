Ext.define('AM.model.icode4.microservice.MicroServiceItfcParameters', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'microservice/microServiceItfcParameters'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'viewIndex'
            ,type:'int'
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
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'type'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'pathMapping'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'memo'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
        ,{
            name: 'microServiceItfcId'
            ,type:'string'

	        ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'microServiceItfcName'
            ,type:'string'
            ,persist:false
        }
    	,{
            name: 'required'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    ]
});