<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
.sign--wrap {
	width: 700px;
	padding: 50px;
}

.sign--div {
	display: flex;
	margin-left: 50px;
}

.sign--label {
	width: 100px;
	height: 50px;
	line-height: 50px;
}

.sign--putbox {
	width: 200px;
	hiehgt: 50px;
	line-height: 50px;
}

label {
	font-size: 20px;
}

input {
	height: 30px;
}

input:invalid {
	border: 1px solid red;
}

.sign--submit {
	width: 350px;
	margin-top: 50px;
	text-align: center;
}
</style>
<jsp:include page="../css.jsp"></jsp:include>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		console.log('onload....');

	});

	function checkPattern() {
		let input = $("#id").val();
		let regex = new RegExp('[^A-Za-z0-9]', 'gi');
		if (input.match(regex)) {
			$("#idcheck").html("ID는 영문, 숫자만 사용할 수 있습니다.");
		} else {
			$("#idcheck").html("");
		}
	}

function m_insert() {
		$.ajax({
	        url: "member_insertOK.do",
	        type: "POST",
	        dataType: "json",
	        data: {
	        	id:$('#id').val(),
	        	pw:$('#pw').val(),
	        	name:$('#name').val(),
	        	tel:$('#tel').val()
	        },
	        success: function(response) {
	            if (response.result === 1) {
	                window.location.href = "home.do";
	                alert("회원 가입에 성공했습니다!");
	            } else {
	            	alert("회원 가입에 실패했습니다. 다시 시도해주세요.");
	            }
	        },
	        error: function(xhr, status, error) {
	        }
	    });
	}
</script>
</head>
<body>
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<div class="sign block">
		<div class="sign--wrap">
			<div class="sign--div">
				<div class="sign--label">
					<label for="ID">아이디</label>
				</div>
				<div class="sign--putbox">
					<input type="text" name="id" id="id" pattern="[A-Za-z0-9]+"
						maxlength="20" oninput="checkPattern()">
				</div>
			</div>
			<div id="idcheck"></div>
			<div class="sign--div">
				<div class="sign--label">
					<label for="PW">비밀번호</label>
				</div>
				<div class="sign--putbox">
					<input type="text" name="pw" id="pw">
				</div>
			</div>
			<div class="sign--div">
				<div class="sign--label">
					<label for="name">이름</label>
				</div>
				<div class="sign--putbox">
					<input type="text" name="name" id="name">
				</div>
			</div>
			<div class="sign--div">
				<div class="sign--label">
					<label for="tel">전화번호</label>
				</div>
				<div class="sign--putbox">
					<input type="text" name="tel" id="tel">
				</div>
			</div>
			<div class="sign--submit">
				<input type="submit" onclick="m_insert()" value="가입완료">
			</div>
		</div>
	</div>
</body>
</html>