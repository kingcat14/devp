Ext.define('AM.model.icode4.microservice.MicroService', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'microservice/microService'
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
            name: 'moduleId'
            ,type:'string'

	        ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'moduleName'
            ,type:'string'
            ,persist:false
        }
    	,{
            name: 'description'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});