Ext.define('AM.model.icode4.database.DatabaseTableInfo', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'database/databaseTableInfo'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'connectionId'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'connectionUrl'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'moduleName'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'tableName'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'tableDisplayName'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'entityName'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'tableDesc'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});