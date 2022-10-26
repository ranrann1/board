<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%
    request.setCharacterEncoding("utf-8");
    %>   
    
<jsp:useBean id="vo" class="board.BoardVo"/>
<jsp:useBean id="dao" class="board.BoardDAO"/>
<jsp:setProperty name="vo" property="*"/>
<%
dao.update(vo);
pageContext.setAttribute("vo", vo);
%>

<c:redirect url="${pageContext.request.contextPath}/boardDetail.jsp?num=${vo.num}"></c:redirect>