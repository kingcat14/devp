Ext.define('AM.model.icode4.system.DomainModel', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'system/domainModel'
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
            name: 'viewIndex'
            ,type:'int'
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
            name: 'systemId'
            ,type:'string'

	        ,allowNull:true
            ,critical:true
        }
        ,{
            name: 'systemName'
            ,type:'string'
            ,persist:false
        }
    	,{
            name: 'persist'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    ]
});