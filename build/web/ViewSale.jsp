<%-- 
    Document   : ViewSale
    Created on : Mar 30, 2018, 1:31:13 AM
    Author     : aamir
--%>

<%@page import="sale.Sale"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="salesReport.SalesReport"%>
<%@page import="java.util.List"%>
<%@page import="sale.Sale_DAO_Implt"%>
<%@page import="salesReport.SalesReport_DAO_Implt"%>
<%@page import="salesReport.SalesReport_DAO"%>
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

        Sale_DAO_Implt sdao=new Sale_DAO_Implt();
        
        List<Sale> s=sdao.display();
        request.setAttribute("Sale", s); 
    %>
    <body id="myPage">
    <c:import url="Header.jsp"></c:import>
        <br>
        <table class="table table-responsive table-striped">
            <caption><center><b><i style="font-size: 100px; color: silver; font-family: fantasy">
                Sale
            </i></b></center></caption>    
            <thead>
                <th>Sales ID.</th>
                <th>Date and Time</th>
                <th>Amount</th>
                <th>Invoice</th>
                <th>Customer ID</th>
                <th>Supplier ID</th>
                
                    
            </thead>
            <tbody>
                <c:forEach items="${Sale}" var="sr">
                    <tr>
                        <td>${sr.getSale_id()}</td>
                        <td>${sr.getDate()}</td>
                        <td>${sr.getAmount()}</td>
                        <td><a target='_blank' href='Invoice.jsp?Invoice=${sr.getInvoice()}' class='btn btn-default'>Get Invoice</a></td>
                        <td>${sr.getCustomer_id()}</td>
                        <td>${sr.getSupplier_id()}</td>                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
                <c:import url="Footer.jsp"></c:import>
        ${error}
</html>
