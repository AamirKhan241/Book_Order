<%-- 
    Document   : newjspViewCart
    Created on : Mar 6, 2018, 10:27:15 AM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="profile.Profiles_DAO_Implt"%>
<%@page import="profile.Profiles_DAO"%>
<%@page import="books.Books_DAO_Implt"%>
<%@page import="books.Books_DAO"%>
<%@page import="cart.Cart"%>
<%@page import="java.util.List"%>
<%@page import="cart.Cart_DAO_Implt"%>
<%@page import="cart.Cart_DAO"%>
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
        </style>

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
        Profiles_DAO pdao=new Profiles_DAO_Implt();
        Cart_DAO cdao=new Cart_DAO_Implt();
        
        List<Cart> list=cdao.displayC(pdao.displayE(request.getSession().getAttribute("user").toString()).getId());
        
        request.setAttribute("Cart", list);
        Books_DAO bdao=new Books_DAO_Implt();
        request.setAttribute("Books", bdao);
    %>
    <body id="myPage">
        <c:import url="Header.jsp"></c:import>
        <br>
        <table class="table table-striped" >
            <caption><center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Cart</i></b></center></caption>
            <thead>
                <tr>
                    <th>Serial No.</th>
                    <th>Book Name</th>
                    <th>Author</th>
                    <th>Publisher</th>
                    <th>Image</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                
                
            <c:forEach items="${Cart}" var="c">
                <c:forEach items="${Books.display(c.getBook_id())}" var="b">
                        <tr style="text-align: center">
                            <td>${b.getSerial_No()}</td>
                            <td>${b.getName()} </td>
                            <td>${b.getAuthor()}</td>
                            <td>${b.getPublisher()}</td>
                            <td><img src="  ${b.getImage()}" style="border-radius: 10%" height="100px"></td>
                            <td>&#8377; ${b.getPrice()}</td>
                            <td>                                
                                <a class ="btn btn-default" href="UpdateCart?id=min,${c.getSerial_no()},${b.getSerial_No()}">
                                    <span class=" glyphicon glyphicon-minus"></span>
                                </a> 
                                ${c.getQuantity()}
                                <a class ="btn btn-default" href="UpdateCart?id=plus,${c.getSerial_no()},${b.getSerial_No()}">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </a>
                            </td>
                            
                            <td>&#8377; ${b.getPrice()*c.getQuantity()}</td>
                            <td><a href="DeleteCart?Sno=${c.getSerial_no()}" class="btn btn-danger">Delete</a></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
                        <tr style="background-color: blanchedalmond">
                            <td colspan="8">Total Amount</td>
                            <td style="text-align: center; font-size: large" ><b >&#8377; <%=cdao.sum(Integer.valueOf(request.getSession().getAttribute("id").toString()))%></b></td>
                        </tr>
            </tbody>
        </table>
        <script>
            if(<%=cdao.sum(Integer.valueOf(request.getSession().getAttribute("id").toString()))%> === 0){
                document.write('<br><center><h1 style="color: red">Cart is Empty</h1></center></b>');
                document.getElementById('checkout').style.display='none';
            }
        </script>
                        
        <a id="checkout" href="BuyBook.jsp?page=<%=request.getRequestURI().replace("/Book_Order/", "")%>" class="well col-sm-4" style="width:40%; text-align: center; margin-left: 30%;" >Check Out</a>
        
        <br>
        <br>
        <br>
        <br>
        <div style="position: fixed; left: 0; bottom: 50%; width: 100%; text-align: center;">${Message}</div>
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>