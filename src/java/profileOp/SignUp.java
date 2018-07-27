package profileOp;

import singleCopy.Mail;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
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

@WebServlet(urlPatterns = "/SignUp")
public class SignUp extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean st=false;
        Profiles p=new Profiles();
        System.out.println("adding profile");
        p.setFirst_Name(req.getParameter("fName").toUpperCase());
        p.setLast_Name(req.getParameter("lName").toUpperCase());
        p.setRole(req.getParameter("role").toUpperCase());
        p.setEmail(req.getParameter("email").toUpperCase());
        p.setPassword(req.getParameter("password"));
        p.setContact(req.getParameter("contact"));
        p.setAddress(req.getParameter("address").toUpperCase());
        
        Profiles_DAO pdao=new Profiles_DAO_Implt();
        if(pdao.insert(p)){
            System.out.println("Profile added successfully");
            p=pdao.displayE(p.getEmail());
            if(req.getParameter("role").equalsIgnoreCase("Supplier")){
                System.out.println("Supplier found");
                System.out.println("\n\nNow adding Publisher");
                Publisher pub=new Publisher();
                pub.setName(req.getParameter("Publisher"));
                pub.setSupplier_Id(p.getId());
                    
                Publisher_DAO pubdao=new Publisher_DAO_Implt();
                if(pubdao.insert(pub)){
                    System.out.println("Publisher Added Successfully");
                    pub=pubdao.display(pubdao.getNextId()-1);
                    System.out.println("now Adding Supplier");
                    Supplier s=new Supplier();
                    s.setEmail(p.getEmail());
                    s.setSID(p.getId());
                    s.setPublisher(pub.getId());
                    Supplier_DAO sdao=new Supplier_DAO_Implt();
                    if(sdao.insert(s)){
                        System.out.println("Supplier Added Successfully");
                        Mail obj=new Mail();
                        obj.sentMail(p.getEmail(),"Registered Successfully", p.getFirst_Name()+" "+p.getLast_Name()+",<br><br>For username: '"+p.getEmail()+"' and password: '"+p.getPassword()+"' Your account is successfully created with us as '"+req.getParameter("role")+"'.<br> Enjoy our services.<br>Thank You");
                        st=true;                        
                    }else{
                        System.out.println("Error in adding Supplier");
                        st=false;
                        pubdao.delete(pub.getId());
                        pdao.delete(p.getId());
                    }
                }else{
                    System.out.println("Error in adding Publisher");
                    st=false;
                    pdao.delete(p.getId());
                }                
            }else{            
                Mail obj=new Mail();
                obj.sentMail(p.getEmail(),"Registered Successfully", p.getFirst_Name()+" "+p.getLast_Name()+",<br><br>For username: '"+p.getEmail()+"' and password: '"+p.getPassword()+"' Your account is successfully created with us as '"+req.getParameter("role")+"'.<br> Enjoy our services.<br>Thank You");
                st=true;
            }
        }else{
            st=false;
            req.setAttribute("Message","<script>alert('"+pdao.getMessage()+"');</script>");
            RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);
            System.out.println("Error in adding profile");
        }
        
        if(st){
//LOGGING IN    
        
        req.getSession().removeAttribute("id");
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("password");
        req.getSession().removeAttribute("role");
        req.getSession().removeAttribute("name");
        req.getSession().removeAttribute("status");
            
        System.out.println("Logging INNN");
        
        req.getSession().setAttribute("id", p.getId());
            req.getSession().setAttribute("user", p.getEmail());
            req.getSession().setAttribute("password", p.getPassword());
            req.getSession().setAttribute("role", p.getRole());
            req.getSession().setAttribute("name", " "+singleCopy.Sentence.conv(p.getFirst_Name()));
            req.getSession().setAttribute("status", "active");
            resp.sendRedirect("index.jsp");
        }
        
        
    }
}
