function initSelect(config, id, url, idField, displayField){
    $("#"+config.id).select2({
        placeholder: config.placeholder,
//                theme: "classic",
        width: "100%",
        ajax: {
            type: "GET",
            url: config.url,
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    start:0,
                    limit:100
                };
            },
            processResults: function (data, params) {
                // parse the results into the format expected by Select2
                // since we are using custom formatting functions we do not need to
                // alter the remote JSON data, except to indicate that infinite
                // scrolling can be used
                params.page = params.page || 0;
                params.limit = params.limit || 10000;
                var options = [];
                for(var i= 0, len = data.content.length;i<len;i++){
                    var option = {"id":data.content[i][config.idField], "text":data.content[i][config.displayField]};
                    options.push(option);
                }

                return {
                    results: options,
                    pagination: {
                        more: ((params.page+1) * 30) < data.totalElements
                    }
                };
            },
            cache: true
        },

        minimumResultsForSearch: Infinity,
//                formatSelection: resultFormatSelection,  // 设定查询样式
//                formatResult: resultFormatResult,　　　　// 设定查询结果样式
//                dropdownCssClass: "bigdrop", 　　　　// 设定SELECT2下拉框样式，bigdrop样式并不在CSS里定义，暂时没深入研究
        escapeMarkup: function (markup) { return markup; } // let our custom formatter work

    })
}
