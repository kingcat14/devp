Ext.define('AM.model.icode4.database.DatabaseProject', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'database/databaseProject'
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
            name: 'url'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'username'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'password'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'driverName'
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
    ]
});