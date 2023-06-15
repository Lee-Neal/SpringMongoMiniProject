<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"></script>
<style type="text/css">
table {
	border-collapse: collapse;
	width: 900px;
	margin: 50px 0px 0px 50px;
	font-size: 16px;
}
.table__title{
width:500px;
}
.table__writer, .table__wdate{
width:200px;
}

th, td {
	height:30px;
	border: 1px solid black;
	text-align: cetner;
}
.searchWrap {
	width: 500px;
	margin: 20px 0px 0px 50px;
}

#searchKey, #searchButton {
	width: 100px;
	height: 50px;
	line-height: 50px;
	font-size: 20px;
	text-align: center;
}

#searchWord {
	width: 270px;
	height: 50px;
	line-height: 50px;
	font-size: 20px;
}

.page-LinkWrap {
	text-align: center;
}

.page-link {
	width: 36px;
	height: 36px;
	line-height: 36px;
	border: none;
}

.clicked {
	background-color:#87CEEB;
}

.page-link:hover {
	cursor: pointer;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	  console.log('onload....');

	  function fetchData(pageNumber,searchKey,searchWord) {
	    $.ajax({
	      url: 'board_findAllSearch.do',
	      method: 'GET',
	      dataType: 'json',
	      data: {
	        page: pageNumber, // 페이지 번호를 파라미터로 추가
	        searchKey: $('#searchKey').val(),
	        searchWord: $('#searchWord').val()
	      },
	      success: function(arr) {
	        console.log('ajax...success:', arr);
	        let table_msg = '';
	        for (let i = 0; i < arr.length; i++) {
	          table_msg += `
	        	  <tr>
		            <td onclick="location.href='b_findOne.do?bid=\${arr[i].bid}'" style="cursor:pointer">\${arr[i].title}</td>
		            <td onclick="location.href='b_findOne.do?bid=\${arr[i].bid}'" style="cursor:pointer">\${arr[i].writer}</td>
		            <td onclick="location.href='b_findOne.do?bid=\${arr[i].bid}'" style="cursor:pointer">\${new Date(arr[i].wdate).toLocaleString()}</td>
		         </tr>
	          `;
	        }
	        $('#tbody').html(table_msg);
	      },
	      error: function(xhr, status, error) {
	        console.log('xhr.status:', xhr.status);
	      },
	    });
	  }

	  // 페이지 로딩 시 초기 데이터 가져오기
	  fetchData(1,'','');

	  // 페이지 번호 클릭 시 해당 페이지 데이터 가져오기
	$(function() {
	  let previousPageLink = null;
	
	  $('.page-link').on('click', function(e) {
	    e.preventDefault();
	
	    // 이전에 클릭된 클래스의 이름을 원래대로 되돌리기
	    if (previousPageLink !== null) {
	      previousPageLink.removeClass('clicked');
	    }
	
	    // 클릭된 클래스의 이름 변경
	    let currentPageLink = $(this);
	    currentPageLink.addClass('clicked');
	
	    // 이전에 클릭된 클래스로 설정
	    previousPageLink = currentPageLink;
	
	    let pageNumber = currentPageLink.data('page');
	    fetchData(pageNumber);
	  });
	});
	  $('#searchButton').on('click', function(e) {
		  e.preventDefault();
		  let pageNumber = $('.page-link').data('page');
	      let searchKey = $('#searchKey').val();
	      let searchWord = $('#searchWord').val();
	      console.log('pageNumber..',pageNumber);
	      console.log('searchKey..',searchKey);
	      console.log('searchWord..',searchWord);
		  fetchData(pageNumber, searchKey, searchWord);
		});
	});
</script>
</head>
<body>
	<jsp:include page="../top_menu.jsp"></jsp:include>

	<table>
		<thead>
			<tr>
				<th class="table__title">title</th>
				<th class="table__writer">writer</th>
				<th class="table__wdate">wdate</th>
			</tr>
		</thead>
		<tbody id="tbody">
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6" class="page-LinkWrap">
					<button class="page-link" data-page="1">1</button>
					<button class="page-link" data-page="2">2</button>
					<button class="page-link" data-page="3">3</button>
					<button class="page-link" data-page="4">4</button>
					<button class="page-link" data-page="5">5</button>
				</td>
			</tr>
		</tfoot>
	</table>
	<div class="searchWrap">
		<select id="searchKey">
			<option value="title">title</option>
			<option value="writer">writer</option>
		</select> <input type="text" id="searchWord">
		<button id="searchButton">검색</button>
	</div>
</body>
</html>