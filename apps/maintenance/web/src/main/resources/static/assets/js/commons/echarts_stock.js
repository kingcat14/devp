/**
 * Created by kingcat on 2016/11/19.
 */
function splitData(rawData) {
    var categoryData = [];
    var values = [];
	var volumes = [];
    for (var i = 0; i < rawData.length; i++) {


        categoryData.push(rawData[i].date);
        values.push([rawData[i].open/100, rawData[i].close/100,rawData[i].low/100, rawData[i].high/100, rawData[i].volume, rawData[i].zhangDieFu/100]);
	    volumes.push(rawData[i].volume);

    }

    return {
        categoryData: categoryData,
        values: values,
	    volumes: volumes
    };
}
function getClose(dataSet1){
	var result = [];
	for (var i = 0, len = dataSet1.values.length; i < len; i++) {
		result.push(dataSet1.values[i][1]);
	}
	return result;
}
function calculateMA(dayCount, dataSet2) {
    var result = [];
    for (var i = 0, len = dataSet2.values.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += dataSet2.values[i - j][1];
        }
        result.push((sum / dayCount).toFixed(2));
    }
    return result;
}

function get_common_stock_chart_config() {

	var stockViewData = {
        categoryData: [],
        values: []
    };

	var config = {
		backgroundColor: '#eee',
		animation: false,
		legend: {
			bottom: 10,
			left: 'center',
			data: ['日K', '收盘', 'MA5', 'MA10', 'MA20', 'MA30']
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross'
			},
			backgroundColor: 'rgba(245, 245, 245, 0.8)',
			borderWidth: 1,
			borderColor: '#ccc',
			padding: 10,
			textStyle: {
				color: '#000'
			},
			formatter:function(params){

				var param = params[0];
				return [
					'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
					'Open: ' + param.data[0] + '<br/>',
					'Close: ' + param.data[1] + '<br/>',
					'Lowest: ' + param.data[2] + '<br/>',
					'Highest: ' + param.data[3] + '<br/>',
					'Volume: ' + param.data[4] + '<br/>',
					'ZhangDieFu: ' + param.data[5] + '%<br/>'
				].join('');

			},
			position: function (pos, params, el, elRect, size) {
				var obj = {top: 10};
				obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
				return obj;
			},
			extraCssText: 'width: 170px'
		},
		axisPointer: {
			link: {xAxisIndex: 'all'},
			label: {
				backgroundColor: '#777'
			}
		},
		toolbox: {
			feature: {
				dataZoom: {
					yAxisIndex: false
				},
				brush: {
					type: ['lineX', 'clear']
				}
			}
		},
		brush: {
			xAxisIndex: 'all',
			brushLink: 'all',
			outOfBrush: {
				colorAlpha: 0.1
			}
		},
		grid: [
			{
				left: '5%',
				right: '5%',
				height: '50%'
			},
			{
				left: '5%',
				right: '5%',
				top: '63%',
				height: '16%'
			}
		],
		xAxis: [
			{
				type: 'category',
				data: stockViewData.categoryData,
				scale: true,
				boundaryGap : false,
				axisLine: {onZero: false},
				splitLine: {show: false},
				splitNumber: 20,
				min: 'dataMin',
				max: 'dataMax',
				axisPointer: {
					z: 100
				}
			},
			{
				type: 'category',
				gridIndex: 1,
				data: stockViewData.categoryData,
				scale: true,
				boundaryGap : false,
				axisLine: {onZero: false},
				axisTick: {show: false},
				splitLine: {show: false},
				axisLabel: {show: false},
				splitNumber: 20,
				min: 'dataMin',
				max: 'dataMax',
				axisPointer: {
					label: {
						formatter: function (params) {

							var seriesValue = (params.seriesData[0] || {}).value;

							return params.value
								+ (seriesValue != null
										? '\n' + echarts.format.addCommas(seriesValue)
										: ''
								);
						}
					}
				}
			}
		],
		yAxis: [
			{
				scale: true,
				splitArea: {
					show: true
				}
			},
			{
				scale: true,
				gridIndex: 1,
				splitNumber: 2,
				axisLabel: {show: false},
				axisLine: {show: false},
				axisTick: {show: false},
				splitLine: {show: false}
			}
		],
		dataZoom: [
			{
				type: 'inside',
				xAxisIndex: [0, 1]
//					,start: 98,
//					,end: 100
				,startValue:98
				,endValue:100
			},
			{
				show: true,
				xAxisIndex: [0, 1],
				type: 'slider',
				top: '85%'
//					,start: 98
//					,end: 100
				,startValue:98
				,endValue:100
			}
		],
		series: [
			{
				name: '日K',
				type: 'candlestick',
				data: [],
				itemStyle: {
					normal: {
//							color: '#06B800',
//							color0: '#FA0000',
						borderColor: null,
						borderColor0: null
					}
				},
				tooltip: {
					formatter: function (param) {
						param = param[0];
						return [
							'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
							'Open: ' + param.data[0] + '<br/>',
							'Close: ' + param.data[1] + '<br/>',
							'Lowest: ' + param.data[2] + '<br/>',
							'Highest: ' + param.data[3] + '<br/>',
							'ZhangDieFu: ' + param.data[4] + '%<br/>'
						].join('');
					}
				}
			}
			,{
				name: '收盘',
				type: 'line',
				data: getClose(stockViewData),
				smooth: true,
				markPoint: {
					label: {
						normal: {
							formatter: function (param) {
								return param != null ? Math.round(param.value) : '';
							}
						}
					},
					data: [],
					tooltip: {
						formatter: function (param) {
							return param.name + '<br>' + (param.data.coord || '');
						}
					}
				}
			}, {
				name: 'MA5',
				type: 'line',
				data: calculateMA(5, stockViewData),
				smooth: true,
				lineStyle: {
					normal: {opacity: 0.5}
				}
			},
			{
				name: 'MA10',
				type: 'line',
				data: calculateMA(10, stockViewData),
				smooth: true,
				lineStyle: {
					normal: {opacity: 0.5}
				}
			},
			{
				name: 'MA20',
				type: 'line',
				data: calculateMA(20, stockViewData),
				smooth: true,
				lineStyle: {
					normal: {opacity: 0.5}
				}
			},
			{
				name: 'MA30',
				type: 'line',
				data: calculateMA(30, stockViewData),
				smooth: true,
				lineStyle: {
					normal: {opacity: 0.5}
				}
			},
			{
				name: 'Volume',
				type: 'bar',
				xAxisIndex: 1,
				yAxisIndex: 1,
				data: stockViewData.volumes
			}
		]
	}

	return config;
}
function create_stock_chart_config(stockViewData, title, startValue, endValue) {
    var config = {
	    backgroundColor: '#eee',
	    animation: false,
	    legend: {
		    bottom: 10,
		    left: 'center',
		    data: ['日K', '收盘', 'MA5', 'MA10', 'MA20', 'MA30']
	    },
	    tooltip: {
		    trigger: 'axis',
		    axisPointer: {
			    type: 'cross'
		    },
		    backgroundColor: 'rgba(245, 245, 245, 0.8)',
		    borderWidth: 1,
		    borderColor: '#ccc',
		    padding: 10,
		    textStyle: {
			    color: '#000'
		    },
		    formatter:function(params){

			    // var param = params[0];
			    var param = params[0];
			    return [
				    'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
				    'Open: ' + param.data[0] + '<br/>',
				    'Close: ' + param.data[1] + '<br/>',
				    'Lowest: ' + param.data[2] + '<br/>',
				    'Highest: ' + param.data[3] + '<br/>',
				    'Volume: ' + param.data[4] + '<br/>',
				    'ZhangDieFu: ' + param.data[5] + '%<br/>'
			    ].join('');

		    },
		    position: function (pos, params, el, elRect, size) {
			    var obj = {top: 10};
			    obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
			    return obj;
		    },
		    extraCssText: 'width: 170px'
	    },
	    axisPointer: {
		    link: {xAxisIndex: 'all'},
		    label: {
			    backgroundColor: '#777'
		    }
	    },
	    toolbox: {
		    feature: {
			    dataZoom: {
				    yAxisIndex: false
			    },
			    brush: {
				    type: ['lineX', 'clear']
			    }
		    }
	    },
	    brush: {
		    xAxisIndex: 'all',
		    brushLink: 'all',
		    outOfBrush: {
			    colorAlpha: 0.1
		    }
	    },
	    grid: [
		    {
			    left: '5%',
			    right: '5%',
			    height: '50%'
		    },
		    {
			    left: '5%',
			    right: '5%',
			    top: '63%',
			    height: '16%'
		    }
	    ],
	    xAxis: [
		    {
			    type: 'category',
			    data: stockViewData.categoryData,
			    scale: true,
			    boundaryGap : false,
			    axisLine: {onZero: false},
			    splitLine: {show: false},
			    splitNumber: 20,
			    min: 'dataMin',
			    max: 'dataMax',
			    axisPointer: {
				    z: 100
			    }
		    },
		    {
			    type: 'category',
			    gridIndex: 1,
			    data: stockViewData.categoryData,
			    scale: true,
			    boundaryGap : false,
			    axisLine: {onZero: false},
			    axisTick: {show: false},
			    splitLine: {show: false},
			    axisLabel: {show: false},
			    splitNumber: 20,
			    min: 'dataMin',
			    max: 'dataMax',
			    axisPointer: {
				    label: {
					    formatter: function (params) {

						    var seriesValue = (params.seriesData[0] || {}).value;

						    return params.value
							    + (seriesValue != null
									    ? '\n' + echarts.format.addCommas(seriesValue)
									    : ''
							    );
					    }
				    }
			    }
		    }
	    ],
	    yAxis: [
		    {
			    scale: true,
			    splitArea: {
				    show: true
			    }
		    },
		    {
			    scale: true,
			    gridIndex: 1,
			    splitNumber: 2,
			    axisLabel: {show: false},
			    axisLine: {show: false},
			    axisTick: {show: false},
			    splitLine: {show: false}
		    }
	    ],
	    dataZoom: [
		    {
			    type: 'inside',
			    xAxisIndex: [0, 1]
//					,start: 98,
//					,end: 100
			    ,startValue:startValue
			    ,endValue:endValue
		    },
		    {
			    show: true,
			    xAxisIndex: [0, 1],
			    type: 'slider',
			    top: '85%'
//					,start: 98
//					,end: 100
			    ,startValue:startValue
			    ,endValue:endValue
		    }
	    ],
	    series: [
		    {
			    name: '日K',
			    type: 'candlestick',
			    data: stockViewData.values,
			    itemStyle: {
				    normal: {
//							color: '#06B800',
//							color0: '#FA0000',
					    borderColor: null,
					    borderColor0: null
				    }
			    },
			    tooltip: {
				    formatter: function (param) {
					    console.log("日K:"+JSON.stringify(param,' ',4));
					    param = param[0];
					    return [
						    'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
						    'Open: ' + param.data[0] + '<br/>',
						    'Close: ' + param.data[1] + '<br/>',
						    'Lowest: ' + param.data[2] + '<br/>',
						    'Highest: ' + param.data[3] + '<br/>',
						    'ZhangDieFu: ' + param.data[4] + '%<br/>'
					    ].join('');
				    }
			    }
			    ,markPoint: {
				    label: {
					    normal: {
						    formatter: function (param) {
							    return param != null ? Math.round(param.value) : '';
						    }
					    }
				    },
				    data: [],
				    tooltip: {
					    formatter: function (param) {
						    return param.name + '<br>' + (param.data.coord || '');
					    }
				    }
			    }
		    },{
			    name: '收盘',
			    type: 'line',
			    data: getClose(stockViewData),
			    smooth: true,
			    markPoint: {
				    label: {
					    normal: {
						    formatter: function (param) {
							    return param != null ? Math.round(param.value) : '';
						    }
					    }
				    },
				    data: [],
				    tooltip: {
					    formatter: function (param) {
						    return param.name + '<br>' + (param.data.coord || '');
					    }
				    }
			    }
		    },
		    {
			    name: 'MA5',
			    type: 'line',
			    data: calculateMA(5, stockViewData),
			    smooth: true,
			    lineStyle: {
				    normal: {opacity: 0.5}
			    }
		    },
		    {
			    name: 'MA10',
			    type: 'line',
			    data: calculateMA(10, stockViewData),
			    smooth: true,
			    lineStyle: {
				    normal: {opacity: 0.5}
			    }
		    },
		    {
			    name: 'MA20',
			    type: 'line',
			    data: calculateMA(20, stockViewData),
			    smooth: true,
			    lineStyle: {
				    normal: {opacity: 0.5}
			    }
		    },
		    {
			    name: 'MA30',
			    type: 'line',
			    data: calculateMA(30, stockViewData),
			    smooth: true,
			    lineStyle: {
				    normal: {opacity: 0.5}
			    }
		    },
		    {
			    name: 'Volume',
			    type: 'bar',
			    xAxisIndex: 1,
			    yAxisIndex: 1,
			    data: stockViewData.volumes
		    }
	    ]
    }

    return config;
}