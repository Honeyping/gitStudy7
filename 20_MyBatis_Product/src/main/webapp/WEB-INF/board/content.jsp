<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<b>글내용 보기</b>

<table border="1" width="600">
  <tr align="center" height="30">
    <td width="150">글번호</td>
    <td width="150">${board.num }</td>
    <td width="150">조회수</td>
    <td width="150">${board.readcount }</td>
  </tr>
  <tr>
    <td width="150">작성자</td>
    <td width="150">${board.writer }</td>
    <td width="150">작성일</td>
    <td width="150">${board.reg_date }</td>
  </tr>
  <tr>
	<td width="150">글제목</td>
	<td width="150" colspan="4">${board.subject }</td>
  </tr>
  <tr>
  	<td width="150">글내용</td>
	<td width="150" colspan="4">${board.content }</td>
  </tr>
  <tr align="center" height="30">
  	<td colspan="4">
	  	<input type="button" value="글수정" onClick="location.href='update.brd?num=${board.num }&whatColumn=${param.whatColumn}&keyword=${param.keyword}&pageNumber=${param.pageNumber}'">
	  	<input type="button" value="글삭제" onClick="location.href='delete.brd?num=${board.num }&whatColumn=${param.whatColumn}&keyword=${param.keyword}&pageNumber=${param.pageNumber}'">
	  	<input type="button" value="답글쓰기" onClick="location.href='reply.brd?ref=${board.ref }&re_step=${board.re_step }&re_level=${board.re_level }&whatColumn=${param.whatColumn}&keyword=${param.keyword}&pageNumber=${param.pageNumber}'">
	  	<input type="button" value="글목록" onClick="location.href='list.brd?whatColumn=${param.whatColumn}&keyword=${param.keyword}&pageNumber=${param.pageNumber}'">
	</td>
  </tr>
</table>

    
