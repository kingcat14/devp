Ext.define('AM.model.icode4.microservice.TransModel', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'microservice/transModel'
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
            name: 'memo'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'description'
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
            name: 'domainModelId'
            ,type:'string'

	        ,allowNull:true
            ,critical:true
        }
        ,{
            name: 'domainModelName'
            ,type:'string'
            ,persist:false
        }
    	,{
            name: 'viewIndex'
            ,type:'int'
            ,allowNull:false
            ,critical:true
        }
    ]
});