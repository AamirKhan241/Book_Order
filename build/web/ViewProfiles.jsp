<%@page import="profile.Profiles"%>
<%@page import="java.util.List"%>
<%@page import="profile.Profiles_DAO_Implt"%>
<%@page import="profile.Profiles_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profiles</title>
        <style>
            tr th{
                text-align: center;
            }
        </style>
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

        Profiles_DAO pdao=new Profiles_DAO_Implt();
        List<Profiles> list=pdao.display();
        
        request.setAttribute("Profiles", list);
    %>
    <body style=" background-color: aliceblue" id="myPage">
    <c:import url="Header.jsp"></c:import>
               
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Profiles</i></b></center>
            
            <input class="form-control" style="float: right; width: 30%" id="filter" type="text" placeholder="Filter Profiles..">
              
      <script>
            $(document).ready(function(){
              $("#filter").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#profiles tr").filter(function() {
                  $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
              });
            });
        </script>
        <table class="table table-striped" >
            <thead>
                <tr>
                    <th>Id.</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Contact</th>
                    <th>Role</th>
                    <th>Address</th>
                    <th>Password</th>
                    <th>Status</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody id="profiles">
                <c:forEach items="${Profiles}" var="p">
                    <tr style="text-align: center">
                        <td>${p.getId()}</td>
                        <td>${p.getFirst_Name()} ${p.getLast_Name()}</td>
                        <td>${p.getEmail()}</td>
                        <td>${p.getContact()}</td>
                        <td id="role">${p.getRole()}</td>
                        <td style="width: 25%">${p.getAddress()}</td>
                        <td><input type="button" class="btn btn-default" value="View Password" onclick="alert('Password: ${p.getPassword()}')"></td>
                        <td><a href="Status?id=${p.getId()}&delete=no" class="btn btn-default">${p.getStatus()}</a></td>
                        <td><a href="UpdateProfile.jsp?id=${p.getId()}" class="btn btn-success">Update</a></td>
                    </tr>
                </c:forEach>
                    
            </tbody>
        </table>
        <c:import url="Footer.jsp"></c:import>
    </body>
</html>
