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
import publisher.Publisher;
import publisher.Publisher_DAO;
import publisher.Publisher_DAO_Implt;
import supplier.Supplier;
import supplier.Supplier_DAO;
import supplier.Supplier_DAO_Implt;

@WebServlet(urlPatterns = "/DeleteProfile")
public class DeleteProfile extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Profiles_DAO pdao= new  Profiles_DAO_Implt();
        Profiles p=pdao.display(Integer.valueOf(req.getParameter("id")));
        if(p.getRole().equalsIgnoreCase("supplier")){
            Supplier_DAO sdao=new Supplier_DAO_Implt();
            System.out.println("Deleting Supplier");
            Supplier s=sdao.display(p.getId());
            
            if(sdao.deleteS(p.getId())){
                System.out.println("Supplier deleted");
                Publisher_DAO pubdao=new Publisher_DAO_Implt();
                Publisher pub=pubdao.display(s.getPublisher());
                System.out.println("deleting publisher");
                
                if(pubdao.delete(s.getPublisher())){
                    System.out.println("Publisher deleted");
                    
                    if(pdao.delete(p.getId())){
                        System.out.println("Profile deleted");
                        Mail obj=new Mail();
                        obj.sentMail(p.getEmail(),"Profile Deleted", p.getFirst_Name()+" "+p.getLast_Name()+",<br><br>For username: '"+p.getEmail()+"' Your account is successfully deleted with us.<br>Thans for being with us.");
                        resp.sendRedirect("ViewProfiles.jsp");
                    }else{
                        System.out.println("Error in deleting profile");
                        pubdao.insert(pub);
                        sdao.insert(s);
                    }
                    
                }else{
                    System.out.println("Error in deleting Publisher");
                    sdao.insert(s);
                }   
                
            }else{
                System.out.println("Error in deleting Supplier");
            }            
        }else{
            if(pdao.delete(p.getId())){
                System.out.println("Profile delelted successfully");
                Mail obj=new Mail();
                obj.sentMail(p.getEmail(),"Profile Deleted", p.getFirst_Name()+" "+p.getLast_Name()+",<br><br>For username: '"+p.getEmail()+"' Your account is successfully deleted with us.<br>Thans for being with us.");
                resp.sendRedirect("ViewProfiles.jsp");
            }
        }
    }
}
