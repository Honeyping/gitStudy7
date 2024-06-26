<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>

<table border="1">
	<tr>
		<td align="center" colspan="3">${loginInfo.name}&nbsp;${loginInfo.id }</td>
	</tr>
	<tr>
		<td>주문 번호</td>
		<td>주문 일자</td>
		<td>상세 보기</td>
	</tr>
	<c:forEach var="i" items="${orderLists }">
		<tr>
			<td>${i.oid }</td>
			<td>${i.orderdate }</td>
			<td><a href="detailView.mall?oid=${i.oid }">상세보기</a></td>
		</tr>
	</c:forEach>
</table>
