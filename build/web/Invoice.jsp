<%-- 
    Document   : Invoice
    Created on : Mar 19, 2018, 1:11:54 PM
    Author     : aamir
--%>

<%@page import="books.Books_DAO_Implt"%>
<%@page import="books.Books_DAO"%>
<%@page import="profile.Profiles"%>
<%@page import="profile.Profiles_DAO_Implt"%>
<%@page import="profile.Profiles_DAO"%>
<%@page import="java.util.List"%>
<%@page import="sale.Sale"%>
<%@page import="sale.Sale_DAO_Implt"%>
<%@page import="sale.Sale_DAO"%>
<%@page import="salesReport.SalesReport"%>
<%@page import="salesReport.SalesReport_DAO_Implt"%>
<%@page import="salesReport.SalesReport_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            tr td{
                text-align: center;
            }
        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
        SalesReport sr=srdao.display(Integer.valueOf(request.getParameter("Invoice")));
        
        
        Profiles_DAO pdao=new Profiles_DAO_Implt();
        Profiles p=pdao.display(sr.getcID());
        
        Sale_DAO sdao=new Sale_DAO_Implt();
        List<Sale> list=sdao.displayI(sr.getInvoice());
        request.setAttribute("Sale", list);
        Books_DAO bdao=new Books_DAO_Implt();
        request.setAttribute("bdao", bdao);
    %>
    <body id="myPage">
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Invoice</i></b></center>               
        <div class="container">
            <div class="row">
                <div class="col-xs-12">                    
                    <div class="text-center">
                        <h1 style="font-family: cursive; "><strong><i>BooksVilla</i></strong></h1>
                        <hr/>
                        <h3 style="float: left">Invoice Number:<strong> <%=sr.getInvoice()%></strong></h3>
                    <h3 style="float: right">Order Date: <strong><%= sdao.getDate(sr.getInvoice())%> </strong></h3>
                        <br>
                        <br>
                        <hr/>
                        
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-5 col-md-3 col-lg-5 pull-left">
                            <div class="panel panel-default height">
                                <div class="panel-heading">Order By</div>
                                <div class="panel-body">
                                    <strong><i><%=p.getFirst_Name()+" "+p.getLast_Name()%></i></strong><br>
                                    <strong>Address: </strong><%=p.getAddress()%><br>
                                    <strong>Contact No: </strong><%=p.getContact()%><br>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-5 col-md-3 col-lg-5 pull-right">
                            <div class="panel panel-default height">
                                <div class="panel-heading">Shipping Address</div>
                                <div class="panel-body">
                                    <strong>Name: <i><%=sr.getrName()%></i></strong><br>
                                    <strong>Address: </strong><%=sr.getrAddress()%><br>
                                    <strong>Contact No: </strong><%=sr.getrContact()%><br>
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
                                        <c:forEach items="${Sale}" var="s">
                                            <tr>
                                                <c:forEach items="${bdao.display(s.getISBN())}" var="b">
                                                    <td>${b.getISBN()}</td>
                                                    <td>${b.getName()}</td>
                                                    <td>${b.getAuthor()}</td>
                                                    <td>${b.getPublisher()}</td>
                                                    <td>${b.getPrice()}</td>
                                                    <td class="text-center">${s.getCopies()}</td>
                                                    <td class="text-right">${s.getAmount()}</td>
                                                </c:forEach>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td class="highrow" colspan="5"></td>
                                            <td class="highrow text-center"><strong>Subtotal</strong></td>
                                            <td class="highrow text-right"><%=sr.getAmountToPay()%></td>
                                        </tr>
                                        <tr>
                                            <td class="emptyrow" colspan="5"></td>
                                            <td class="emptyrow text-center"><strong>Amount Paid</strong></td>
                                            <td class="emptyrow text-right"><%=sr.getAmountPaid()%></td>
                                        </tr>
                                        <tr>
                                            <td class="emptyrow" colspan="5"></td>
                                            <td class="emptyrow text-center"><strong>Balance Amount</strong></td>
                                            <td class="emptyrow text-right"><%=sr.getBalance()%></td>
                                        </tr>
                                        <tr style="font-size: 20px">
                                            <td class="highrow" colspan="3"></td>
                                            <td class="highrow text-center" colspan="3"><strong>Final Amount</strong></td>
                                            <td class="highrow text-right"><strong> &#8377; <%=sr.getBalance()%></strong></td>
                                        </tr>
                                        <tr>
                                            <td class="highrow" colspan="1">In Words:</td>
                                            <td class="highrow text-left" style="font-size: 20px" colspan="6"><strong>&#8377; 
                                                    <% int amt=0;
                                                    try{
                                                        
                                                        amt=Integer.valueOf(sr.getBalance().substring(0,sr.getBalance().indexOf(".")));
                                                        }catch(Exception e){
                                                        amt=Integer.valueOf(sr.getBalance());}%>
                                                    <%= singleCopy.NumberConvert.getInstance().Convert(amt)%>
                                                </strong></td>
                                        </tr>
                                        <hr/>
                                        <tr>
                                            <td class=" text-center" colspan="7"><pre>From  <strong style="font-size: 20px"><i>Aamir Khan</i></strong> (<i>BOOKSVILLA.COM</i>)
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
    </body>
</html>
