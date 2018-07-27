<%-- 
    Document   : PurchaseNote
    Created on : Mar 26, 2018, 11:14:10 PM
    Author     : aamir
--%>

<%@page import="books.Books"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="books.Books_DAO_Implt"%>
<%@page import="books.Books_DAO"%>
<%@page import="purchase.Purchase"%>
<%@page import="java.util.List"%>
<%@page import="purchase.Purchase_DAO_Implt"%>
<%@page import="purchase.Purchase_DAO"%>
<%@page import="profile.Profiles"%>
<%@page import="profile.Profiles_DAO_Implt"%>
<%@page import="profile.Profiles_DAO"%>
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
        PurchaseReport_DAO prdao=new PurchaseReport_DAO_Implt();
        PurchaseReport pr=prdao.display(Integer.valueOf(request.getParameter("pNote")));
        
        Profiles_DAO pdao=new Profiles_DAO_Implt();
        Profiles p=pdao.display(pr.getSupplier_Id());
        
        Purchase_DAO ppdao=new Purchase_DAO_Implt();
        Purchase pur;
        pur=ppdao.display(pr.getPurchase_Note_Id());
        Books_DAO bdao=new Books_DAO_Implt();
        List<Books> b=bdao.display(pr.getBook_ISBN());
        request.setAttribute("books", b);
    %>
    <body id="myPage">
    <c:import url="Header.jsp"></c:import>
    <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Purchase Note</i></b></center>               
        <div class="container">
            <div class="row">
                <div class="col-xs-12">                    
                    <div class="text-center">
                        <h1 style="font-family: cursive; "><strong><i>BooksVilla</i></strong></h1>
                        <hr/>
                        <h3 style="float: left">Purchase Note Number:<strong> <%=pr.getPurchase_Note_Id()%></strong></h3>
                        <h3 style="float: right">Order Date: <strong><%= pur.getPur_Date()%> </strong></h3>
                        <br>
                        <br>
                        <hr/>
                        
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-5 col-md-3 col-lg-5 pull-left">
                            <div class="panel panel-default height">
                                <div class="panel-heading">Supplier Details: </div>
                                <div class="panel-body">
                                    <strong><i><%=p.getFirst_Name()+" "+p.getLast_Name()%></i></strong><br>
                                    <strong>Address: </strong><%=p.getAddress()%><br>
                                    <strong>Contact No: </strong><%=p.getContact()%><br>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="text-center"><strong>Order summary</strong></h3>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-condensed">
                                    <thead>
                                        <tr>
                                            <td><strong>Book ISBN</strong></td>
                                            <td><strong>Book Name</strong></td>
                                            <td><strong>Author</strong></td>
                                            <td><strong>Publisher</strong></td>
                                            <td class="text-center"><strong>Unit Price</strong></td>
                                            <td class="text-center"><strong>Quantity</strong></td>
                                            <td class="text-right"><strong>Total</strong></td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <c:forEach items="${books}" var="b">
                                                <td>${b.getISBN()}</td>
                                                <td>${b.getName()}</td>
                                                <td>${b.getAuthor()}</td>
                                                <td>${b.getPublisher()}</td>
                                                <td>${b.getPrice()}</td>
                                                <td class="text-center"><%=pr.getCopies()%></td>
                                                <td class="text-right"><%=pur.getAmount()%></td>
                                            </c:forEach>
                                        </tr>
                                        <tr>
                                            <td class="highrow" colspan="5"></td>
                                            <td class="highrow text-center"><strong>Subtotal</strong></td>
                                            <td class="highrow text-right"><%=pr.getAmount_To_Pay()%></td>
                                        </tr>
                                        <tr>
                                            <td class="emptyrow" colspan="5"></td>
                                            <td class="emptyrow text-center"><strong>Amount Paid</strong></td>
                                            <td class="emptyrow text-right"><%=pr.getAmount_Paid()%></td>
                                        </tr>
                                        <tr>
                                            <td class="emptyrow" colspan="5"></td>
                                            <td class="emptyrow text-center"><strong>Balance Amount</strong></td>
                                            <td class="emptyrow text-right"><%=pr.getBalance()%></td>
                                        </tr>
                                        <tr style="font-size: 20px">
                                            <td class="highrow" colspan="3"></td>
                                            <td class="highrow text-center" colspan="3"><strong>Final Amount</strong></td>
                                            <td class="highrow text-right"><strong> &#8377; <%=pr.getBalance()%></strong></td>
                                        </tr>
                                        <tr>
                                            <td class="highrow" colspan="1">In Words:</td>
                                            <td class="highrow text-left" style="font-size: 20px" colspan="6"><strong>&#8377; <%= singleCopy.NumberConvert.getInstance().Convert(Integer.valueOf(pr.getBalance()))%></strong></td>
                                        </tr>
                                        <hr/>
                                        <tr>
                                            <td class=" text-center" colspan="7"><pre>From  <strong style="font-size: 20px"><i>Aamir Khan</i></strong> 
(CEO: <i>BOOKSVILLA</i>)
                                                </pre>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <center>
                <input type="button" onclick="window.print()" value="Print" class="btn btn-default">
            </center>
        </div>

        <style>
        .height {
            min-height: 100px;
        }

        .table > tbody > tr > .emptyrow {
            border-top: none;
        }

        .table > thead > tr > .emptyrow {
            border-bottom: none;
        }

        .table > tbody > tr > .highrow {
            border-top: 3px solid;
        }
        </style>
<c:import url="Footer.jsp"></c:import>        
    </body>
</html>
