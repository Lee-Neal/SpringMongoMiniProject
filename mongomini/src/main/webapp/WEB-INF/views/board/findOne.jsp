<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 정보</title>
<style>
.boardOneWrap {
	width: 470px;
	height: 350px;
	border: 1px solid black;
	box-sizing: border-box;
	display: flex;
	justify-content: space-around;
	align-items: center;
}

.modifierTab {
	width: 470px;
	height: 50px;
	margin-top: 10px;
	display: flex;
	justify-content: space-around;
	align-items: center;
}

.deletebtn, .updatebtn, .updateOKbtn {
	width: 100px;
	height: 50px;
}

.wrap, .boardOne__JSON {
	display: flex;
	flex-wrap: wrap;
	flex-direction: column;
}

.tab {
	width: 100px;
	height: 30px;
	text-align: center;
	margin: 5px 5px 5px 5px;
	background-color: #DEDEDE;
	box-sizing: border-box;
}

.text {
	width: 350px;
	height: 30px;
	margin: 5px 5px 5px 5px;
	background-color: #DEDEDE;
	box-sizing: border-box;
	overflow: hidden;
}

.boardOne__contentTab, .boardOne__content {
	height: 200px;
}

textarea {
	width: 100%;
	height: 100%;
	box-sizing: border-box;
}

.commentWrap {
	margin-top: 20px;
	width: 450px;
	heihgt: 100px;
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
}

.classFor {
	margin-top: 20px;
	width: 450px;
	heihgt: 100px;
	border: 1px solid black;
	display: flex;
	justify-content: space-between;
}

.comment__content {
	width: 300px;
	height: 90px;
	background-color: #DDDDDD;
}

.comment__leftTap {
	width: 140px;
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
const urlParams = new URLSearchParams(window.location.search);
const param = {
  bid: urlParams.get('bid')
};
//board 최초 로딩
$(document).ready(function(){
	console.log('param...',param);
	$.ajax({
		url : "board_findOne.do",
		method : 'GET',
		dataType : 'json',
		data : param,
		success : function(vo) {
			console.log('ajax...success:', vo);
			let msg = '';
			msg+=`
				<div class="boardOne__bid text" style="display:none">\${vo.bid}</div>
				<div class="boardOne__title text">\${vo.title}</div>
				<div class="boardOne__content text">\${vo.content}</div>
				<div class="boardOne__writer text">\${vo.writer}</div>
				<div class="boardOne__wdate text">\${new Date(vo.wdate).toLocaleString()}</div>
			`;
			let commentMsg = '';
			$(".boardOne__JSON").html(msg);
			$(".modifierTab__board--update").html(`<button class="modifierTab__board--updatebtn updatebtn">게시글 수정</button>`);
		},
		error : function(xhr, status, error) {
			
			console.log('xhr.status:', xhr.status);
		}
	})
});
//comment 최초 로딩
$(document).ready(function(){
$.ajax({
    url: "comment_findAll.do",
    method: 'GET',
    dataType: 'json',
    data: param,
    success: function(arr) {
        if (arr.length === 0) {
            $('.commentAll').hide();
        } else {
            console.log('ajax...success:', arr);
            // arr이 null이 아닌 경우에 대한 처리
            let commentMsg = '';
            for (var i = 0; i < arr.length; i++) {
			    
            commentMsg+=`
            <div class="classFor">
	            <div style="display:none;"class="comment__cnum">\${arr[i].cnum}</div>
	    		<div class="comment__content">\${arr[i].content}</div>
	    		<div class="comment__leftTap">
	    			<div class="comment__writer">\${arr[i].writer}</div>
	    			<div class="comment__wdate">\${new Date(arr[i].wdate).toLocaleString()}</div>
	    			<div class="comment__modifierTab">
	    				<div class="modifierTab__comment--update">
	    					<button class="modifierTab__comment--updatebtn cupdatebtn">댓글 수정</button>
	    				</div>
	    				<div class="modifierTab__comment--delete">
	    					<button class="modifierTab__comment--deletebtn cdeletebtn">댓글 삭제</button>
	    				</div>
	    			</div>
	    		</div>
	    	</div>
    		`;
            }
            $('.commentAll').html(commentMsg);
        }
    },
    error: function(xhr, status, error) {
        console.log('xhr.status:', xhr.status);
    }
})
});
//board 업데이트 변경
$(document).on('click', '.updatebtn', function() {
	$.ajax({
		url : "board_findOne.do",
		method : 'GET',
		dataType : 'json',
		data : param,
		success : function(vo) {
			console.log('ajax...success:', vo);
			let update_msg = '';
			update_msg+=`
			<div class="boardOne__title text">
				<textarea class="update__title">\${vo.title}</textarea></div>
			<div class="boardOne__content text">
				<textarea class="update__content">\${vo.content}</textarea></div>
				<textarea style="display: none;" class="update__bid">\${vo.bid}</textarea>
				<textarea style="display: none;" class="update__num">\${vo.num}</textarea>
				</div>
			<div class="boardOne__writer text">\${vo.writer}</div>
			<div class="boardOne__wdate text">\${new Date(vo.wdate).toLocaleString()}</div>
			`;
	
			$(".boardOne__JSON").html(update_msg);
			$(".updatebtn").hide();
			$(".modifierTab__board--update").html(`<button class="modifierTab__board--updateOKbtn updateOKbtn">수정 완료</button>`);
		},
			error : function(xhr, status, error) {
			console.log('xhr.status:', xhr.status);
		}
	})
})
// board 업데이트 적용
$(document).on('click', '.updateOKbtn', function() {
    $.ajax({
        url: "board_updateOK.do",
        method: 'GET',
        dataType: 'json',
        data: {
            bid: $('.update__bid').val(),
            num: $('.update__num').val(),
            title: $('.update__title').val(),
            content: $('.update__content').val(),
            writer: $('.boardOne__writer').text(),
            wdate: new Date()
        },
        success: function(response) {
            if (response.result === 1) {
                console.log('update OK...');
                window.location.href = window.location.href;
                alert("게시글 수정이 완료되었습니다.");
            } else {
                alert("게시글 수정에 실패했습니다. 다시 시도해주시기 바랍니다.");
            }
        },
        error: function(xhr, status, error) {
            console.log('xhr.status:', xhr.status);
        }
    });
});
//board 삭제
function b_deleteOK() {
    $.ajax({
        url: "board_deleteOK.do",
        method: 'GET',
        dataType: 'json',
        data: {
            bid: $('.boardOne__bid').text(),
        },
        success: function(response) {
            console.log(response); // 응답 확인용
            if (response.result === 1) {
                console.log('delete OK...');
                window.location.href = "b_findAll.do";
                alert("게시글 삭제가 완료되었습니다.");
            } else {
                alert("게시글 삭제에 실패했습니다. 다시 시도해주시기 바랍니다.");
            }
        },
        error: function(xhr, status, error) {
            console.log('xhr.status:', xhr.status);
        }
    });
}
//댓글 삭제 - 구현중
$(document).ready(function(){
    // 이벤트 델리게이션을 사용하여 cupdatebtn 클릭 이벤트를 처리
    $('.commentAll').on('click', '.cdeletebtn', function() {
    	console.log('cdeletebtn click...');
        // 클릭된 버튼의 부모 요소인 classFor 클래스를 찾음
        let classFor = $(this).closest('.classFor');
        $.ajax({
            url: "comment_deleteOK.do",
            method: 'GET',
            dataType: 'json',
            data: {
                cnum: classFor.find('.comment__cnum').text(),
            },
            success: function(response) {
                console.log(response); // 응답 확인용
                if (response.result === 1) {
                    console.log('comment_deleteOK OK...');
                    window.location.href = window.location.href;
                    alert("댓글 삭제가 완료되었습니다.");
                } else {
                    alert("댓글 삭제에 실패했습니다. 다시 시도해주시기 바랍니다.");
                }
            },
            error: function(xhr, status, error) {
                console.log('xhr.status:', xhr.status);
            }
        });
    });
});
//댓글 등록
function comment_insert() {
    $.ajax({
        url: "comment_insertOK.do",
        method: 'GET',
        dataType: 'json',
        data: {
        	bid: $('.boardOne__bid').text(),
            writer : $('#writer').val(),
            content : $('#content').val(),
            wdate : new Date()
        },
        success: function(response) {
            console.log(response); // 응답 확인용
            if (response.result === 1) {
                console.log('comment_insert OK...');
                window.location.href = window.location.href;
                alert("댓글 작성이 완료되었습니다.");
            } else {
                alert("댓글 작성에 실패했습니다. 다시 시도해주시기 바랍니다.");
            }
        },
        error: function(xhr, status, error) {
            console.log('xhr.status:', xhr.status);
        }
    });
}
//댓글 수정 완료
$(document).ready(function(){
    // 이벤트 델리게이션을 사용하여 cupdatebtn 클릭 이벤트를 처리
    $('.commentAll').on('click', '.cupdateOKbtn', function() {
    	console.log('cupdateOKbtn click...');
        // 클릭된 버튼의 부모 요소인 classFor 클래스를 찾음
        let classFor = $(this).closest('.classFor');
        $.ajax({
            url: "comment_updateOK.do",
            method: 'GET',
            dataType: 'json',
            data: {
                cnum: classFor.find('.comment__cnum').text(),
                content : $('.comment__updateContent').val(),
                wdate : new Date()
            },
            success: function(response) {
                console.log(response); // 응답 확인용
                if (response.result === 1) {
                    console.log('comment_insert OK...');
                    window.location.href = window.location.href;
                    alert("댓글 작성이 완료되었습니다.");
                } else {
                    alert("댓글 작성에 실패했습니다. 다시 시도해주시기 바랍니다.");
                }
            },
            error: function(xhr, status, error) {
                console.log('xhr.status:', xhr.status);
            }
        });
    });
});
//댓글 업데이트 html변경
$(document).ready(function(){
    // 이벤트 델리게이션을 사용하여 cupdatebtn 클릭 이벤트를 처리
    $('.commentAll').on('click', '.cupdatebtn', function() {
        // 클릭된 버튼의 부모 요소인 classFor 클래스를 찾음
        let classFor = $(this).closest('.classFor');
                let update_msg = '';
                update_msg += `
                    <textarea class="comment__updateContent"></textarea>
                `;
                let update_button = '';
                update_button +=`
                	<button class="modifierTab__comment--cupdateOKbtn cupdateOKbtn">수정 완료</button>
                `;
                classFor.find('.comment__content').html(update_msg);
                classFor.find('.modifierTab__comment--update').html(update_button);
            })
        });
</script>
</head>
<body>
	<jsp:include page="../top_menu.jsp"></jsp:include>
	<h1>글 정보</h1>
	<div class="boardOneWrap">
		<div class="boardOne__tabWrap wrap">
			<div class="boardOne__titleTab tab">제목</div>
			<div class="boardOne__contentTab tab">내용</div>
			<div class="boardOne__writerTab tab">작성자</div>
			<div class="boardOne__wdateTab tab">작성일</div>
		</div>
		<div class="boardOne__JSON"></div>
	</div>
	<div class="modifierTab">
		<div class="modifierTab__board--update"></div>
		<div class="modifierTab__board--delete">
			<button class="modifierTab__board--deletebtn deletebtn"
				onclick="b_deleteOK()">게시글 삭제</button>
		</div>
	</div>
	<div class="commentWrap commentAll"></div>
				<!-- 댓글 출력 -->
				<!-- 댓글 출력 -->
				<!-- 댓글 출력 -->
				<!-- 댓글 출력 -->
	<div class="commentWrap">
		<div class="comment__content">
			<textarea id="content"></textarea>
		</div>
		<div class="comment__leftTap">
			<div class="comment__writer">
				<div>작성자</div>
				<textarea id="writer"></textarea>
			</div>
			<button class="modifierTab__comment--insertbtn insertbtn"
				onclick="comment_insert()">댓글 작성</button>
		</div>
	</div>
</body>
</html>