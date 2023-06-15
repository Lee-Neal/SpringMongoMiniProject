<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	localStorage.setItem('name','kim');
	sessionStorage.setItem('tel','010');

</script>
</head>
<body>
	<div id="welcome"><h1>${user_id}님 환영합니다.</h1></div>
	<div id="no_welcome"><h1>Index</h1></div>
	<jsp:include page="top_menu.jsp"></jsp:include>
	<h1>${user_id}</h1>
	<h1>${user_name}</h1>
	<h1>${cookie.user_name.value}</h1>
<script type="text/javascript">
if('${user_id}'===''){
	$('#no_welcome').show();
	$('#welcome').hide();
}else{
	$('#no_welcome').hide();
	$('#welcome').show();
}
</script>
</body>
</html>