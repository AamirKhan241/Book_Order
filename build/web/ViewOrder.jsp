<%-- 
    Document   : ViewOrder
    Created on : Mar 18, 2018, 3:36:29 PM
    Author     : aamir
--%>

<%@page import="books.Books_DAO_Implt"%>
<%@page import="books.Books_DAO"%>
<%@page import="profile.Profiles_DAO_Implt"%>
<%@page import="profile.Profiles_DAO"%>
<%@page import="java.util.List"%>
<%@page import="sale.Sale"%>
<%@page import="salesReport.SalesReport"%>
<%@page import="sale.Sale_DAO_Implt"%>
<%@page import="sale.Sale_DAO"%>
<%@page import="salesReport.SalesReport_DAO_Implt"%>
<%@page import="salesReport.SalesReport_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        if(!request.getSession().getAttribute("status").toString().equals("active")){
            request.setAttribute("message", "<script>alert('You have to login first');</script>");
            RequestDispatcher rd=request.getRequestDispatcher("");
            if(request.getParameter("pageReq") == null){
                rd=request.getRequestDispatcher("index.jsp");
            }else{
                rd=request.getRequestDispatcher(request.getParameter("pageReq"));
            }
            rd.forward(request, response);
        }

        SalesReport_DAO srdao=new SalesReport_DAO_Implt();
        Sale_DAO sdao=new Sale_DAO_Implt();
        request.setAttribute("sdao", sdao);
        
        List<SalesReport> sr;
        
        if(request.getParameter("view").toString().equals("mine")){
            sr=srdao.displayC(Integer.valueOf(request.getSession().getAttribute("id").toString()));
        }else{
            sr=srdao.display();
        }
        request.setAttribute("sale", sr);
    %>
    <body id="myPage">
        <c:import url="Header.jsp"></c:import>
        <br>
        
        <table class="table table-responsive table-striped">
            <caption><center><b><i style="font-size: 100px; color: silver; font-family: fantasy">
                <%
                if(request.getParameter("view").toString().equals("mine"))
                    out.print("Orders Placed By You");
                else 
                    out.print("Orders");
                %>
            </i></b></center></caption>    
            <thead>
                <th>Invoice No.</th>
                <th>Date and Time</th>
                <th>Amount</th>
                <th>Description</th>
                <th>Status</th>
                <th>Invoice</th>
                <%
                    if(!request.getParameter("view").toString().equals("mine")){
                %>
                    <th>Order By</th>
                    <th>Update</th>
                <%}%>        
                    
                <th id='cnc' >Cancel Order</th>
            </thead>
            <tbody>
                <c:forEach items="${sale}" var="sr">
                    <tr>
                        <td>${sr.getInvoice()}</td>
                        <td><c:out value="${sdao.getDate(sr.getInvoice())}"/></td>
                        <td>${sr.getAmountToPay()}</td>
                        <td>${sr.getDescription()}</td>
                        <td id='stat'>${sr.getStatus()}</td>
                        <td><a target='_blank' href='Invoice.jsp?Invoice=${sr.getInvoice()}' class='btn btn-default'>Get Invoice</a></td>
                        <%
                            if(!request.getParameter("view").toString().equals("mine")){
                        %>
                            <td>${sr.getcID()}</td>
                            <td><a href='UpdateOrder.jsp?Invoice=${sr.getInvoice()}' class='btn btn-success'>Update</a></td>                        
                        <%}%>
            
                        <td id='cncB' ><a href='CancelOrder?Invoice=${sr.getInvoice()}&add=<%=request.getParameter("view")%>' class='btn btn-danger' >Cancel Order</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        ${error}
        
        <%
            Profiles_DAO pdao=new Profiles_DAO_Implt();
            if(pdao.display(Integer.valueOf(request.getSession().getAttribute("id").toString())).getRole().equals("SUPPLIER")){
                List<SalesReport> list=srdao.displayS(Integer.valueOf(request.getSession().getAttribute("id").toString()));
                request.setAttribute("sales", list);
                %>
                <br><hr/>
                    <table class="table table-responsive table-striped">
                        <caption style="font-size: 30px"><center><b><i>
                            Orders For You
                        </i></b></center></caption>    
                        <thead>
                            <th>Invoice No.</th>
                            <th>Date and Time</th>
                            <th>Amount</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Invoice</th>
                            <th>Buyer Name</th>
                            <th>Update</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${sales}" var="srr">
                                <tr>
                                    <td>${srr.getInvoice()}</td>
                                    <td><c:out value="${sdao.getDate(srr.getInvoice())}"/></td>
                                    <td>${srr.getAmountToPay()}</td>
                                    <td>${srr.getDescription()}</td>
                                    <td id='stat'>${srr.getStatus()}</td>
                                    <td><a target='_blank' href='Invoice.jsp?Invoice=${srr.getInvoice()}' class='btn btn-default'>Get Invoice</a></td>
                                    <td>${srr.getcID()}</td>
                                    <td><a href='UpdateOrder.jsp?Invoice=${srr.getInvoice()}' class='btn btn-success'>Update</a></td>             
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                <%
            }
        %>
<c:import url="Footer.jsp"></c:import>        
    </body>
</html>
