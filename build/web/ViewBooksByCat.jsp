<%-- 
    Document   : ViewBooksByCat
    Created on : Mar 30, 2018, 3:01:39 AM
    Author     : aamir
--%>

<%@page import="books.Books"%>
<%@page import="supplier.Supplier_DAO_Implt"%>
<%@page import="supplier.Supplier_DAO"%>
<%@page import="java.util.List"%>
<%@page import="books.Books_DAO_Implt"%>
<%@page import="books.Books_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        Books_DAO bdao=new Books_DAO_Implt();
        List<Books> list=bdao.displayC(request.getParameter("id"));
        request.setAttribute("Books", list);
        
        try {
                request.getSession().getAttribute("role").toString();
        } catch (Exception e) {
            request.getSession().setAttribute("role", "BUYER");
        }
    %>
    <body id="myPage">
        <c:import url="Header.jsp"></c:import>
        <br>
        <br>
        <center><b><i style="font-size: 50px; color: silver; font-family: fantasy">Books by Category <br><font style='color: black'> ( '<%=request.getParameter("id")%>' )</font></i></b></center>
        <hr/>        
          <div class="container">
                <div id="products" class="column list-group">
                    <%  
                    if(list.size() == 0){
                    %>
                        <div class="w3-center thumbnail" style="height:100px; width:100px; border:2px solid silver; padding:auto; margin:auto">
                            NO BOOK AVAILABLE
                        </div>
                    <%
                    }else{
                    %>
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
                    <%}%>
                </div>
            </div>
            <br>
            <hr/>
    <c:import url='Footer.jsp'></c:import>
    </body>
</html>
