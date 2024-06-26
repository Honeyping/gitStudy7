<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
(board)list.jsp<br><br>

<h1 align="center">게시판 목록보기</h1>
	<form action="list.brd" align="center">
		<select name="whatColumn">
			<option value="all">전체검색 
			<option value="subject">제목
			<option value="writer">작성자
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>
	<table border="1" align="center">
		<tr>
			<td colspan="6" align="right">
				<input type="button" value="추가하기" onClick="location.href='write.brd?whatColumn=${param.whatColumn}&keyword=${param.keyword}&pageNumber=${param.pageNumber}'">
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
			<th>IP</th>
		</tr>
		<c:choose>
			<c:when test="${pageInfo.totalCount == 0}">
	        <table align="center">
	            <tr>
	                <td>
	                    게시판에 저장된 글이 없습니다.
	                </td>
	            </tr>
	        </table>
	   		</c:when>
	   		<c:otherwise>
			<c:forEach var="article" items="${boardLists}">
				<tr align="center">
	                   <td>${article.num}</td>
	                   <td align="left">
	                   		<c:if test="${article.re_level > 0}">
	                            <img src="images/level.gif" width="${20 * article.re_level}" height="15">
	                            <img src="images/re.gif">
	                        </c:if>
	                        <a href="content.brd?num=${article.num}&pageNumber=${pageInfo.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}">${article.subject}</a>
	                    </td>
	                    <td>${article.writer}</td>
	                    <td><fmt:formatDate value="${article.reg_date}" pattern="yyyy-MM-dd HH:mm"/></td>
	                    <td>${article.readcount}</td>
	                    <td>${article.ip}</td>
	                </tr>
			</c:forEach>
		</c:otherwise>
		</c:choose>
		</table>
	<br><br>
	<center>${pageInfo.pagingHtml }</center>

<c:if test="${count > 0}">
    <%
        int pageCount = (Integer) request.getAttribute("count") / (Integer) request.getAttribute("pageSize") + 
                        ((Integer) request.getAttribute("count") % (Integer) request.getAttribute("pageSize") == 0 ? 0 : 1);
        int pageBlock = 10;
        int startPage = ((Integer) request.getAttribute("currentPage") - 1) / pageBlock * pageBlock + 1;
        int endPage = startPage + pageBlock - 1;
        if (endPage > pageCount) {
            endPage = pageCount;
        }
    %>
    <div>
        <c:if test="${startPage > 10}">
            <a href="list.jsp?pageNum=${startPage - 10}">[이전]</a>
        </c:if>
        <c:forEach begin="${startPage}" end="${endPage}" var="i">
            <a href="list.jsp?pageNum=${i}">[${i}]</a>
        </c:forEach>
        <c:if test="${endPage < pageCount}">
            <a href="list.jsp?pageNum=${startPage + 10}">[다음]</a>
        </c:if>
    </div>
</c:if>