<%-- 
    Document   : ViewPurchase
    Created on : Mar 26, 2018, 8:15:38 PM
    Author     : aamir
--%>

<%@page import="purchaseReport.PurchaseReport"%>
<%@page import="java.util.List"%>
<%@page import="purchase.Purchase_DAO_Implt"%>
<%@page import="purchase.Purchase_DAO"%>
<%@page import="purchaseReport.PurchaseReport_DAO_Implt"%>
<%@page import="purchaseReport.PurchaseReport_DAO"%>
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
            if(request.getSession().getAttribute("role").toString().equals("BUYER")){
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

        PurchaseReport_DAO prdao=new PurchaseReport_DAO_Implt();
        Purchase_DAO pdao=new Purchase_DAO_Implt();
        request.setAttribute("pdao", pdao);
        
        List<PurchaseReport> pr;
        
        if(request.getParameter("view").toString().equals("mine")){
            pr=prdao.displayS(Integer.valueOf(request.getSession().getAttribute("id").toString()));
        }else{
            pr=prdao.display();
        } 
        
        request.setAttribute("Purchase", pr); 
    %>
    <body id="myPage">
        <c:import url="Header.jsp"></c:import>
        <br>
        <table class="table table-responsive table-striped">
            <caption><center><b><i style="font-size: 100px; color: silver; font-family: fantasy">
                <%
                if(request.getParameter("view").toString().equals("mine"))
                    out.print("Sale Made By You");
                else 
                    out.print("Purchase");
                %>
            </i></b></center></caption>    
            <thead>
                <th>Purchase Note No.</th>
                <th>Date and Time</th>
                <th>Amount</th>
                <th>Purchase Note</th>
                <%
                    if(!request.getParameter("view").toString().equals("mine")){
                %>
                    <th>Supplier ID</th>
                    <th>Update</th>
                <%}%>        
                    
            </thead>
            <tbody>
                <c:forEach items="${Purchase}" var="pr">
                    <tr>
                        <td>${pr.getPurchase_Note_Id()}</td>
                        <td><c:out value="${pdao.getPur_Date(pr.getPurchase_Note_Id())}"/></td>
                        <td>${pr.getAmount_To_Pay()}</td>
                        <td><a target='_blank' href='PurchaseNote.jsp?pNote=${pr.getPurchase_Note_Id()}' class='btn btn-default'>Get Purchase Note</a></td>
                        <%
                            if(!request.getParameter("view").toString().equals("mine")){
                        %>
                            <td>${pr.getSupplier_Id()}</td>
                            <td><a href='UpdatePurchase.jsp?pNote=${pr.getPurchase_Note_Id()}' class='btn btn-success'>Update</a></td>                        
                        <%}%>                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
                <c:import url="Footer.jsp"></c:import>
        ${error}
    </body>
</html>
