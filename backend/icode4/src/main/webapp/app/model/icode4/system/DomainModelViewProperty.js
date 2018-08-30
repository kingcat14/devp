Ext.define('AM.model.icode4.system.DomainModelViewProperty', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'system/domainModelViewProperty'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'viewIndex'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'code'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'name'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'addViewable'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'addable'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'addRequired'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'editViewable'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'editable'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'editRequired'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'searchCondition'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'girdColumn'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'simpleSearchCondition'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'domainModelId'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});