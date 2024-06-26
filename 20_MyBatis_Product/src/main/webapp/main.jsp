<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/common/common.jsp" %>

<%
	String viewProduct = request.getContextPath() +"/list.prd";
	String viewOrder = request.getContextPath() +"/order.mall";
	String viewMember = request.getContextPath()+"/memberList.mb";
	String viewBoard = request.getContextPath()+"/list.brd";
%>
    <a href="<%=viewProduct%>">상품 목록 보기</a><br>
    <a href="<%=viewOrder %>">상품 주문 보기</a><br>
    <a href="<%=viewMember %>">회원 목록 보기</a><br>
	<a href="<%=viewBoard %>">게시판 보기</a><br>
    
    