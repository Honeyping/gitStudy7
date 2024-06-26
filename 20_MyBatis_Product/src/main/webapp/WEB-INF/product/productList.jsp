<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>   
<script type="text/javascript">
	function insert(){
		location.href="insert.prd";
	}
	function goUpdate(num, whatColumn, keyword, pageNumber) {
		//alert(1);
		location.href="update.prd?num=" + num + "&whatColumn=" + whatColumn + "&keyword=" + keyword + "&pageNumber=" + pageNumber;
	}
</script> 
productList.jsp<br><br>

<h1 align="center">상품 리스트 화면(${pageInfo.totalCount })</h1>
	<form action="list.prd" align="center">
		<select name="whatColumn">
			<option value="all">전체검색 
			<option value="name">상품명
			<option value="contents">설명
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>
	<table border="1" align="center" width="60%">
		<tr>
			<td colspan="6" align="right">
				<input type="button" value="추가하기" onClick="insert()">
			</td>
		</tr>
		<tr>
			<th>상품번호</th>
			<th>상품명</th>
			<th>설명</th>
			<th>가격</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		
		<c:forEach var="prd" items="${productLists }">
			<tr>
				<td>${prd.num }</td>
				<td>
					<a href="detail.prd?num=${prd.num }&pageNumber=${pageInfo.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}">${prd.name }</a>
				</td>
				<td>${prd.contents }</td>
				<td>${prd.price }</td>
				<td>
					<input type="button" value="수정" onclick="goUpdate('${prd.num}', '${param.whatColumn}', '${param.keyword}', '${pageInfo.pageNumber}')">
				</td>
				<td><a href="delete.prd?num=${prd.num }&pageNumber=${pageInfo.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<center>${pageInfo.pagingHtml }</center>
	