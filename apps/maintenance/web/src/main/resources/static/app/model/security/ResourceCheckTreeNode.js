Ext.define('AM.model.security.ResourceCheckTreeNode', {
    extend: 'Ext.data.Model',

    fields: [
        {
            name: 'id',
            type: 'int'
        },
        {
            name: 'name',
            type: 'string'
        },
        {
            name: 'checked',
            defaultValue: false,
            type: 'boolean'
        }
    ]
});