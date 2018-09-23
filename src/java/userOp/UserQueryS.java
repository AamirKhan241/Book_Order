package userOp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import userQuery.UserQuery_DAO;
import userQuery.UserQuery_DAO_Implt;
import userQuery.UserQuery;

@WebServlet(urlPatterns = "/UserQuery")
public class UserQueryS extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        UserQuery_DAO udao=new UserQuery_DAO_Implt();
        UserQuery u=new  UserQuery();
        u.setName(req.getParameter("Qname"));
        u.setEmail(req.getParameter("Qemail"));
        u.setSubject(req.getParameter("Qsubject"));
        u.setMessage(req.getParameter("Qmessage"));
        if(udao.insert(u)){
            System.out.println("Request Posted");
            resp.sendRedirect("index.jsp");
        }else{
            System.out.println("Error in requesting");
        }
    }
    
    
}
