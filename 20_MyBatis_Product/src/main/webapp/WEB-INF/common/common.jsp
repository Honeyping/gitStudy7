<%@page import="member.model.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
접속자 아이디 : ${loginInfo.id }<br>
접속자 아이디 : ${sessionScope.loginInfo.id }<br>
<%-- 접속자 아이디4 : <%= ((MemberBean)session.getAttribute("loginInfo")).getId() %><br> --%>

<a href="main.jsp">시작페이지</a>
<a href="logout.jsp">로그아웃</a><br>

