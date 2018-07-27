<%-- 
    Document   : BuyBook
    Created on : Feb 25, 2018, 1:24:22 PM
    Author     : aamir
--%>

<%@page import="cart.Cart"%>
<%@page import="java.util.List"%>
<%@page import="cart.Cart_DAO_Implt"%>
<%@page import="cart.Cart_DAO"%>
<%@page import="books.Books_DAO_Implt"%>
<%@page import="books.Books_DAO"%>
<%@page import="books.Books"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buy</title>
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
        
        
        if(Integer.valueOf(request.getParameter("copy").toString()) <= 0){
            request.setAttribute("Message", "<script>alert('This book is OUT OF STOCK');</script>");
            RequestDispatcher rd=request.getRequestDispatcher("ViewBook.jsp");
            rd.forward(request, response);
        }
        
        Books_DAO bdao=new Books_DAO_Implt();
        Books b=bdao.display(Integer.valueOf(request.getParameter("Sno")));
        Cart_DAO cdao=new Cart_DAO_Implt();
        List<Cart> list=cdao.display();
        
        for (Cart cart : list) {
                if(cart.getBook_id().equals(b.getISBN()) && cart.getcID() == Integer.valueOf(request.getSession().getAttribute("id").toString()))
                {
                    request.setAttribute("Message", "<script>alert('Already added to cart!')</script>");
                    RequestDispatcher rd=request.getRequestDispatcher("ViewBook.jsp");
                    rd.forward(request, response);
                }
            }


    %>
    <body id="myPage">
        <c:import url="Header.jsp"></c:import>z
        <div class="container-fluid" style="width: 90%">
            <div id="img" style="float: left; width: 100%; height:50%">
                <h2 style="text-align: center; font-weight: bold; font-family: cursive">Order Now</h2>
                <div>
                    <img src="<%=b.getImage()%>" class="img-thumbnail" style="float: left; width: 40%; height:80vh" alt="Image Not Found...">
                </div>
                <div class="container-fluid" style="border: 2px black solid; width: 60%; float: right">
                    <table style="width: 100%" class="table table-responsive table-striped" >
                        <tbody>
                            <tr>
                                <td>Book Name </td>
                                <td><%=b.getName()%></td>
                            </tr>
                            <tr>
                                <td>Author</td>
                                <td><%=b.getAuthor()%></td>
                            </tr>
                            <tr>
                                <td>Publisher </td>
                                <td><%=b.getPublisher()%></td>
                            </tr>
                            <tr>
                                <td>Edition</td>
                                <td><%=b.getEdition()%></td>
                            </tr>
                            <tr>
                                <td>Store Location</td>
                                <td><%=b.getStore_Location()%></td>
                            </tr>
                            <tr>
                                <td>Price</td>
                                <td><%=b.getPrice()%></td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="#" data-toggle="modal"  class="btn btn-success" style="text-align:center; width: 100%; height: 5vh" data-target="#buy">add to cart</a>
                            
                    <div id="buy" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <!-- Modal content-->
                            <div class="modal-content" >
                                <div class="modal-header" style="background-color: lime">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Add to cart</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="AddToCart?ISBN=<%=b.getISBN()%>" method="post">
                                    <select name="quant" class="form form-control" required>
                                        <option selected value="">Select from the list...</option>
                                        <%
                                            for (int i = 1; i <= Integer.valueOf(b.getCopies()); i++) {
                                                    out.println("<option>"+i+"</option>");
                                                }
                                        %>
                                    </select>

                                </div>
                                <div class="modal-footer">
                                    <input type="submit" class="btn btn-success" value="Add">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                    <c:import url="Footer.jsp"></c:import>
    </body>
</html>
