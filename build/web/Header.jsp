
<%-- 
    Document   : Header
    Created on : Feb 20, 2018, 5:57:18 PM
    Author     : aamir
--%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/Login.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        #suppDet{
            display: none;
        }
        #da{
            display: none;
        }
        body{
            font-family: Arial, Helvetica, sans-serif;
        }
    </style>
    <script>
        $(document).ready(function(){  
            var checkField;
            checkField = $("input#password").val().length;  

            var enable = function(){         
            if(checkField > 0){
                $('#Confirm_password').removeAttr("disabled");
            $('#password, #Confirm_password').on('keyup', function () {
                if (($('#password').val() == $('#Confirm_password').val())&& checkField != 0 ) 
                    $('#Submit').removeAttr("disabled");
                else 
                    $('#Submit').attr("disabled","disabled");
            });
            }else{
                $('#Confirm_password').attr("disabled","disabled");
            }
            } 
            enable();            

            $('input#password').keyup(function(){ 
                checkField = $("input#password").val().length;
                enable();
            });
        });
    </script>    
    

    <!-- Navbar -->
    <div class="w3-top">
        <div class="w3-bar w3-theme-d2 w3-left-align">
            <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-hover-white w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
            <a href="index.jsp" class="w3-bar-item w3-button w3-teal"><i class="fa fa-home w3-margin-right"></i>BooksVilla.com</a>
            <a href="ViewBook.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Books</a>
            <a href="ContactUs.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-white">Contact</a>
            <%if(!(request.getSession().getAttribute("role").equals("0"))){%>
            <div class="w3-dropdown-hover w3-hide-small">
                <button class="w3-button" title="Notifications">More <i class="fa fa-caret-down"></i></button>     
                <div class="w3-dropdown-content w3-card-4 w3-bar-block">
                <%if(request.getSession().getAttribute("role").equals("BUYER")){%>
                    <a href="ViewOrder.jsp?view=mine" class="w3-bar-item w3-button">ORDERS</a>
                    <a href="ViewCategory.jsp" class="w3-bar-item w3-button">Category</a>
                <%}else if(request.getSession().getAttribute("role").equals("SUPPLIER")){%>
                    <a href="ViewCategory.jsp" class="w3-bar-item w3-button">Category</a>
                    <a href="ViewOrder.jsp?view=mine" class="w3-bar-item w3-button">ORDERS</a>
                    <a href="ViewPurchase.jsp?view=mine"  class="w3-bar-item w3-button">Purchase</a>
                <%}else if(request.getSession().getAttribute("role").equals("ADMINISTRATOR")){%>
                    <a href="ViewCategory.jsp" class="w3-bar-item w3-button">Category</a>
                    <a href="ViewProfiles.jsp" class="w3-bar-item w3-button">Profiles</a>
                    <a href="ViewPublisher.jsp" class="w3-bar-item w3-button">Publisher</a>
                    <a href="ViewSupplier.jsp" class="w3-bar-item w3-button">Supplier</a>
                    <a href="ViewOrder.jsp?view=mine" class="w3-bar-item w3-button">ORDERS</a>
                    <a href="ViewOrder.jsp?view=all" class="w3-bar-item w3-button">ALL ORDERS</a>
                    <a href="ViewPurchase.jsp?view=mine"  class="w3-bar-item w3-button">Purchase</a>
                    <a href="ViewPurchase.jsp?view=all" class="w3-bar-item w3-button">ALL Purchase</a>
                    <a href="ViewSale.jsp" class="w3-bar-item w3-button">Sale</a>
                    <a href="ViewProfit.jsp"  class="w3-bar-item w3-button">Profit Statement</a>
                    <a href="ViewUserQuery.jsp"  class="w3-bar-item w3-button">User's Query</a>
                    <%}%>
                </div>
            </div>
            <%}
                if(!request.getSession().getAttribute("status").toString().equalsIgnoreCase("active")){
            %>
            <a onclick="document.getElementById('SignUp').style.display='block'" class="w3-right w3-bar-item w3-button w3-hide-small w3-hover-white" title="SignUp">SignUp  <span class="glyphicon glyphicon-user" ></span> </a>
            <a onclick="document.getElementById('LogIn').style.display='block'" class="w3-right  w3-bar-item w3-button w3-hide-small w3-hover-white" title="Login">LogIn  <span class="glyphicon glyphicon-log-in" ></span> </a>            <%
                }else{
            %>
            <a href="UpdateProfile.jsp?id=<%=request.getSession().getAttribute("id")%>" class="w3-right w3-bar-item w3-button w3-hide-small w3-hover-green btn-default" ><span class="glyphicon glyphicon-user"><%=request.getSession().getAttribute("name")%></span></a>
            <a href="ViewCart.jsp" class="w3-bar-item w3-button w3-hide-small w3-hover-silver w3-right "><c:import url="Cart.jsp"></c:import></a>
            <a href="LogOut?ReqPage=<%=request.getRequestURI().replace("/Book_Order/", "")%>" class="w3-right w3-bar-item w3-button w3-hide-small w3-hover-silver"><span class="glyphicon glyphicon-log-out"></span> LogOut</a>
            <%
                }
            %>
        </div>

  <!-- Navbar on small screens -->
        <div id="navDemo" class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium">
            <a href="" class="w3-bar-item w3-button">Books</a>
            <a href="#team" class="w3-bar-item w3-button">Team</a>
            <a href="#contact" class="w3-bar-item w3-button">Contact</a>
        </div>
    </div>
        
    <!--SignUp Modal-->
    <div id="SignUp" class="modalL">
        <!--<form class="modal-contentL animateL" action="LogIn" method="post" style="width:60%; padding: auto; margin: auto;">-->
            <form action="SignUp" method="post" style="width: 60%; text-align: center; margin: auto; padding: auto" class="modal-contentL animateL">
                <div class="modal-header" style="background-color: aquamarine" >
                    <button type="button" class="close" data-dismiss="modal" onclick="document.getElementById('SignUp').style.display='none'">&times;</button>
                    <center><h3><b><i style="color: whitesmoke; font-family: fantasy">SignUp</i></b></h3></center>
                </div>
                <hr/>
                <select name="role" class="form form-control" id="role" onchange="supp()" required>
                    <option value="">Select a role...</option>
                    <option>Buyer</option>
                    <option>Supplier</option>
                </select>
                <br>
                <script>
                        function supp(){
                            if(document.getElementById("role").value.length>0){
                                document.getElementById("da").style.display="block";
                                if(document.getElementById("role").value === 'Supplier' )
                                    document.getElementById("suppDet").style.display="block";
                                else
                                    document.getElementById("suppDet").style.display="none";
                            }
                            else
                                document.getElementById("da").style.display="none";
                        }
                </script>
                <div id="da">
                    <div id="suppDet">              
                        <input type="text" placeholder="Publisher..." name="Publisher" class="form form-control" pattern=".{3,50}" title="length should be between 3-50.">              
                        <br>
                    </div>    
                    <input type="text" placeholder="First Name..." name="fName" class="form form-control" required pattern=".{3,20}" title="length should be between 3-20.">
                    <br>
                    <input type="text" placeholder="Last Name..." name="lName" class="form form-control" required pattern=".{3,20}" title="length should be between 3-20.">
                    <br>
                    <input type="email" placeholder="Email..." name="email" class="form form-control" required>
                    <br>
                    <input id="password" type="password" placeholder="Password..." name="password" class="form form-control" required pattern=".{8,16}" title="length should be between 8-16.">
                    <br>
                    <input id="Confirm_password" type="password" placeholder="Confirm Password..." name="cPassword" class="form form-control" required>
                    <br>
                    <input type="number" placeholder="Contact Number..." name="contact" class="form form-control" required pattern=".{10,10}" title="length should be 10.">
                    <br>
                    <textarea name="address" placeholder="Address..." class="form form-control" required pattern=".{3,500}" title="length should be between 3-500."></textarea>
                    <br>
                    <input id="Submit" type="Submit" value="SignUp" class="btn btn-success" style="width: 30%" disabled>
                    <input type="reset" value="Clear All" class="btn btn-default" style="width: 30%">
                </div>
                <div class="modal-footer">
                    Already have a account, click here <a onclick="document.getElementById('LogIn').style.display='block';"><span class="glyphicon glyphicon-log-in"></span> Login</a>
                </div>
        </form>
    </div>
    <!-- LogIn Modal -->
    <div id="LogIn" class="modalL">

        <form class="modal-contentL animateL" action="LogIn" method="post" style="width:60%; padding: auto; margin: auto;">
            <div class="imgcontainer">
                <span onclick="document.getElementById('LogIn').style.display='none'" class="closeL" title="Close Modal">&times;</span>
                <img src="Images/login.jpg" alt="Avatar" class="avatar">
            </div>

            <div class="containerL">
                <label for="uName"><b>Username</b></label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input class=" form-control" type="email" placeholder="Enter Username" name="uName" required>
                </div>

                <label for="uPass"><b>Password</b></label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input class="form-control" type="password" placeholder="Enter Password" name="uPass" required>
                </div>
                <br>
                <center><input class="btn btn-success" type="submit" style="width:20%" value="LogIn"></center>
                <br>
            </div>
        </form>
    </div>
