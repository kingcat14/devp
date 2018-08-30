Ext.define('AM.model.icode4.tplfile.TplCode', {
    extend: 'Ext.data.Model',
    proxy: {
        type: "rest",
        writer:{writeRecordId:false, partialDataOptions:{changes:false}},
        headers:{"Accept":"application/json"},
        url: 'tplfile/tplCode'
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
            name: 'type'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'acceptModelTypeId'
            ,type:'string'

	        ,allowNull:false
            ,critical:true
        }
        ,{
            name: 'acceptModelTypeName'
            ,type:'string'
            ,persist:false
        }
    	,{
            name: 'filePath'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'fileName'
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
        ,{
            name: 'tplSetName'
            ,type:'string'
            ,persist:false
        }
    	,{
            name: 'overridable'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'content'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});