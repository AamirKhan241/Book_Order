<%-- 
    Document   : UpdateOrder
    Created on : Mar 21, 2018, 10:49:33 PM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="salesReport.SalesReport"%>
<%@page import="salesReport.SalesReport_DAO_Implt"%>
<%@page import="salesReport.SalesReport_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #del{
                display: 'none';
            }
        </style>
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
        SalesReport_DAO srdao=new SalesReport_DAO_Implt();
        SalesReport sr=srdao.display(Integer.valueOf(request.getParameter("Invoice").toString()));
        if(sr.getStatus().equals("Delivered")){
            request.setAttribute("error", "<script>alert('You cannot update it. It is already delivered.');</script>");
            RequestDispatcher rd=request.getRequestDispatcher("ViewOrder.jsp?view=all");
            rd.forward(request, response);
        }
    %>
    <body id="myPage">
        <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Update Order</i></b></center>               
        <hr/>
        <form action="UpdateOrder?invoice=<%=sr.getInvoice()%>" method="post" style="text-align: center; width: 70%; margin: auto" class="frm">
            
            <label style="float: left">Invoice Number:  </label>
            <input type="number" value="<%=sr.getInvoice()%>" class="form form-control" readonly required>
            <br>
            <label style="float: left">Amount To Pay:  </label>
            <input type="number" value="<%=sr.getAmountToPay()%>" class=" amt form form-control" readonly required>
            <br>
            <label style="float: left">Amount Paid:  </label>
            <input type="number" value="<%=sr.getAmountPaid()%>" class="form form-control" required name='amountpaid' id='amt' required>
            <br>
            <label style="float: left">Balance:  </label>
            <input type="number" value="<%=sr.getBalance()%>" class="form form-control" required name='balance' id='bal' readonly required>
            <br>
            <script>
                $("#amt").keyup(function(){
                    if(document.getElementById("amt").value > <%=sr.getAmountToPay()%>){
                        document.getElementById("bal").value= 0 ;
                        document.getElementById("amt").value= <%= sr.getAmountToPay()%>;
                    }else{
                        document.getElementById("bal").value= <%=sr.getAmountToPay()%> - document.getElementById('amt').value ;
                    }
                    
                    if(document.getElementById("bal").value === 0){
                        document.getElementById("del").style.display="block";
                    }
                });
            </script>
            <label style="float: left">Status:  </label>
            <select name="status" class="form form-control" required>
                <option selected><%=sr.getStatus()%></option>
                <option>Order Placed</option>
                <option>Dispatched</option>
                <option id="del">Delivered</option>
            </select>
            <br>
            <hr/>
            <input type="submit" value="Update" class="btn btn-success" >
            <br>
        </form>
                <c:import url="Footer.jsp"></c:import>
    </body>
</html>
