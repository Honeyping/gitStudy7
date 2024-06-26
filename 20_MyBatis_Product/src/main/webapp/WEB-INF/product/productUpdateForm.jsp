<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
productUpdateForm.jsp<br>
<%@include file = "../common/common.jsp" %>
<style type="text/css">
	.err{
		font-size : 9pt;
		color : red;
		font-weight: bold;
	}
	
	table {
		border : 1px solid;
		border-collapse: collapse;
		margin : auto;
		width : 80%;
		height : 60%;
	}
	
	th{
		background-color: pink;
	}
	
	th, td, tr{
		border : 1px solid;
	}
	
	h2{
		text-align: center;
	}
</style>

<h2>상품 수정 화면</h2>
<form:form commandName = "product" action = "update.prd" method = "post" enctype = "multipart/form-data">
	<input type="hidden" name = "num" value = "${product.num }">
	<input type="hidden" name="pageNumber" value="${param.pageNumber}">
   	<input type="hidden" name="whatColumn" value="${param.whatColumn}">
   	<input type="hidden" name="keyword" value="${param.keyword}">
		<table>
		<tr>
			<th>* 상품명</th> 
		 	<td>
				 <input type = "text" name = "name" value = "${product.name }">
				<form:errors path = "name" class = "err"/>
			</td>
		</tr>
	
		<tr>
			<th>제조회사 </th>
			<td>
			<input type = "text" name = "company" value = "${product.company }">
			</td>
		</tr>
	
	<tr>
		<th>* 가격 </th>
		<td>
			 <input type = "text" name = "price" value = "${product.price }">
			<form:errors path = "price" class = "err"/>
		</td>
	</tr>
	
	<tr>
		<th>재고 수량</th> 
		<td>
			<input type = "text" name = "stock" value = "${product.stock }">
		</td>
	</tr>
<tr>
		<th>적립 포인트</th>
		<td>
			<input type = "text" name = "point" value = "${product.point }">
		</td>
	</tr>
	
	<tr>
		<th>* 설명</th>
		<td>
			<input type = "text" name = "contents" value = "${product.contents }">
			<form:errors path = "contents" class = "err"/>
		</td>
	</tr>
	
	<tr>
		<th>* 상품 이미지</th>
		<td> 
			<img src = "<%=request.getContextPath() + "/resources/uploadImage/" %>${product.image}" width = "100" height = "100"/> <br><br>
			<input type = "file" name = "upload" value = "${product.image }"><br><br> <!-- upload:새로 업로드할 파일명 -->
			<input type = "text" name="upload2" value = "${product.image }"> <!-- upload2:삭제할 파일명 -->
			<form:errors path = "image" class = "err"/>
		</td>
	</tr>
	
	<tr style = "text-align : center">
		<td colspan="2">
			<input type = "submit" value = "수정하기">
			<input type = "button" value = "목록보기" onClick = "location.href='list.prd?pageNumber=${param.pageNumber}&whatColumn=${param.whatColumn }&keyword=${keyword }'">
		</td>
	</tr>
	</table>
</form:form>
