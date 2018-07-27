<%-- 
    Document   : ViewUserQuery
    Created on : Mar 28, 2018, 12:23:31 AM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="userQuery.UserQuery"%>
<%@page import="java.util.List"%>
<%@page import="userQuery.UserQuery_DAO_Implt"%>
<%@page import="userQuery.UserQuery_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            tr th{
                text-align: center;
            }
        </style> 
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

        UserQuery_DAO udao=new UserQuery_DAO_Implt();
        List<UserQuery> list=udao.display();
        request.setAttribute("userquery", list);
    %>
    <body id="myPage">
    <c:import url="Header.jsp"></c:import>
        <br>
        <table class="table table-striped" >
            <caption><center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Profit</i></b></center></caption>
            <thead>
                <tr>
                    <th>Serial No.</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Subject</th>
                    <th>Message</th>
                    <th>Replied Message</th>
                    <th>Reply</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${userquery}" var="p">
                <tr style="text-align: center">
                    <td>${p.getSerial()}</td>
                    <td>${p.getName()} </td>
                    <td>${p.getEmail()} </td>
                    <td>${p.getSubject()} </td>
                    <td>${p.getMessage()}</td>
                    <td>${p.getReply()}</td>
                    <td><a href="ReplyQuery.jsp?email=${p.getEmail()}&sNo=${p.getSerial()}" class="btn btn-default">Reply</a></td>
                    <td><a href="DeleteRequest?sNo=${p.getSerial()}" class="btn btn-danger">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>
