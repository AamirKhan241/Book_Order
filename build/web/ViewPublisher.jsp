<%-- 
    Document   : ViewPublisher
    Created on : Feb 24, 2018, 12:45:49 PM
    Author     : aamir
--%>

<%@page import="supplier.Supplier"%>
<%@page import="supplier.Supplier_DAO_Implt"%>
<%@page import="supplier.Supplier_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="publisher.Publisher_DAO_Implt"%>
<%@page import="publisher.Publisher"%>
<%@page import="publisher.Publisher_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Publishers</title>
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

        Publisher_DAO pdao=new Publisher_DAO_Implt();
        List<Publisher> list=pdao.display();
        
        request.setAttribute("Publisher", list);
        
        Supplier_DAO sdao=new  Supplier_DAO_Implt();
        request.setAttribute("sdao", sdao);
    %>
    <body style=" background-color: aliceblue"  id="myPage">
    <c:import url="Header.jsp"></c:import>
    <br>
    <table class="table table-striped" style="text-align: center">
            <caption><center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Publishers</i></b></center></caption>
            <thead>
                <tr>
                    <th>Publisher Id.</th>
                    <th>Publisher Name</th>
                    <th>Supplier's Id.</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${Publisher}" var="c">
                <tr style="text-align: center">
                    <td>${c.getId()}</td>
                    <td>${c.getName()}</td>
                    <td>
                    <c:forEach items="${sdao.displayP(c.getId())}" var="s">
                        <strong>${s.getSID()}</strong>
                    </c:forEach>

                    </td>
                </tr>
            </c:forEach>
                <%if(!request.getSession().getAttribute("role").equals("BUYER")){%>
                <tr>
                    <td colspan="5"> 
                        <a href="AddPublisher.jsp" class="btn btn-default" style="width: 20%; height:90px;" >
                            <span class="glyphicon glyphicon-plus" style="font-size: 300%"></span><br>
                            <b><i>Add Publisher</i></b>
                        </a>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    <c:import url="Footer.jsp"></c:import>
    </body>
</html>
