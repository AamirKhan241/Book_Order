<%-- 
    Document   : ViewSupplier
    Created on : Feb 21, 2018, 11:33:33 AM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="supplier.Supplier"%>
<%@page import="supplier.Supplier_DAO_Implt"%>
<%@page import="supplier.Supplier_DAO"%>
<%@page import="profile.Profiles"%>
<%@page import="java.util.List"%>
<%@page import="profile.Profiles_DAO_Implt"%>
<%@page import="profile.Profiles_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suppliers</title>
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

        Supplier_DAO sdao=new Supplier_DAO_Implt();
        List<Supplier> Slist=sdao.display();
        request.setAttribute("Suppliers", Slist);
        
        Profiles_DAO pdao=new Profiles_DAO_Implt();        
        request.setAttribute("pdao", pdao);
    %>
    <body style=" background-color: aliceblue" id="myPage">
    <c:import url="Header.jsp"></c:import>
    <br>
        <table class="table table-striped" >
            <caption><center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Suppliers</i></b></center></caption>
            <thead>
                <tr>
                    <th>Serial No.</th>
                    <th>Supplier ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Contact</th>
                    <th>Publisher</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${Suppliers}" var="s">
                    <c:forEach  items="${ pdao.displays( s.getSID() ) }" var="p">
                    <tr style="text-align: center">
                        <td>${s.getSNo()}</td>
                        <td>${s.getSID()}</td>
                        <td>${p.getFirst_Name()} ${p.getLast_Name()}</td>
                        <td>${s.getEmail()}</td>
                        <td>${p.getContact()}</td>
                        <td>${s.getPublisher()}</td>
                        <td><input type="text" class="btn btn-default" value="${p.getStatus()}" style="width: 50%"></td>
                    </tr>
                    </c:forEach>
                </c:forEach>
                    
            </tbody>
        </table>
    <c:import url="Footer.jsp"></c:import>
    </body>
</html>
