Ext.define('AM.model.speedcloud.pipeline.jenkins.JenkinsAdapter', {
    extend: 'Ext.data.Model'
    ,proxy: {
        type: "rest"
        ,writer:{writeRecordId:false, partialDataOptions:{changes:false}}
        ,headers:{"Accept":"application/json"}
        ,url: 'speedcloud/pipeline/jenkins/jenkinsadapter'
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
            name: 'project'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'env'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'port'
            ,type:'int'
            ,allowNull:false
            ,critical:true
        }
    	,{
            name: 'host'
            ,type:'string'
            ,allowNull:false
            ,critical:true
        }
    ]
});