Ext.define('AM.model.icode4.microservice.TransModelProperty', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'microservice/transModelProperty'
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
            name: 'type'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'transModelId'
            ,type:'string'

	        ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'transModelName'
            ,type:'string'
            ,persist:false
        }
    	,{
            name: 'editable'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'nullable'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'memo'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'coreRelation'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'domainModelId'
            ,type:'string'

	        ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'domainModelName'
            ,type:'string'
            ,persist:false
        }
        ,{
            name: 'domainModelPropertyId'
            ,type:'string'

	        ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'domainModelPropertyName'
            ,type:'string'
            ,persist:false
        }
    ]
});