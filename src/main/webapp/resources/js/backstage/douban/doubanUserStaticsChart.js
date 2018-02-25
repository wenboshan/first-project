function getContextPath() {
	var localObj = window.location;
	return localObj.protocol + "//" + localObj.host + "/"
			+ localObj.pathname.split("/")[1];
}

$.get(getContextPath() + "/douban/spider/doubanUserChart", function(data) {
	var myChart = echarts.init(document.getElementById('doubanUserChart'));
	 option = {
		        tooltip : {
		            trigger: 'axis',
		            axisPointer: {
		                type: 'shadow',
		                label: {
		                    show: true
		                }
		            }
		        },
		        toolbox: {
		            show : true,
		            feature : {
		                mark : {show: true},
		                dataView : {show: true, readOnly: false},
		                magicType: {show: true, type: ['line', 'bar','pie']},
		                restore : {show: true},
		                saveAsImage : {show: true}
		            }
		        },
		        calculable : true,
		        grid: {
		            top: '12%',
		            left: '1%',
		            right: '10%',
		            containLabel: true
		        },
		        xAxis: [
		            {
		                type : 'category',
		                data : data.data[0]
		            }
		        ],
		        yAxis: [
		            {
		                type : 'value',
		                name : '人数（1000）',
		                axisLabel: {
		                    formatter: function (a) {
		                        a = +a;
		                        return isFinite(a)
		                            ? echarts.format.addCommas(+a)
		                            : '';
		                    }
		                }
		            }
		        ],
		        dataZoom: [
		            {
		                show: true,
		                start: 94,
		                end: 100
		            },
		            {
		                type: 'inside',
		                start: 94,
		                end: 100
		            },
		            {
		                show: true,
		                yAxisIndex: 0,
		                filterMode: 'empty',
		                width: 30,
		                height: '80%',
		                showDataShadow: false,
		                left: '93%'
		            }
		        ],
		        series : [
		            {
		                name: '豆瓣用户数据',
		                type: 'bar',
		                data: data.data[1]
		            }
		        ]
		    };
	myChart.setOption(option);
});

//function getContextPath() {
//	var localObj = window.location;
//	return localObj.protocol + "//" + localObj.host + "/"
//			+ localObj.pathname.split("/")[1];
//}
//
//$.get(getContextPath() + "/douban/spider/doubanUserChart", function(data) {
//	var myChart = echarts.init(document.getElementById('doubanUserChart'));
//	var dataAxis = data.data[0];
//	var data =data.data[1];
//	var yMax = 500;
//	var dataShadow = [];
//	for (var i = 0; i < data.length; i++) {
//	    dataShadow.push(yMax);
//	}
//
//	option = {
//	    title: {
//	        text: '特性示例：渐变色 阴影 点击缩放',
//	        subtext: 'Feature Sample: Gradient Color, Shadow, Click Zoom'
//	    },
//	    xAxis: {
//	        data: dataAxis,
//	        axisLabel: {
//	            inside: true,
//	            textStyle: {
//	                color: '#fff'
//	            }
//	        },
//	        axisTick: {
//	            show: false
//	        },
//	        axisLine: {
//	            show: false
//	        },
//	        z: 10
//	    },
//	    yAxis: {
//	        axisLine: {
//	            show: false
//	        },
//	        axisTick: {
//	            show: false
//	        },
//	        axisLabel: {
//	            textStyle: {
//	                color: '#999'
//	            }
//	        }
//	    },
//	    dataZoom: [
//	        {
//	            type: 'inside'
//	        }
//	    ],
//	    series: [
//	        { // For shadow
//	            type: 'bar',
//	            itemStyle: {
//	                normal: {color: 'rgba(0,0,0,0.05)'}
//	            },
//	            barGap:'-100%',
//	            barCategoryGap:'40%',
//	            data: dataShadow,
//	            animation: false
//	        },
//	        {
//	            type: 'bar',
//	            itemStyle: {
//	                normal: {
//	                    color: new echarts.graphic.LinearGradient(
//	                        0, 0, 0, 1,
//	                        [
//	                            {offset: 0, color: '#83bff6'},
//	                            {offset: 0.5, color: '#188df0'},
//	                            {offset: 1, color: '#188df0'}
//	                        ]
//	                    )
//	                },
//	                emphasis: {
//	                    color: new echarts.graphic.LinearGradient(
//	                        0, 0, 0, 1,
//	                        [
//	                            {offset: 0, color: '#2378f7'},
//	                            {offset: 0.7, color: '#2378f7'},
//	                            {offset: 1, color: '#83bff6'}
//	                        ]
//	                    )
//	                }
//	            },
//	            data: data
//	        }
//	    ]
//	};
//
//	// Enable data zoom when user click bar.
//	var zoomSize = 6;
//	myChart.on('click', function (params) {
//	    console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
//	    myChart.dispatchAction({
//	        type: 'dataZoom',
//	        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
//	        endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
//	    });
//	});
//	myChart.setOption(option);
//});
