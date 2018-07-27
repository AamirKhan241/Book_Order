<%-- 
    Document   : BuyBook
    Created on : Mar 8, 2018, 12:29:06 PM
    Author     : aamir
--%>

<%@page import="books.Books_DAO_Implt"%>
<%@page import="books.Books_DAO"%>
<%@page import="books.Books"%>
<%@page import="cart.Cart"%>
<%@page import="java.util.List"%>
<%@page import="cart.Cart_DAO_Implt"%>
<%@page import="cart.Cart_DAO"%>
<%@page import="java.util.Arrays"%>
<%@page import="profile.Profiles"%>
<%@page import="profile.Profiles_DAO_Implt"%>
<%@page import="profile.Profiles_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <%
        System.out.println(request.getRequestURI());
        
        if(!request.getSession().getAttribute("status").toString().equals("active")){
            request.setAttribute("message", "<script>alert('You have to login first');</script>");
            RequestDispatcher rdd=request.getRequestDispatcher("");
            if(request.getParameter("pageReq") == null){
                rdd=request.getRequestDispatcher("index.jsp");
            }else{
                rdd=request.getRequestDispatcher(request.getParameter("pageReq"));
            }
            rdd.forward(request, response);
        }
        
        
        Cart_DAO cdao=new Cart_DAO_Implt();
        Profiles_DAO pdao=new Profiles_DAO_Implt();
        Profiles p=new Profiles();
        if(!(cdao.item(Integer.valueOf(request.getSession().getAttribute("id").toString())) > 0) && request.getParameter("page").toString().equals("ViewCart.jsp")){
            
            request.setAttribute("Message", "<center><div class='alert alert-danger' role='alert' style='width:50%' >" +
                "  <strong>Warning!</strong> CART IS EMPTY!!!</div></center>"
                + "<script>window.setTimeout(function() {$('.alert').fadeTo(500, 0).slideUp(500, function(){$(this).remove();});}, 2000);</script>");
                RequestDispatcher rd=request.getRequestDispatcher("ViewCart.jsp");
                System.out.println("Cart Updated");
                rd.forward(request, response);
        }else{
            if(!request.getParameter("page").toString().equals("ViewCart.jsp") && Integer.valueOf(request.getParameter("copy").toString()) <= 0){
                request.setAttribute("Message", "<script>alert('This book is OUT OF STOCK');</script>");
                RequestDispatcher rd=request.getRequestDispatcher("ViewBook.jsp");
                rd.forward(request, response);
            }
            p=pdao.display(Integer.valueOf(request.getSession().getAttribute("id").toString()));
        }
        
    %>
    <body style="background-color: blanchedalmond" id="myPage">
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Buy Book</i></b></center>
        
        <div class="container">
            <form action="" method="post" id="frm" class="form_class">   
            <label style="float: left">Reciever Name</label>
            <input type="text" name="dName" placeholder="Reciever Name" value="<%=p.getFirst_Name()+" "+p.getLast_Name()%>" class="form form-control" required pattern=".{3,20}" title="length should be between 3-20.">
            <br>
            <label style="float: left">Reciever Address</label>
            <textarea name="dAddress" placeholder="Delivery Address..." class="form form-control" required pattern=".{3,500}" title="length should be between 3-500."><%=p.getAddress()%></textarea>
            <br>
            <label style="float: left">Contact Number</label>
            <input type="number" name="dContact" placeholder="Contact Number..." value="<%=p.getContact()%>"class="form form-control" required pattern=".{10,10}" title="length should be 10.">
            <br>
            <label style="float: left">Description</label>
            <input type="text" name="Description" placeholder="Order Description..." class="form form-control" required pattern=".{3,100}" title="length should be between 3-100.">
            <br>
            <%
                if(request.getParameter("page").equals("ViewBook.jsp")){
                    Books_DAO bdao=new Books_DAO_Implt();
                    Books dao=bdao.display(Integer.valueOf(request.getParameter("Sno")));
            %>
            <div class="form-group">
                <label for="email">Quantity</label>
                <select class="form-control" name="dquantity" id="qnt" onchange="getP()"  required>
                    <option value="">Select the number of books...</option>
                    <%
                        for (int i = 1; i <= Integer.valueOf(dao.getCopies()); i++) {
                                out.println("<option>"+i+"</option>");
                            }
                    %>
                </select>
            </div>
            <div class="form-group">
                <label for="email">Amount</label>
                <input type="number" class="form-control" name="dAmt" id="amt" value="0">
            </div>
            <script>
               function getP(){  
                    document.getElementById("amt").value=document.getElementById("qnt").value * '<%=Integer.valueOf(dao.getPrice())%>';
                }
            </script>
            <%
                }else{
                    Cart_DAO dao=new Cart_DAO_Implt();
                %>
                    <label style="float: left">Amount</label>
                    <input type="number" name="dAmount" value="<%=dao.sum(Integer.valueOf(request.getSession().getAttribute("id").toString()))%>"class="form form-control" id="amt" required>
                <%    
                }
            %>
            
                        </form>
            <c:import url="Transaction.jsp"></c:import>
            <br>
            <hr/>
                <center><a type="button" class="btn btn-default" href="<%=request.getParameter("page")%>">Go Back</a></center>
        </div>            
    </body>
</html>