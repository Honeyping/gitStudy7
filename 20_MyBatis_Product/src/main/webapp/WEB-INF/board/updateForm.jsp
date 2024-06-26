<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../common/common.jsp" %>
 
 <style type="text/css">
 	.err{
		font-size : 9pt;
		color : red;
		font-weight: bold;
	}
 </style>   
 
    <form:form commandName="board" action="update.brd" method="post">
		<input type="hidden" name="num" value="${board.num }">
		<input type="hidden" name="pageNum" value="${pageInfo.pageNum }">
		<table border="1" width="500">
			<tr>
				<td align="right" colspan="2">
					<a href="list.brd?whatColumn=${param.whatColumn}&keyword=${param.keyword}&pageNumber=${param.pageNumber}">글목록</a>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="writer" maxlength="10" value="${board.writer }">
					<form:errors path = "writer" class = "err"/>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="subject" value="${board.subject }">
					<form:errors path = "subject" class = "err"/>
				</td>
			</tr>
			<tr>
				<th>email</th>
				<td>
					<input type="text" name="email" value="${board.email }">
					<form:errors path = "email" class = "err"/>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="5" cols="50">${board.content }</textarea>
					<form:errors path = "content" class = "err"/>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="text" name="passwd">
					<form:errors path = "passwd" class = "err"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="num" value="${param.num }">
				    <input type="hidden" name="pageNumber" value="${param.pageNumber}">
				    <input type="hidden" name="whatColumn" value="${param.whatColumn}">
				    <input type="hidden" name="keyword" value="${param.keyword}">
					<input type="submit" value="글수정">
					<input type="button" value="다시작성" onClick="location.reload()">
					<input type="button" value="목록보기" onClick="location.href='list.brd?whatColumn=${param.whatColumn}&keyword=${param.keyword}&pageNumber=${param.pageNumber}'">
				</td>
			</tr>
		</table>
	</form:form>
    