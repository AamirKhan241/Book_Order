package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Admin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function(){  \n");
      out.write("                var checkField;\n");
      out.write("                //checking the length of the value of message and assigning to a variable(checkField) on load\n");
      out.write("                checkField = $(\"input#password\").val().length;  \n");
      out.write("\n");
      out.write("                var enable = function(){         \n");
      out.write("                if(checkField > 0){\n");
      out.write("                    $('#Confirm_password').removeAttr(\"disabled\");\n");
      out.write("                $('#password, #Confirm_password').on('keyup', function () {\n");
      out.write("                    if (($('#password').val() == $('#Confirm_password').val())&& checkField != 0 ) \n");
      out.write("                        $('#Submit').removeAttr(\"disabled\");\n");
      out.write("                    else \n");
      out.write("                        $('#Submit').attr(\"disabled\",\"disabled\");\n");
      out.write("                });\n");
      out.write("                }else{\n");
      out.write("                    $('#Confirm_password').attr(\"disabled\",\"disabled\");\n");
      out.write("                }\n");
      out.write("                } \n");
      out.write("                //calling enableDisableButton() function on load\n");
      out.write("                enable();            \n");
      out.write("\n");
      out.write("                $('input#password').keyup(function(){ \n");
      out.write("                    //checking the length of the value of message and assigning to the variable(checkField) on keyup\n");
      out.write("                    checkField = $(\"input#password\").val().length;\n");
      out.write("                    //calling enableDisableButton() function on keyup\n");
      out.write("                    enable();\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("        <style>\n");
      out.write("            .data{\n");
      out.write("                background-color: #ccccff; \n");
      out.write("                width: 40%; \n");
      out.write("                height: 50vh; \n");
      out.write("                position: absolute; \n");
      out.write("                top: 25%; \n");
      out.write("                left: 30%; \n");
      out.write("                border-radius: 10%; \n");
      out.write("            }   \n");
      out.write("            #LogIn{\n");
      out.write("                background-image: url('Images/AdminLogin.png'); \n");
      out.write("                background-size: 100% 40vh;\n");
      out.write("                background-repeat: no-repeat;\n");
      out.write("                height: 39vh; \n");
      out.write("                border-radius: 10%;\n");
      out.write("                width: 100%; \n");
      out.write("                margin-top: 2vh;\n");
      out.write("            }\n");
      out.write("            body{\n");
      out.write("                background-image: url('Images/AdminBack.jpg');\n");
      out.write("                background-repeat: no-repeat;\n");
      out.write("                background-size: 100% 100vh;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body id=\"myPage\">\n");
      out.write("    <center><b><i style=\"font-size: 100px; color: whitesmoke; font-family: fantasy\">The Administrator</i></b></center>\n");
      out.write("        <div class=\"container data\">\n");
      out.write("            <br/>\n");
      out.write("            <ul class=\"nav nav-tabs nav-justified\" >\n");
      out.write("                <li class=\"active\"><a data-toggle=\"tab\" href=\"#LogIn\">LogIn</a></li>\n");
      out.write("                <li><a data-toggle=\"tab\" href=\"#SignUp\">SignUp</a></li>\n");
      out.write("            </ul>\n");
      out.write("\n");
      out.write("            <div class=\"tab-content\" >\n");
      out.write("                <div id=\"LogIn\" class=\"tab-pane fade in active\">\n");
      out.write("                    <form action=\"LogIn\" method=\"post\">\n");
      out.write("                        <br><br>\n");
      out.write("                        <label for=\"uName\"><b>Username</b></label>\n");
      out.write("                        <div class=\"input-group\">\n");
      out.write("                            <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-user\"></i></span>\n");
      out.write("                            <input class=\"form-control\" type=\"email\" placeholder=\"Enter Username\" name=\"uName\" required style=\"opacity: .7\">\n");
      out.write("                        </div>\n");
      out.write("                        <br><br>\n");
      out.write("                        <label for=\"uPass\"><b>Password</b></label>\n");
      out.write("                        <div class=\"input-group\">\n");
      out.write("                            <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-lock\"></i></span>\n");
      out.write("                            <input class=\"form form-control\" type=\"password\" placeholder=\"Enter Password\" name=\"uPass\" required style=\"opacity: .7\">\n");
      out.write("                        </div>\n");
      out.write("                        <br>\n");
      out.write("                        <center><input class=\"btn btn-success\" type=\"submit\" style=\"width:20%; left: 40%; bottom: 10px; position: absolute\" value=\"LogIn\"></center>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"SignUp\" class=\"tab-pane fade\">\n");
      out.write("                    <form action=\"SignUp?role=ADMINISTRATOR\" method=\"post\" style=\"text-align: center\">\n");
      out.write("                        <br>\n");
      out.write("                        <div class=\"col-sm-6\" style=\"position: absolute;left: 0%;\">\n");
      out.write("                            <input type=\"text\" placeholder=\"First Name...\" name=\"fName\" class=\"form-control\" pattern=\".{3,20}\" title=\"length should be between 3-20.\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-sm-6\"style=\"position: absolute;left: 50%;\">\n");
      out.write("                            <input type=\"text\" placeholder=\"Last Name...\" name=\"lName\" class=\"form-control\" pattern=\".{3,20}\" title=\"length should be between 3-20.\">\n");
      out.write("                        </div>                \n");
      out.write("                        <br><br><br>\n");
      out.write("                        \n");
      out.write("                        <div class=\"col-sm-6\" style=\"position: absolute;left: 0%;\">\n");
      out.write("                            <input type=\"email\" placeholder=\"Email...\" name=\"email\" class=\"form-control\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-sm-6\"style=\"position: absolute;left: 50%;\">\n");
      out.write("                            <input type=\"number\" placeholder=\"Contact Number...\" name=\"contact\" class=\"form-control\" pattern=\".{10,10}\" title=\"length should be 10.\">\n");
      out.write("                        </div>                \n");
      out.write("                        <br><br><br>\n");
      out.write("                        <div class=\"col-sm-6\" style=\"position: absolute;left: 0%;\">\n");
      out.write("                            <input id=\"password\" type=\"password\" placeholder=\"Password...\" name=\"password\" class=\"form-control\"pattern=\".{8,16}\" title=\"length should be between 8-16.\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-sm-6\"style=\"position: absolute;left: 50%;\">\n");
      out.write("                            <input id=\"Confirm_password\" type=\"password\" placeholder=\"Confirm Password...\" name=\"cPassword\" class=\"form-control\">\n");
      out.write("                        </div>                \n");
      out.write("                        <br><br><br>\n");
      out.write("                        <textarea name=\"address\" placeholder=\"Address...\" class=\"form form-control\" pattern=\".{3,500}\" title=\"length should be between 3-500.\"></textarea>\n");
      out.write("                        <br>\n");
      out.write("                        <input id=\"Submit\" type=\"Submit\" value=\"SignUp\" class=\"btn btn-success\" style=\"width: 30%;\" disabled>\n");
      out.write("                        <input type=\"reset\" value=\"Clear All\" class=\"btn btn-default\" style=\"width: 30%\">\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
