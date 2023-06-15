<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<style>
.infoWrap{
width:500px;
height:400px;
border:1px solid black;
box-sizing:border-box;
display:flex;
flex-wrap: wrap;
}
.infoKey, .infoVal{
width:40%;
height:100px;
line-height:100px;
text-align:center;
}
.infoVal{
width:60%;
}
.myPage__info__update, .member--delete{
width:250px;
}
button, .info__updatebtn{
	margin:20px 0px 0px 200px;
	width:100px;
	height:50px;
	line-height:50px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">

const urlParams = new URLSearchParams(window.location.search);
const param = {
  mid: urlParams.get('mid')
};
console.log('param...',param);
$.ajax({
	url : "member_findOne.do",
	method : 'GET',
	dataType : 'json',
	data : param,
	success : function(vo) {
		console.log('ajax...success:', vo);
		let msg = '';
		msg+=`
		<div class="myPage__infoWrap infoWrap">
			<div class="myPage__infoKey infoKey">아이디</div>
			<div class="myPage__infoVal infoVal">\${vo.id}</div>
			<div class="myPage__infoKey infoKey">비밀번호</div>
			<div class="myPage__infoVal infoVal">\${vo.pw}</div>
			<div class="myPage__infoKey infoKey">이름</div>
			<div class="myPage__infoVal infoVal">\${vo.name}</div>
			<div class="myPage__infoKey infoKey">휴대폰</div>
			<div class="myPage__infoVal infoVal">\${vo.tel}</div>
		</div>
		<div class="myPage__info__update">
			<button class="myPage__info__updatebtn info__updatebtn" onclick="m_update()">수정</button>
		</div>
		
		`;
		$(".memberInfo").html(msg);
	},
	error : function(xhr, status, error) {
		
		console.log('xhr.status:', xhr.status);
	}
});
function m_update() {
	$.ajax({
		url : "member_findOne.do",
		method : 'GET',
		dataType : 'json',
		data : param,
		success : function(vo) {
			console.log('ajax...success:', vo);
			let update_msg = '';
			update_msg+=`
			<div class="myPage__infoWrap infoWrap">
					<input id="mid" name="mid" type="hidden" value="\${vo.mid}">
					<input id="num" name="num" type="hidden" value="\${vo.num}">
				<div class="myPage__infoKey infoKey">아이디</div>
				<div class="myPage__infoVal_mid infoVal">
					<input id="id" name="id" type="text" value="\${vo.id}" readonly>
				</div>
				<div class="myPage__infoKey infoKey">비밀번호</div>
				<div class="myPage__infoVal infoVal">
					<input id="pw" name="pw" type="text" value="\${vo.pw}">
				</div>
				<div class="myPage__infoKey infoKey">이름</div>
				<div class="myPage__infoVal infoVal">
					<input id="name" name="name" type="text" value="\${vo.name}">
				</div>
				<div class="myPage__infoKey infoKey">전화번호</div>
				<div class="myPage__infoVal infoVal">
					<input id="tel" name="tel" type="text" value="\${vo.tel}">
				</div>
			</div>
			<div class="myPage__info__update">
				<input class="myPage__info__updatebtn info__updatebtn" onclick="m_updateOK()" type="submit" value="적용">
			</div>
			`;
	
			$(".memberInfo").html(update_msg);
		},
			error : function(xhr, status, error) {
			console.log('xhr.status:', xhr.status);
		}
	});
}
function m_updateOK() {
	$.ajax({
		url : "member_updateOK.do",
		method : 'POST',
		dataType : 'json',
		data : {
			mid:$('#mid').val(),
			pw:$('#pw').val(),
			name:$('#name').val(),
			tel:$('#tel').val(),
			},
		success : function(response) {
            if (response.result === 1) {
            window.location.href = window.location.href;
            alert("회원 정보 수정이 완료되었습니다.");
            }else{
            alert("회원 정보 수정이 실패했습니다.다시 시도해주시기바랍니다.");
            }
			},
			error : function(xhr, status, error) {
			console.log('xhr.status:', xhr.status);
		}
	})
}

function m_deleteOK() {
	$.ajax({
		url : "member_deleteOK.do",
		method : 'POST',
		dataType : 'json',
		data : param,
		success : function(response) {
            if (response.result === 1) {
            window.location.href = "home.do";
            alert("회원 탈퇴가 완료되었습니다.");
            }else{
            alert("회원 탈퇴가 실패했습니다.다시 시도해주시기바랍니다.");
            }
			},
			error : function(xhr, status, error) {
			console.log('xhr.status:', xhr.status);
		}
	})
}
</script>
</head>
<body>
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<h1>회원 정보</h1>
		<div class="memberInfo"></div>
		<div class="myPage__member--delete member--delete">
			<button class="myPage__member--deletebtn member--deletebtn" onclick="m_deleteOK()">회원탈퇴</button>
		</div>
</body>
</html>