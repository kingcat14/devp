Ext.define('AM.model.icode4.system.DomainModelProperty', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'system/domainModelProperty'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
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
            name: 'persist'
            ,type:'string'
            ,allowNull:false
            ,critical:true
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
            name: 'primaryKey'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'search'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'visible'
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
            name: 'relatedDomainModelId'
            ,type:'string'

	        ,allowNull:true
            ,critical:true
        }
        ,{
            name: 'relatedDomainModelName'
            ,type:'string'
            ,persist:false
        }
        ,{
            name: 'relatedDomainModelPropertyId'
            ,type:'string'

	        ,allowNull:true
            ,critical:true
        }
        ,{
            name: 'relatedDomainModelPropertyName'
            ,type:'string'
            ,persist:false
        }
    	,{
            name: 'viewIndex'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    ]
});