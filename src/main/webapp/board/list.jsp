<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%
    BoardDAO dao=new BoardDAO();
   List<BoardVo> ls= dao.selectAll();
   pageContext.setAttribute("ls", ls);
    %>
    
    
   <%
    request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
</head>
<body>
<h2>게시글 목록</h2>
<table border="1">

<tr>
<th>번호</th>
<th>제목</th>
<th>작성자</th>
<th>등록일</th>
<th>조회수</th>
</tr>


<c:forEach var = "board" items="${ls}">


<tr>
<th>${board.num }</th>
<th><a href="${pageContext.request.contextPath}/board/boardDetail.jsp?num=${board.num}">${board.title }</th>
<th>${board.writer }</th>
<th>${board.regdate }</th>
<th>${board.cnt }</th>
</tr>


</c:forEach>
</table>
<a href="<c:url value="registForm.jsp"/>"><button>글등록</button></a>
</body>
</html>