<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>
mallList.jsp<br>

num: ${param.num }<br>
pageNumber: ${param.pageNumber}<br>
orderqty: ${param.orderqty}<br>

<h2 align="center">주문 내역</h2>
<table border="1" align="center">
	<tr>
		<td colspan="5">주문자 정보 : ${loginInfo.name }(${loginInfo.id })</td>
	</tr>
	<tr>
		<td>상품 번호</td>
		<td>상품명</td>
		<td>주문 수량</td>
		<td>단가</td>
		<td>금액</td>
	</tr>
	<c:forEach var="abc" items="${shoplists }">
		<tr>
			<td>${abc.pnum }</td>
			<td>${abc.pname }</td>
			<td>${abc.qty }</td>
			<td><fmt:formatNumber value="${abc.price }" type="number" pattern="#,###"/></td>
			<td><fmt:formatNumber value="${abc.amount }" type="number" pattern="#,###"/></td>
		</tr>
		<c:set var="total" value="${total + abc.amount}"/>
	</c:forEach>
	<tr>
		<td colspan="3">
			<a href="calculate.mall">결제하기</a>&nbsp;&nbsp;
			<a href="list.prd">추가 주문</a>
		</td>
		<td colspan="2">
			<c:out value="총 금액 : ${total }"></c:out>
		</td>
	</tr>
</table>

