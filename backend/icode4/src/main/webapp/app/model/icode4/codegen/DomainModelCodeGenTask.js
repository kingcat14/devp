Ext.define('AM.model.icode4.codegen.DomainModelCodeGenTask', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'codegen/domainmodel/task'
    },
    fields: [
        {
            name: 'id',
            type:'string',
            allowNull:true
        }
        ,{
            name: 'status',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'modelId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'tplCodeId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'projectId'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    ]
});