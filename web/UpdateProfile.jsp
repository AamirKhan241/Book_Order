<%-- 
    Document   : UpdateProfile
    Created on : Feb 20, 2018, 5:59:44 PM
    Author     : aamir
--%>

<%@page import="publisher.Publisher"%>
<%@page import="publisher.Publisher_DAO_Implt"%>
<%@page import="publisher.Publisher_DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="profile.Profiles"%>
<%@page import="profile.Profiles_DAO_Implt"%>
<%@page import="profile.Profiles_DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Profile</title>                
        <script>
            $(document).ready(function(){  
                var checkField;
                //checking the length of the value of message and assigning to a variable(checkField) on load
                checkField = $("input#Upassword").val().length;  

                var enable = function(){         
                if(checkField > 0){
                    $('#Confirm_Upassword').removeAttr("disabled");
                $('#Upassword, #Confirm_Upassword').on('keyup', function () {
                    if (($('#Upassword').val() == $('#Confirm_Upassword').val())&& checkField != 0) 
                        $('#Usubmit').removeAttr("disabled");
                    else 
                        $('#Usubmit').attr("disabled","disabled");
                });
                }else{
                    $('#Confirm_Upassword').attr("disabled","disabled");
                }
                } 
                //calling enableDisableButton() function on load
                enable();            

                $('input#Upassword').keyup(function(){ 
                    //checking the length of the value of message and assigning to the variable(checkField) on keyup
                    checkField = $("input#Upassword").val().length;
                    //calling enableDisableButton() function on keyup
                    enable();
                });
            });
        </script>
        <script>
                function Usupp(){
                    if(document.getElementById("Urole").value === 'SUPPLIER')
                        document.getElementById("UsuppDet").style.display="block";
                    else{
                        document.getElementById("UsuppDet").style.display="none";
                        document.getElementById("det").style.display="none";
                    }
                }
        </script>
        <style>
            #UsuppDet{
                display: none;
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
        Profiles p=pdao.display(Integer.valueOf(request.getParameter("id")));
        
        Publisher_DAO pubdao=new Publisher_DAO_Implt();
        Publisher pub=new Publisher();

    %>
    <body id="myPage">
    <c:import url="Header.jsp"></c:import>
        <br>
        <center><b><i style="font-size: 100px; color: silver; font-family: fantasy">Updating Profile</i></b></center>
        <form id="updat" method="post" style="text-align: center; width: 80%; margin: auto">
            <label style="float: left">Role:</label>
            <select id="Urole" name="Urole" class="form form-control" onchange="Usupp()" required>
                <option selected><%=p.getRole()%></option>
                <option value="">Select a role...</option>
                <option>ADMINISTRATOR</option>
                <option>BUYER</option>
                <option>SUPPLIER</option>
            </select>
                <%
                    if(p.getRole().equalsIgnoreCase("supplier")){
                        pub=pubdao.displaySi(p.getId());
                %>
                <br>
                <input id="det" type="button" value="Publisher Detail" class="btn btn-success" onclick="return document.getElementById('UsuppDet').style.display='block'">
                <% 
                    }
                %>
            <br><br>
            <div id="UsuppDet">              
                <input type="text" placeholder="Publisher..." name="UPublisher" class="form form-control" id='p' pattern=".{3,20}" title="length should be between 3-20.">              
                <br>
            </div>
            <script>
                if('<%=p.getRole()%>' === 'SUPPLIER')
                    document.getElementById("p").value='<%=pub.getName()%>';
            </script>
            <label style="float: left">First Name:</label>
            <input type="text" value="<%=p.getFirst_Name()%>" name="UfName" class="form form-control" required pattern=".{3,20}" title="length should be between 3-20.">
            <br>
            <label style="float: left">Last Name:</label>
            <input type="text" value="<%=p.getLast_Name()%>" name="UlName" class="form form-control" required pattern=".{3,20}" title="length should be between 3-20.">
            <br>
            <label style="float: left">Email:</label>
            <input type="email" value="<%=p.getEmail()%>" name="Uemail" class="form form-control" readonly required>
            <br>
            <label style="float: left">Password:</label>
            <input id="Upassword" type="password" placeholder="Password..." name="Upassword" class="form form-control" required pattern=".{8,16}" title="length should be between 8-16.">
            <br>
            <label style="float: left">Confirm Password:</label>
            <input id="Confirm_Upassword" type="password" placeholder="Confirm Password..." name="UcPassword" class="form form-control" required>
            <br>
            <label style="float: left">Contact Number:</label>
            <input type="number" value="<%=p.getContact()%>" name="Ucontact" class="form form-control" required pattern=".{10,10}" title="length should be 10.">
            <br>
            <label style="float: left">Address:</label>
            <textarea name="Uaddress" class="form form-control" required pattern=".{3,500}" title="length should be between 3-500."><%=p.getAddress()%></textarea>
            <br>
            <input id="Usubmit" type="submit" value="Update" class="btn btn-success" style="width: 30%" onclick="setActionS()">
            <script>
                function setActionS(){
                    document.getElementById("updat").action="UpdateProfile?id=<%=p.getId()%>";
                }
            </script>
            <input type="submit" value="Cancel Update" class="btn btn-default" style="width: 30%" onclick="setActionC()">
       <% if(p.getId() == Integer.valueOf(request.getSession().getAttribute("id").toString())){%>
            <a href="Status?id=<%=p.getId()%>&delete=yes" class="btn btn-danger" style="width: 30%">Delete Account</a>
            <%}%>
            <script>
                function setActionC(){
                    document.getElementById("updat").action="ViewProfiles.jsp";
                }
            </script>
        </form>
            <c:import url="Footer.jsp"></c:import>
    </body>
</html>