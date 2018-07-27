package profileOp;

import singleCopy.Mail;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import profile.Profiles;
import profile.Profiles_DAO;
import profile.Profiles_DAO_Implt;

@WebServlet(urlPatterns = "/Status")
public class ChangeStatus extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Profiles_DAO pdao=new Profiles_DAO_Implt();
        Profiles p=pdao.display(Integer.valueOf(req.getParameter("id")));
        if(req.getParameter("delete").equals("no")){        
            String status=p.getStatus();
            if(pdao.changeStatus(Integer.valueOf(req.getParameter("id")))){
                System.out.println("Status Changed");
                Mail mail=new Mail();
                p=pdao.display(Integer.valueOf(req.getParameter("id")));
                mail.sentMail(p.getEmail(),"Status Update" ,p.getFirst_Name()+" "+p.getLast_Name()+",<br><br>Your status for '"+p.getEmail().toLowerCase()+"' has been changed from '"+status+"' to '"+p.getStatus()+"'.<br>Thank You");
            }
            else
                System.out.println("Error in changing Status");
        }else{
            if(pdao.changeStatus(Integer.valueOf(req.getParameter("id")))){
                System.out.println("Status Changed");
                Mail mail=new Mail();
                p=pdao.display(Integer.valueOf(req.getParameter("id")));
                mail.sentMail(p.getEmail(),"Account Deleted" ,p.getFirst_Name()+" "+p.getLast_Name()+",<br><br>Your Account '"+p.getEmail().toLowerCase()+"' has been deleted with us.<br>Thank You");
                req.getSession().removeAttribute("id");
                req.getSession().removeAttribute("user");
                req.getSession().removeAttribute("password");
                req.getSession().removeAttribute("role");
                req.getSession().removeAttribute("name");
                req.getSession().removeAttribute("status");

            }
            else
                System.out.println("Error in Deleting status change");
        }
        resp.sendRedirect("ViewProfiles.jsp");
    }
}
