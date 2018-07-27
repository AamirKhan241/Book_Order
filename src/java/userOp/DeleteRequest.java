package userOp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import userQuery.UserQuery_DAO;
import userQuery.UserQuery_DAO_Implt;

@WebServlet(urlPatterns = "/DeleteRequest")
public class DeleteRequest extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserQuery_DAO udao=new UserQuery_DAO_Implt();
        
        if(udao.delete(Integer.valueOf(req.getParameter("sNo")))){
            System.out.println("Deleted");
            resp.sendRedirect("ViewUserQuery.jsp");
        }else{
            System.out.println("Error in deleting");
        }
    }
    
    
}
