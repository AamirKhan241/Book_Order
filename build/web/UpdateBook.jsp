<%-- 
    Document   : UpdateBook
    Created on : Feb 22, 2018, 11:36:47 PM
    Author     : aamir
--%>

<%@page import="publisher.Publisher"%>
<%@page import="publisher.Publisher_DAO_Implt"%>
<%@page import="publisher.Publisher_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="books.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="category.*"%>
<%@page import="java.util.List"%>
<%@page import="supplier.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Book</title>
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
        
        Books_DAO bdao=new Books_DAO_Implt();
        Books b=bdao.display(Integer.valueOf(request.getParameter("Sno")));
        
        Publisher_DAO pdao=new Publisher_DAO_Implt();
        Publisher p=pdao.displaySi(b.getPublisher());
        
        if( !request.getSession().getAttribute("role").equals("ADMINISTRATOR"))
            if(Integer.valueOf(request.getSession().getAttribute("id").toString()) != p.getSupplier_Id())
                response.sendRedirect("Unauthorised.jsp");
            
    %>
    <body id="myPage">
    <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Update Book</i></b></center>
        <br>
        <form id="updateBook" method="post" style="width: 90%; text-align: center; margin: auto" enctype="multipart/form-data">
            <label style="float: left">Category:</label>
            <input type="text" value="<%=b.getCategory()%>" name="category" class="form form-control" readonly required>
            <br>
            <label style="float: left">Book Name:</label>
            <input type="text" value="<%=b.getName()%>" name="bName" class="form form-control" required pattern=".{5,30}" title="length should be between 5-30.">
            <br>
            <label style="float: left">Author:</label>
            <input type="text" value="<%=b.getAuthor()%>" name="author" class="form form-control" required pattern=".{5,30}" title="length should be between 5-30.">
            <br>
            <label style="float: left">Publisher:</label>
            <input type="text" value="<%=b.getPublisher()%>" name="publisher" class="form form-control" readonly required>
            <br>
            <label style="float: left">ISBN:</label>
            <input type="text" value="<%=b.getISBN()%>" name="isbn" class="form form-control" required readonly>
            <br>
            <label style="float: left">Store Location:</label>
            <input type="text" value="<%=b.getStore_Location()%>" name="location" class="form form-control" required pattern=".{3,100}" title="length should be between 3-100.">
            <br>
            <label style="float: left">Copies:</label>
            <input type="number" value="<%=b.getCopies()%>" name="copies" class="form form-control" required pattern=".{1,3}" title="length should be between 1-3.">
            <br>
            <label style="float: left">Edition:</label>
            <select name="edition" class="form form-control" required>
                <option><%=b.getEdition()%></option>
                <option value="">--Select Edition from the following list---</option>
                <% Calendar now=Calendar.getInstance();
                    for(int i=20;i>0;i--)
                        out.print("<option>"+(now.get(Calendar.YEAR) -i)+"</option>");
                %>
            </select>
            <br>
            <label style="float: left">Price(in INR):</label>
            <input type="number" value="<%=b.getPrice()%>" name="price" class="form form-control" required pattern=".{1,5}" title="length should be between 1-5.">
            <br>
            <label style="float: left">Image :</label>
            <input type="file" name="img" class="form form-control" onchange="readURL(this)" >
            <br>
            <input id="Usubmit" type="submit" value="Update" class="btn btn-success" style="width: 30%" onclick="setActionS()">
            <script>
                function setActionS(){
                    document.getElementById("updateBook").action="UpdateBook?Sno=<%=b.getSerial_No()%>";
                }
            </script>
            <input type="submit" value="Cancel Update" class="btn btn-default" style="width: 30%" onclick="setActionC()">
            <script>
                function setActionC(){
                    document.getElementById("updateBook").action="ViewBook.jsp";
                }
            </script>
        </form>
        <br><br>
        <div style="padding: auto;margin: auto; width:30%; text-align:center  ">
            <img id="dispImg" src="<%=b.getImage()%>" alt="Image Not Found" style="width: 100%">
        </div>
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>
