<%@ page pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8"/>
    <title></title>
</head>
	<!--
	frameset标签必须要放在head和body之间
	 -->
	<frameset rows="14%,86%" frameborder="0">
		<frame src="_top.jsp" />
		<frameset cols="180px,*">
			<frame src="_left.jsp" />
			<frame src="_right.jsp" name="rightFrame"/>
		</frameset>
	</frameset>

<body>

</body>
</html>