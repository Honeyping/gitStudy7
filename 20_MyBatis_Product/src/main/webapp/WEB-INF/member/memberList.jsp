<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
memberList.jsp
<br>
<script type="text/javascript">

	function insert(){
		location.href="insert.mb";
	}
	
	function goUpdate(id, pageNumber, whatColumn, keyword){
	      location.href = "update.mb?id=" + id + "&pageNumber=" + pageNumber + "&whatColumn=" + whatColumn + "&keyword=" + keyword  ;
	}
	
</script>

<center>
	<h2>회원 리스트 화면(${pageInfo.totalCount})</h2>
	<form action="memberList.mb" method="get">
		<select name="whatColumn">
			<option value="all">전체 검색
			<option value="gender">성별
			<option value="hobby">취미
			<option value="name">이름
		</select> <input type="text" name="keyword" value=""> <input
			type="submit" value="검색">
	</form>
</center>

<table border="1" align="center" width="50%" height="30%">
	<tr>
		<th colspan="9"><input type="button" value="추가하기"
			onClick="insert()"></th>
	</tr>
	<tr align="center">
		<th>ID</th>
		<th>이름</th>
		<th>비번</th>
		<th>성별</th>
		<th>취미</th>
		<th>주소</th>
		<th>포인트</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
	<c:forEach var="member" items="${memberLists}">
		<tr align="center">
			<td>${member.id }</td>
			<td><a
				href="detail.mb?id=${member.id}&pageNumber=${pageInfo.pageNumber}&whatColumn=${pageInfo.whatColumn}&keyword=${pageInfo.keyword}">
					${member.name} </a></td>
			<td>${member.password}</td>
			<td>${member.gender}</td>
			<td>${member.hobby}</td>
			<td>${member.address1}${member.address2}</td>
			<td>${member.mpoint}</td>
			<td><a
				href="delete.mb?id=${member.id}&pageNumber=${pageInfo.pageNumber}&whatColumn=${pageInfo.whatColumn}&keyword=${pageInfo.keyword}">
					삭제 </a></td>
			<td><input type="button" value="수정"
				onClick="goUpdate('${member.id}','${pageInfo.pageNumber}','${pageInfo.whatColumn}','${pageInfo.keyword}')">
			</td>
		</tr>
	</c:forEach>
</table>

<p align="center">${pageInfo.pagingHtml }</p>
