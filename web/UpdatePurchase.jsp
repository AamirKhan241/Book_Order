<%-- 
    Document   : UpdatePurchase.jsp
    Created on : Mar 27, 2018, 2:32:35 AM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="purchaseReport.PurchaseReport"%>
<%@page import="purchaseReport.PurchaseReport_DAO_Implt"%>
<%@page import="purchaseReport.PurchaseReport_DAO"%>
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
        PurchaseReport_DAO srdao=new PurchaseReport_DAO_Implt();
        PurchaseReport sr=srdao.display(Integer.valueOf(request.getParameter("pNote").toString()));
    %>
    <body id="myPage">
    <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Updating Purchase Record</i></b></center>
        <hr/>
        <form action="UpdatePurchase?invoice=<%=sr.getPurchase_Note_Id()%>" method="post" style="text-align: center; width: 70%; margin: auto" class="frm">
            
            <label style="float: left">Purchase Note Number:  </label>
            <input type="number" value="<%=sr.getPurchase_Note_Id()%>" class="form form-control" readonly  name='id' required>
            <br>
            <label style="float: left">Amount To Pay:  </label>
            <input type="number" value="<%=sr.getAmount_To_Pay()%>" class=" amt form form-control" readonly required>
            <br>
            <label style="float: left">Amount Paid:  </label>
            <input type="number" value="<%=sr.getAmount_Paid()%>" class="form form-control" required name='amountpaid' id="amt"  required>
            <br>
            <label style="float: left">Balance:  </label>
            <input type="number" value="<%=sr.getBalance()%>" class="form form-control" required name='amountpaid' id="bal" readonly required>
            <br>
            <script>
                $("#amt").keyup(function(){
                    if(document.getElementById("amt").value > <%=sr.getAmount_To_Pay()%>){
                        document.getElementById("bal").value= 0 ;
                        document.getElementById("amt").value= <%= sr.getAmount_To_Pay()%>;
                    }else{
                        document.getElementById("bal").value= <%=sr.getAmount_To_Pay()%> - document.getElementById('amt').value ;
                    }
                });
            </script>
            <br>
            <hr/>
            <input type="submit" value="Update" class="btn btn-success" >
            <br>
        </form>
                    <c:import url="Footer.jsp"></c:import>
    </body>
</html>
