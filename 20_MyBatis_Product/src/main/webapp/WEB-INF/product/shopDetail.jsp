<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>
    
<h2>주문 상세 내역</h2>
<table border="1">
	<tr>
		<td colspan="2">고객명:${loginInfo.name }</td>
		<td colspan="3">송장 번호(주문번호):${param.oid }</td>
	</tr>
	<tr>
		<td colspan="5">배송지:${loginInfo.address1 }&nbsp;${loginInfo.address2 }</td>
	</tr>
	<tr>
		<td>순번</td>
		<td>상품명(상품번호)</td>
		<td>수량</td>
		<td>단가</td>
		<td>금액</td>
	</tr>
	<c:forEach var="k" begin="0" end="${fn:length(olists)-1 }">
		<tr>
			<td>${k+1}</td>
			<td>${olists[k].pname }(${olists[k].pnum })</td>
			<td>${olists[k].qty }</td>
			<td>${olists[k].price }</td>
			<td>${olists[k].price * olists[k].qty }</td>
		</tr>
	</c:forEach>
</table>
    