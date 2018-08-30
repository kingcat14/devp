Ext.define('AM.model.icode4.database.DbTableSyncTask', {
    extend: 'Ext.data.Model',
    proxy: {

        type: "rest",writer:{writeRecordId:false,partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'database/databaseProjectSync'
    }
    ,fields: [
    	{
            name: 'connectionId'
            ,type:'string'
            ,allowNull:true
        }
    	,{
            name: 'tableNames'
            ,allowNull:true
        }
    ]
});
