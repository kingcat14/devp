Ext.define('AM.model.icode4.system.Module', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'system/module'
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
            name: 'parentModuleId'
            ,type:'string'

	        ,allowNull:true
            ,critical:true
        }
        ,{
            name: 'parentModuleName'
            ,type:'string'
            ,persist:false
        }
        ,{
            name: 'systemId'
            ,type:'string'

	        ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'systemName'
            ,type:'string'
            ,persist:false
        }
    ]
});