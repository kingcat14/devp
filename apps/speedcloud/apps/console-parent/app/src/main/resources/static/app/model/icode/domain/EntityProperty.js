Ext.define('AM.model.icode.domain.EntityProperty', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'icode/domain/entityproperty'
        ,listeners: {
            exception: {
                fn:  function(server, response, operation, options) {
                    var errorBody = Ext.decode(response.responseText);
                    Ext.MessageBox.alert('操作失败('+errorBody.code+')', errorBody.message);
                }
            }
        }
    },
    fields: [
    	{
            name: 'id'
            ,type:'string'
            ,allowNull:true
        }
    	,{
            name: 'tid'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'entity'
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
            name: 'name'
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
            name: 'relatedEntityId'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'relatedEntityPropertyId'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'idx'
            ,type:'int'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'memo'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    	,{
            name: 'primaryKey'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'search'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'editable'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'nullable'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'visible'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'persist'
            ,type:'bool'
            ,allowNull:false
            ,critical:true
        }
    ]
});