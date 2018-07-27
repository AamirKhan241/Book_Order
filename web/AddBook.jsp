<%-- 
    Document   : AddBook
    Created on : Feb 22, 2018, 10:16:52 PM
    Author     : aamir
--%>

<%@page import="publisher.Publisher"%>
<%@page import="publisher.Publisher_DAO_Implt"%>
<%@page import="publisher.Publisher_DAO"%>
<%@page import="category.Category"%>
<%@page import="java.util.Calendar"%>
<%@page import="category.Category_DAO_Implt"%>
<%@page import="category.Category_DAO"%>
<%@page import="java.util.List"%>
<%@page import="supplier.Supplier"%>
<%@page import="supplier.Supplier_DAO_Implt"%>
<%@page import="supplier.Supplier_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
        <script>
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#dispImg')
                            .attr('src', e.target.result);
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
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
        Publisher_DAO pdao=new Publisher_DAO_Implt();
        Publisher p=new Publisher();
        if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("Administrator")){
            List<Publisher> plist=pdao.display();
            request.setAttribute("Publisher", plist);
        }
        else
            p=pdao.displaySi(Integer.valueOf(request.getSession().getAttribute("id").toString()));
        
        Category_DAO cdao=new Category_DAO_Implt();
        List<Category> Clist=cdao.display();
        request.setAttribute("Category", Clist);
    %>
    <body id="myPage">
        <c:import url="Header.jsp"></c:import>
        
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Add Book</i></b></center>
        <br>
        <form action="AddBook" method="post" style="width: 90%; text-align: center; margin: auto" enctype="multipart/form-data">
            <select name="category" class="form form-control" required>
                <option selected value="">--Select Category from the following list---</option>
                <c:forEach items="${Category}" var="c">
                    <option>${c.getName()}</option>
                </c:forEach>
            </select>
            <br>
            <input type="text" placeholder="Book Name..." name="bName" class="form form-control" pattern=".{5,30}" title="length should be between 5-30." required>
            <br>
            <input type="text" placeholder="Author..." name="author" class="form form-control" required pattern=".{5,30}" title="length should be between 5-30.">
            <br>
            <label style="float:left">Publisher</label>
            <%
                if(request.getSession().getAttribute("role").toString().equalsIgnoreCase("administrator")){
            %>
            <select name="publisher" class="form form-control" required>
                <option value="">Select Publisher</option>
                <c:forEach items="${Publisher}" var="p">
                    <option>${p.getName()}</option>
                </c:forEach>
                
            </select>
            <%}else{%>
            <input type="text" value="<%=p.getName()%>" name="publisher" class="form form-control" readonly>
            <%}%>
            <br>            
            <input type="number" placeholder="ISBN..." name="isbn" class="form form-control"  required pattern=".{13,13}" title="length should be 13.">
            <br>
            <input type="text" placeholder="Store Location..." name="location" class="form form-control" required pattern=".{5,100}" title="length should be between 5-100.">
            <br>
            <select name="copies" class="form form-control" required pattern=".{1,3}" title="length should be between 1-3.">
                <option selected value="">Number of Copies...</option>
                <% 
                    for(int i=1;i<1000;i++)
                        out.print("<option>"+i+"</option>");
                %>
            </select>
            <br>
            <select name="edition" class="form form-control"  required>
                <option selected value="">--Select Edition from the following list---</option>
                <% Calendar now=Calendar.getInstance();
                    for(int i=20;i>=0;i--)
                        out.print("<option>"+(now.get(Calendar.YEAR)-i)+"</option>");
                %>
            </select>
            <br>
            <input type="number" placeholder="Price..." name="price" class="form form-control"  required pattern=".{1,5}" title="length should be between 1-5.">
            <br>
            <input type="file" name="img" class="form form-control" onchange="readURL(this)" required>
            <br>
            <input type="Submit" value="Add Book" class="btn btn-success" style="width: 30%">
            <input type="reset" value="Clear All" class="btn btn-default" style="width: 30%">
        </form>
        <br><br>
        <div style="padding: auto;margin: auto; width:30%; text-align:center  ">
            <img id="dispImg" alt="Image Not Found" style="width: 100%">
        </div>
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>
