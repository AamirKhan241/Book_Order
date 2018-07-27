<%-- 
    Document   : UpdateCategory
    Created on : Feb 22, 2018, 7:51:57 PM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="category.Category"%>
<%@page import="category.Category_DAO_Implt"%>
<%@page import="category.Category_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Category</title>
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
            if(!request.getSession().getAttribute("role").toString().equals("ADMINISTRATOR")){
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
        Category_DAO cdao=new Category_DAO_Implt();
        Category c=cdao.display(Integer.valueOf(request.getParameter("id")));
    %>
    <body id="myPage">
    <c:import url="Header.jsp"></c:import>
    <br>
    <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Update Category</i></b></center>

    <form id="updateCategory" method="post" style="text-align: center; width: 80%; margin: auto" enctype="multipart/form-data">
            <label style="float: left">ID:</label>
            <input type="text" value="<%=c.getId()%>" class="form form-control" readonly required>
            <br>
            <label style="float: left">Name:</label>
            <input type="text" value="<%=c.getName()%>" name="cName" class="form form-control" required pattern=".{5,30}" title="length should be between 5-30.">
            <br>
            <label style="float: left">Description:</label>            
            <br>
            <input type="text" value="<%=c.getDescription()%>" name="desc" class="form form-control" required pattern=".{5,200}" title="length should be between 5-200.">
            <br>
            <label style="float: left">Image:</label>
            <input type="file" onchange="readURL(this)" name="img" class="form form-control" value="" >
            <br>
            <input id="Usubmit" type="submit" value="Update" class="btn btn-success" style="width: 30%" onclick="setActionS()">
            <script>
                function setActionS(){
                    document.getElementById("updateCategory").action="UpdateCategory?id=<%=c.getId()%>";
                }
            </script>
            <input type="submit" value="Cancel Update" class="btn btn-default" style="width: 30%" onclick="setActionC()">
            <script>
                function setActionC(){
                    document.getElementById("updateCategory").action="ViewCategory.jsp";
                }
            </script>
        </form>
        <br><br>
        <div style="padding: auto;margin: auto; width:30%; text-align:center  ">
            <img id="dispImg" src="<%=c.getImage()%>" alt="Image Not Found" style="width: 100%">
        </div>
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>