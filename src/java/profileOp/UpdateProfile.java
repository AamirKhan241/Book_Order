package profileOp;

import singleCopy.Mail;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import profile.*;

@WebServlet(urlPatterns = "/UpdateProfile")
public class UpdateProfile extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Profiles p=new Profiles();
        p.setId(Integer.valueOf(req.getParameter("id")));        
        p.setFirst_Name(req.getParameter("UfName"));
        p.setLast_Name(req.getParameter("UlName"));
        p.setRole(req.getParameter("Urole"));
        p.setEmail(req.getParameter("Uemail"));
        p.setPassword(req.getParameter("Upassword"));
        p.setContact(req.getParameter("Ucontact"));
        p.setAddress(req.getParameter("Uaddress"));
        
        Profiles_DAO pdao=new Profiles_DAO_Implt();
        
        if(pdao.update(p)){
            System.out.println("Updated Successfully");
            resp.sendRedirect("ViewProfiles.jsp");
            Mail mail=new Mail();
            mail.sentMail(p.getEmail(), "Profile Updated", p.getFirst_Name()+" "+p.getLast_Name()+",<br><br>Your Profile has been update.<br>Continue with updated information.<br><br>Thank You.");
        }
        else
            System.out.println("Error in Updation");
    }
    
}
