<%-- 
    Document   : Admin
    Created on : Mar 22, 2018, 12:50:45 PM
    Author     : aamir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function(){  
                var checkField;
                //checking the length of the value of message and assigning to a variable(checkField) on load
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
                //calling enableDisableButton() function on load
                enable();            

                $('input#password').keyup(function(){ 
                    //checking the length of the value of message and assigning to the variable(checkField) on keyup
                    checkField = $("input#password").val().length;
                    //calling enableDisableButton() function on keyup
                    enable();
                });
            });
        </script>
        <style>
            .data{
                background-color: #ccccff; 
                width: 40%; 
                height: 50vh; 
                position: absolute; 
                top: 25%; 
                left: 30%; 
                border-radius: 10%; 
            }   
            #LogIn{
                background-image: url('Images/AdminLogin.png'); 
                background-size: 100% 40vh;
                background-repeat: no-repeat;
                height: 39vh; 
                border-radius: 10%;
                width: 100%; 
                margin-top: 2vh;
            }
            body{
                background-image: url('Images/AdminBack.jpg');
                background-repeat: no-repeat;
                background-size: 100% 100vh;
            }
        </style>
    </head>
    <body id="myPage">
    <center><b><i style="font-size: 100px; color: whitesmoke; font-family: fantasy">The Administrator</i></b></center>
        <div class="container data">
            <br/>
            <ul class="nav nav-tabs nav-justified" >
                <li class="active"><a data-toggle="tab" href="#LogIn">LogIn</a></li>
                <li><a data-toggle="tab" href="#SignUp">SignUp</a></li>
            </ul>

            <div class="tab-content" >
                <div id="LogIn" class="tab-pane fade in active">
                    <form action="LogIn" method="post">
                        <br><br>
                        <label for="uName"><b>Username</b></label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input class="form-control" type="email" placeholder="Enter Username" name="uName" required style="opacity: .7">
                        </div>
                        <br><br>
                        <label for="uPass"><b>Password</b></label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input class="form form-control" type="password" placeholder="Enter Password" name="uPass" required style="opacity: .7">
                        </div>
                        <br>
                        <center><input class="btn btn-success" type="submit" style="width:20%; left: 40%; bottom: 10px; position: absolute" value="LogIn"></center>
                    </form>
                </div>
                <div id="SignUp" class="tab-pane fade">
                    <form action="SignUp?role=ADMINISTRATOR" method="post" style="text-align: center">
                        <br>
                        <div class="col-sm-6" style="position: absolute;left: 0%;">
                            <input type="text" placeholder="First Name..." name="fName" class="form-control" pattern=".{3,20}" title="length should be between 3-20.">
                        </div>
                        <div class="col-sm-6"style="position: absolute;left: 50%;">
                            <input type="text" placeholder="Last Name..." name="lName" class="form-control" pattern=".{3,20}" title="length should be between 3-20.">
                        </div>                
                        <br><br><br>
                        
                        <div class="col-sm-6" style="position: absolute;left: 0%;">
                            <input type="email" placeholder="Email..." name="email" class="form-control">
                        </div>
                        <div class="col-sm-6"style="position: absolute;left: 50%;">
                            <input type="number" placeholder="Contact Number..." name="contact" class="form-control" pattern=".{10,10}" title="length should be 10.">
                        </div>                
                        <br><br><br>
                        <div class="col-sm-6" style="position: absolute;left: 0%;">
                            <input id="password" type="password" placeholder="Password..." name="password" class="form-control"pattern=".{8,16}" title="length should be between 8-16.">
                        </div>
                        <div class="col-sm-6"style="position: absolute;left: 50%;">
                            <input id="Confirm_password" type="password" placeholder="Confirm Password..." name="cPassword" class="form-control">
                        </div>                
                        <br><br><br>
                        <textarea name="address" placeholder="Address..." class="form form-control" pattern=".{3,500}" title="length should be between 3-500."></textarea>
                        <br>
                        <input id="Submit" type="Submit" value="SignUp" class="btn btn-success" style="width: 30%;" disabled>
                        <input type="reset" value="Clear All" class="btn btn-default" style="width: 30%">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
