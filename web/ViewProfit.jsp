<%-- 
    Document   : ViewProfit
    Created on : Mar 27, 2018, 1:51:35 AM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="profit.Profit"%>
<%@page import="java.util.List"%>
<%@page import="profit.Profit_DAO_Implet"%>
<%@page import="profit.Profit_DAO"%>
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

        Profit_DAO pdao=new Profit_DAO_Implet();
        List<Profit> list=pdao.display();
        request.setAttribute("profit", list);
    %>
    <body id="myPage">
    <c:import url="Header.jsp"></c:import>
        <br>
        <table class="table table-striped" >
            <caption><center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Profit</i></b></center></caption>
            <thead>
                <tr>
                    <th>Transaction No.</th>
                    <th>Buyer ID</th>
                    <th>Seller ID</th>
                    <th>Profit Amount</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${profit}" var="p">
                        <tr style="text-align: center">
                            <td>${p.getTransaction_no()}</td>
                            <td>${p.getSaleBY()} </td>
                            <td>${p.getSaleFROM()} </td>
                            <td>${p.getAmount()}</td>
                            <td>${p.getDate()}</td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>
