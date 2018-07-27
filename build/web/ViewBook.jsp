<%-- 
    Document   : ViewBook
    Created on : Feb 22, 2018, 11:15:21 PM
    Author     : aamir
--%>

<%@page import="publisher.Publisher_DAO_Implt"%>
<%@page import="publisher.Publisher_DAO"%>
<%@page import="supplier.Supplier_DAO_Implt"%>
<%@page import="supplier.Supplier_DAO"%>
<%@page import="profile.Profiles_DAO_Implt"%>
<%@page import="profile.Profiles_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="books.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Books</title>        
        <style>
            tr th{
                text-align: center;
            }
        </style>
    </head>
    <%
        Books_DAO bdao=new Books_DAO_Implt();
        List<Books> list=bdao.display();
        request.setAttribute("Books", list);
        
        Supplier_DAO sdao=new Supplier_DAO_Implt();
        request.setAttribute("sdao", sdao);
    %>
    <body style=" background-color: aliceblue" id="myPage">
        ${Message}
        <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Books</i></b></center>
        <hr/>        
            
            <%
            if(request.getSession().getAttribute("role").equals("BUYER") || request.getSession().getAttribute("role").equals("0") ){
            %>
            <div class="container">
                <div id="products" class="column list-group">
                    <c:forEach items="${Books}" var="b">
                        <div class="item  col-xs-4 col-lg-4">
                            <div class="thumbnail" style="height: auto; width: auto">               
                                <img class="group list-group-image img-responsive re" src=" ${b.getImage()}" alt="image" style="height: 250px; width: 350px" width="460" height="345" />
                                <div class="caption">
                                    <h3 style="font-family: cursive; font-weight: bold" class="group inner list-group-item-heading">${b.getName()}</h3>
                                    <p class="group inner list-group-item-text">
                                        BY:<b style="font-size: 20px"><i>${b.getAuthor()}</i></b>
                                    </p>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-md-6">
                                        <p class="lead"> &#8377; ${b.getPrice()} </p>
                                    </div>
                                    <div class="col-xs-12 col-md-12">
                                        <a class="btn btn-success" style="float: left" href="BuyBook.jsp?Sno=${b.getSerial_No()}&page=ViewBook.jsp&copy=${b.getCopies()}">Buy Now</a>
                                        <a class="btn btn-success" style="float: right" href="AddToCart.jsp?Sno=${b.getSerial_No()}&copy=${b.getCopies()}">Add to cart</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <br>
            <hr/>
        <%}else{%>
            <table class="table table-striped" style="text-align: center">
                <thead>
                    <tr>
                        <th>Serial No.</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Publisher</th>
                        <th>Category</th>
                        <th>Edition</th>
                        <th>Price</th>                 
                        <%try{
                            if(request.getSession().getAttribute("role").equals("ADMINISTRATOR")){
                        %>
                        <th>Suppliers</th>
                        <%}}catch(Exception e){}%>
                        <th>Image</th>
                        <th>Update</th>
                        <th>Add To Cart</th>
                        <th>BUY</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${Books}" var="b">
                        <tr style="text-align: center">
                            <td>${b.getSerial_No()}</td>
                            <td>${b.getName()} </td>
                            <td>${b.getAuthor()}</td>
                            <td>${b.getPublisher()}</td>
                            <td>${b.getCategory()}</td>
                            <td>${b.getEdition()}</td>
                            <td>${b.getPrice()}</td>
                            <%try{
                            if(request.getSession().getAttribute("role").equals("ADMINISTRATOR")){
                            %>
                                <td>
                                    <select class="form form-control">
                                        <option selected>List of Suppliers...</option>
                                        <c:forEach items="${sdao.getSupplierP(b.getPublisher())}" var="s">
                                            <option>${s.getSID()}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            <%}}catch(Exception e){}%>
                            <td><img class="img-rounded" src="${b.getImage()}" style="height:20vh" ></td>
                            <td><a href="UpdateBook.jsp?Sno=${b.getSerial_No()}" class="btn btn-default">Update</a></td>
                            <td><a href="AddToCart.jsp?Sno=${b.getSerial_No()}&copy=${b.getCopies()}" class="btn btn-default"><span class="glyphicon glyphicon-plus"> &#xe116; </span></a></td>
                            <td><a href="BuyBook.jsp?Sno=${b.getSerial_No()}&page=ViewBook.jsp&copy=${b.getCopies()}" class="btn btn-success">Buy Now</a></td>
                        </tr>
                    </c:forEach>
                    <%try {
                        request.getSession().getAttribute("status").toString();
                    %>
                        <tr>
                            <td colspan="13"> 
                                <a href="AddBook.jsp" class="btn btn-default" style="width: 20%; height:90px;" >
                                    <span class="glyphicon glyphicon-plus" style="font-size: 300%"></span><br>
                                    <b><i>Add Book</i></b>
                                </a>
                            </td>
                        </tr>
                    <%} catch (Exception e) {}%>
                </tbody>
            </table>
        <%}%>
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>
