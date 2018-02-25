var laypage;
layui.use('laypage', function() {
	laypage = layui.laypage;
	laypage.render({
		count : 100,
		layout : [ 'count', 'prev', 'page', 'next', 'limit', 'skip' ],
		jump : function(obj) {
			console.log(obj)
		}
	});
});
layui.use('table', function() {
	var table = layui.table;
	table.render({
		elem : '#doubanUser',
		url : 'http://localhost:8080/spidermans/douban/spider/doubanUser',
		cellMinWidth : 80,
		page : {
			count : 100,
			layout : [ 'count', 'prev', 'page', 'next', 'limit', 'skip' ],
		},
		limit : 13,
		cols : [ [ {
			field : 'id',
			title : 'ID',
			sort : true,
			align : 'center'
		}, {
			field : 'userName',
			title : '用户名',
			align : 'center'
		} // width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1
		// 新增
		, {
			field : 'mainPageUrl',
			title : '主页地址',
			align : 'center'
		}, {
			field : 'location',
			title : '地址',
			align : 'center'
		}, {
			field : 'userId',
			title : '用户id',
			align : 'center'
		} ] ]
	});
});

