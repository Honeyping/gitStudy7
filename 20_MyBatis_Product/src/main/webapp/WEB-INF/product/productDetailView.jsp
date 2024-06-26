<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
productDetailView.jsp<br>

<style>
   table {
      font-family: sans-serif;
      border-collapse: collapse;
      border-width: 2px;
   }
   th {
      background-color: #A6E3E9;
   }
   td {
      padding-left: 4px;
   }
   a {
      color: #00ADB5;
   }
   a:hover{
      color: #393E46;
   }
</style>

<center>
list.jsp => 제목클릭(4가지 detail.prd)=>ProductDetailController(설정)=>detail.jsp<br>

whatColum1 : ${param.whatColumn}<br>
whatColum2 : ${whatColumn}<br>
pageNumber1 : ${param.pageNumber }<br>
pageNumber2 : ${pageNumber }<br>

<h2>상품 상세 화면(<%=request.getContextPath()%>/ ${product.image})</h2> <!-- /ex : C:\Spring_test3\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\20_MyBatis_Product -->
<table border=1 width="600" height="300">
   <tr>
      <td rowspan=6 align="center">
         <img src="<%=request.getContextPath()+"/resources/uploadImage/"%>${product.image}" width="100" height="100">
      </td>
      <th>상품명</th>
      <td>${product.name}</td>
   </tr>
   <tr>
      <th>가격</th>
      <td>${product.price}원</td>
   </tr>
   <tr>
      <th>재고 수량</th>
      <td>${product.stock}</td>
   </tr>
   <tr>
      <th>설명</th>
      <td>${product.contents}</td>
   </tr>
   <tr>
      <th>주문 수량</th>
      <td>
      <form action="add.mall" method="post">
      <input type="hidden" name="num" value="${product.num }">
      <input type="hidden" name="pageNumber" value="${pageNumber }">
         <input type="text" name="orderqty" style="height:30px">
         <input type="submit" value="주문" style="width:70px">
         </form>
      </td>
   </tr>
   <tr align="center">
      <td colspan=2><a href="list.prd?whatColumn=${param.whatColumn}&keyword=${param.keyword}&pageNumber=${param.pageNumber}">상품 리스트</a></td>
   </tr>
</table>
</center>