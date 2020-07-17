<%@ page pageEncoding="utf-8"%>
<%-- 引入JSTL标签库 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<title>门店管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="js/echarts.js"></script>
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<style type="text/css">
	body{ font-family: "微软雅黑"; background-color: #EDEDED; }
	h2{ text-align: center;}
	table{ width:96%; margin: 0 auto; text-align: center; border-collapse:collapse; font-size:16px;}
	td, th{ padding: 5px;}
	th{ background-color: #DCDCDC; width:120px; }
	th.width-40{ width: 40px; }
	th.width-70{ width: 70px; }
	th.width-80{ width: 80px; }
	hr{ margin-bottom:20px; border:1px solid #aaa; }
	#add-door{text-align:center;font-size:20px;}
</style>
</head>
<body><!-- body-start  -->

<h2>门店管理</h2>
<div id="add-door">
	<a href="door_add.jsp" target="rightFrame">新增门店</a>
	<a>&nbsp &nbsp|&nbsp &nbsp</a>
	<a href="#" onclick="showsale()" target="rightFrame">查看门店销售额</a>
	<div style="float: right">
		<form action="${pageContext.request.contextPath}/downexcel">
			<input type="submit" value="报表导出">
		</form>
	</div>
</div>
<hr/>
<table border="1">
	<tr>
		<th class="width-40">序号</th>
		<th>门店名称</th>
		<th class="width-80">联系电话</th>
		<th>门店地址</th>
		<th class="width-80">操 作</th>
	</tr>

	<!-- 模版数据 -->
	<%--<tr>
		<td>1</td>
		<td>永和大王(北三环西路店)</td>
		<td>010-62112313</td>
		<td>北三环西路甲18号院-1号大钟寺中坤广场d座</td>
		<td>
			<a href="doorDelete?id=">删除</a>
			&nbsp;|&nbsp;
			<a href="doorInfo?id=">修改</a>
		</td>
	</tr>--%>

	<c:forEach items="${list}" var="door" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${door.name}</td>
			<td>${door.tel}</td>
			<td>${door.addr}</td>
			<td>
				<a href="${pageContext.request.contextPath}/doorDelete?id=${door.id}">删除</a>
				&nbsp;|&nbsp;
				<a href="${pageContext.request.contextPath}/doorInfo?id=${door.id}">修改</a>
			</td>
		</tr>
	</c:forEach>
</table>
<div style="width: 100%; float: none; display: block">
	<div id="main" style="width: 1100px; height: 350px; margin: 0 auto;display: none" >

	</div>
</div>
<script type="text/javascript">
	function showsale() {
		$("#main").css("display","block")

		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		// 指定图表的配置项和数据
		var option = {
			title: {
				text: '门店年销售额'
			},
			tooltip: {},
			legend: {
				data:['销售额']
			},
			xAxis: {
				data: []
			},
			yAxis: {},
			series: [{
				name: '销售额',
				type: 'bar',
				data: []
			}]
		};
		//设置加载动画
		myChart.showLoading();
		//定义数据接收后台返回的数据
		var names = [];//用来接收店铺名称
		var sales = [];//用来接收销量

		//利用ajax 请求发起数据请求
		$.ajax({
			url: "showsale",
			type: "post",
			data: {}, //浏览器给服务器传送的参数
			dataType: "json", //服务器给浏览器的参数类型
			success: function (result) {
				console.log(result)
				for (var i = 0; i < result.length; i++) {
					names.push(result[i].name);
					sales.push(result[i].sale);
				}
				//隐藏加载动画
				myChart.hideLoading();
				console.log(names);
				console.log(sales);
				myChart.setOption({
					xAxis: {
						data: names
					},
					series: [{
						data: sales
					}]
				})

			}
		})
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	}
</script>
</body><!-- body-end  -->
</html>



