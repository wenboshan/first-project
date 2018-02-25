<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/views/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${path}/resources/js/echart/echarts.min.js" type="text/javascript"></script>
<%-- <script src="${path}/resources/js/layui/lay/modules/laypage.js" type="text/javascript"></script> --%>
<title>Insert title here</title>
</head>
<body>
<div id="doubanUserChart" style="width: 100%;height:300px"></div>
</body>
<script src="${path}/resources/js/backstage/douban/doubanUserStaticsChart.js" type="text/javascript"></script>
</html>