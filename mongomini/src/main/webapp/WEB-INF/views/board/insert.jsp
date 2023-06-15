<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 정보</title>
<style>
.boardInsertWrap{
width:470px;
height:370px;
border:1px solid black;
box-sizing:border-box;
display:flex;
justify-content: space-around;
align-items: center;
}
.modifierTab{
width:470px;
height:50px;
margin-top:10px;
display:flex;
justify-content: space-around;
align-items: center;
}
button{
width:100px;
height:50px;
}
.wrap, .boardInsertContent{
display:flex;
flex-wrap: wrap;
flex-direction: column;
}
.tab{
width:100px;
height:30px;
text-align:center;
margin:5px 5px 5px 5px;
background-color:#DEDEDE;
box-sizing:border-box;
}
.text{
width:350px;
height:30px;
margin:5px 5px 5px 5px;
background-color:#DEDEDE;
box-sizing:border-box;
overflow: hidden;
}
.boardInsert__contentTab, .boardInsert__content{
height:200px;
}
textarea{
width:100%;
height:100%;
box-sizing: border-box;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
$(document).on('click', '.insertbtn', function() {
	$.ajax({
		url : "board_insertOK.do",
		method : 'GET',
		dataType : 'json',
		data : {
				title:document.querySelector('.insert__title').value,
				content:document.querySelector('.insert__content').value,
				writer:document.querySelector('.insert__writer').value,
				wdate:new Date()
				},
		success : function(response) {
		if (response.result === 1) {
               console.log('insert OK...');
               window.location.href = "b_findAll.do";
               alert("게시글 작성이 완료되었습니다.");
           } else {
               alert("게시글 작성에 실패했습니다. 다시 시도해주시기 바랍니다.");
           }
		},
		error : function(xhr, status, error) {
		console.log('xhr.status:', xhr.status);
		}
	})
})
</script>
</head>
<body>
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<h1>글 작성</h1>
	<div class="boardInsertWrap">
		<div class="boardInsert__tabWrap wrap">
			<div class="boardInsert__titleTab tab">제목</div>
			<div class="boardInsert__contentTab tab">내용</div>
			<div class="boardInsert__writerTab tab">작성자</div>
		</div>
		<div class="boardInsertContent">
			<div class="boardInsert__title text">
				<textarea class="insert__title"></textarea>
			</div>
			<div class="boardInsert__content text">
				<textarea class="insert__content"></textarea>
			</div>
			<div class="boardInsert__writer text">
				<textarea class="insert__writer"></textarea>
			</div>
		</div>
	</div>
	<div class="modifierTab">
		<div class="modifierTab__board--insert">
			<button class="modifierTab__board--insertbtn insertbtn">게시글 작성</button>
		</div>
	</div>
</body>
</html>