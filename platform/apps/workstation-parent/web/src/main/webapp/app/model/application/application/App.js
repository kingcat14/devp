Ext.define('AM.model.application.application.App', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'application/app'
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
            name: 'tenantId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'appCategoryCode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'appCategoryId'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'label'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'enable'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'onBoardTime'
            ,type:'date'
            ,dateFormat: 'time'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'url'
            ,type:'string'
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
            name: 'visible'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});