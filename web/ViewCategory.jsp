<%-- 
    Document   : ViewCategory
    Created on : Feb 22, 2018, 7:31:41 PM
    Author     : aamir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="category.Category"%>
<%@page import="java.util.List"%>
<%@page import="category.Category_DAO_Implt"%>
<%@page import="category.Category_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>        
        <style>
            tr th{
                text-align: center;
            }
        </style>
    </head>
    <%
        Category_DAO cdao=new Category_DAO_Implt();
        List<Category> list=cdao.display();
        
        request.setAttribute("Categories", list);
    %>
    <body style=" background-color: aliceblue" id="myPage">
    <c:import url="Header.jsp"></c:import>
    <br>
    <table class="table table-striped" style="text-align: center">
            <caption><center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Categories</i></b></center></caption>
            <thead>
                <tr>
                    <th>Id.</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Image</th>
                    <%if(request.getSession().getAttribute("role").equals("ADMINISTRATOR")){%>
                        <th>Update</th>
                    <%}%>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${Categories}" var="c">
                <tr style="text-align: center">
                    <td>${c.getId()}</td>
                    <td>${c.getName()}</td>
                    <td>${c.getDescription()}</td>
                    <td><img src="${c.getImage()}" height="300px" ></td>
                    <%if(request.getSession().getAttribute("role").equals("ADMINISTRATOR")){%>
                        <td><a href="UpdateCategory.jsp?id=${c.getId()}" class="btn btn-success">Update</a></td>
                    <%}%>
                </tr>
            </c:forEach>
            <%if(request.getSession().getAttribute("role").equals("SUPPLIER") || request.getSession().getAttribute("role").equals("ADMINISTRATOR")){%>
                <tr>
                    <td colspan="6"> 
                        <a href="AddCategory.jsp" class="btn btn-default" style="width: 20%; height:90px;" >
                            <span class="glyphicon glyphicon-plus" style="font-size: 300%"></span><br>
                            <b><i>Add Category</i></b>
                        </a>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    <c:import url="Footer.jsp"></c:import>
    </body>
</html>
