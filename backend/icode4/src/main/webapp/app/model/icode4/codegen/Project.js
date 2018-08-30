Ext.define('AM.model.icode4.codegen.Project', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'codegen/project'
    },
    fields: [
    	{
            name: 'id',
            type:'string',
            allowNull:true
        }
    	,{
            name: 'groupCode'
            ,type:'string'
            ,allowNull:true
            ,critical:true
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
            name: 'number'
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
            name: 'basePackage'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'projectPath'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'tplSetId'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});