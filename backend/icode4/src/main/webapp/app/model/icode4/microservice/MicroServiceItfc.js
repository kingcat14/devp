Ext.define('AM.model.icode4.microservice.MicroServiceItfc', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'microservice/microServiceItfc'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
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
            name: 'url'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'method'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'microServiceId'
            ,type:'string'

	        ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'microServiceName'
            ,type:'string'
            ,persist:false
        }
    	,{
            name: 'description'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
        ,{
            name: 'responseId'
            ,type:'string'

	        ,allowNull:true
            ,critical:true
        }
        ,{
            name: 'responseName'
            ,type:'string'
            ,persist:false
        }
        ,{
            name: 'requestId'
            ,type:'string'

	        ,allowNull:true
            ,critical:true
        }
        ,{
            name: 'requestName'
            ,type:'string'
            ,persist:false
        }
    ]
});