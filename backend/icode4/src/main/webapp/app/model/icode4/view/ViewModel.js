Ext.define('AM.model.icode4.view.ViewModel', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'view/viewModel'
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
            name: 'viewIndex'
            ,type:'int'
            ,allowNull:true
            ,critical:true
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
    ]
});