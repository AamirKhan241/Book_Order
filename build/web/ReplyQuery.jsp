<%-- 
    Document   : ReplyQuery
    Created on : Mar 28, 2018, 12:50:04 AM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        if(request.getSession().getAttribute("status").toString().equals("active")){
            if(!request.getSession().getAttribute("role").toString().equals("ADMINISTRATOR")){
                response.sendRedirect("Unauthorised.jsp");
            }
        }else{
            request.setAttribute("message", "<script>alert('You have to login first');</script>");
            RequestDispatcher rd=request.getRequestDispatcher("");
            if(request.getParameter("pageReq") == null){
                rd=request.getRequestDispatcher("index.jsp");
            }else{
                rd=request.getRequestDispatcher(request.getParameter("pageReq"));
            }
            rd.forward(request, response);
        }
    %>
    <body id="myPage">
    <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Reply</i></b></center>
    <form action="Reply?sNo=<%=request.getParameter("sNo")%>" method="post" style="width: 70%; text-align: center; margin: auto">
            <label style="float: left">Reply To:</label>
            <input type="Remail" value="<%=request.getParameter("email")%>" readonly class="form form-control" name="Remail">
            <br>
            <label style="float: left">Reply Message:</label>
            <textarea class="form form-control" name="RMessage" required pattern=".{3,500}" title="length should be between 3-500."></textarea>
            <br>
            <input type="submit" style="width:20%" value="Reply" class="btn btn-success">
        </form>
            <c:import url="Footer.jsp"></c:import>
    </body>
</html>
