Ext.define('AM.model.icode.project.ComponentDomainRelation', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'icode/project/componentdomainrelation'
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
            name: 'component'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'domain'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'relationType'
            ,type:'string'
            ,allowNull:true
            ,critical:true
        }
    ]
});