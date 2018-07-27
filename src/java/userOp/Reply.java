package userOp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import singleCopy.Mail;
import userQuery.UserQuery;
import userQuery.UserQuery_DAO;
import userQuery.UserQuery_DAO_Implt;

@WebServlet(urlPatterns = "/Reply")
public class Reply extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mail m=new Mail();
        
        UserQuery_DAO udao=new UserQuery_DAO_Implt();
        UserQuery u=udao.display(Integer.valueOf(req.getParameter("sNo")));
        String reply=u.getReply();
        u.setReply(req.getParameter("RMessage"));
        if(udao.update(u)){
            System.out.println("Updated");
            if(m.sentMail(req.getParameter("Remail"), "Reply for your Request", req.getParameter("RMessage"))){
                System.out.println("Mail send Successfully");
                resp.sendRedirect("ViewUserQuery.jsp");
            }else{
                System.out.println("Error in sending mail");
                u.setReply(reply);
                udao.update(u);
            }
        }else{
            System.out.println("Error in updating");
            u.setReply(reply);
            udao.update(u);
        }
    }
    
    
}
