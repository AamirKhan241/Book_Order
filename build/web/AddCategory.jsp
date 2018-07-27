<%-- 
    Document   : AddCategory
    Created on : Feb 22, 2018, 6:27:46 PM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category</title>
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
    %>
    <body id="myPage">
        <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Add Category</i></b></center>
        <br>
        <form action="AddCategory" method="post" style="width: 90%; text-align: center; margin: auto" enctype="multipart/form-data">
            <input type="text" placeholder="Category Name..." name="cName" class="form form-control" required pattern=".{5,30}" title="length should be between 5-30.">
            <br>
            <input type="text" placeholder="Description..." name="desc" class="form form-control" required pattern=".{5,200}" title="length should be between 5-200.">
            <br>
            <input type="file" name="img" class="form form-control" onchange="readURL(this)" required>
            <br>
            <input type="Submit" value="Add Category" class="btn btn-success" style="width: 30%">
            <input type="reset" value="Clear All" class="btn btn-default" style="width: 30%">
        </form>
        <br><br>
        <div style="padding: auto;margin: auto; width:30%; text-align:center">
            <img id="dispImg" alt="Your Image will render here" style="width: 100%">
        </div>
        

        <c:import url="Footer.jsp"></c:import>
    </body>
</html>
