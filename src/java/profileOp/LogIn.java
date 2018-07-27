package profileOp;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import profile.Profiles;
import profile.Profiles_DAO;
import profile.Profiles_DAO_Implt;

@WebServlet(urlPatterns = "/LogIn")
public class LogIn extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean log=false;
        
        Profiles_DAO pdao=new Profiles_DAO_Implt();
            
            req.getSession().removeAttribute("id");
            req.getSession().removeAttribute("user");
            req.getSession().removeAttribute("password");
            req.getSession().removeAttribute("role");
            req.getSession().removeAttribute("name");
            req.getSession().removeAttribute("status");

        List<Profiles> list=pdao.display();
        
        for (Profiles profile : list) {
            if(profile.getEmail().equalsIgnoreCase(req.getParameter("uName")) && profile.getPassword().equals(req.getParameter("uPass"))){
                log=true;             
                if(profile.getStatus().equalsIgnoreCase("active")){   
                    System.out.println("access");
                    req.getSession().setAttribute("id", profile.getId());
                    req.getSession().setAttribute("user", profile.getEmail());
                    req.getSession().setAttribute("password", profile.getPassword());
                    req.getSession().setAttribute("role", profile.getRole());
                    req.getSession().setAttribute("name", " "+singleCopy.Sentence.conv(profile.getFirst_Name()));
                    req.getSession().setAttribute("status", "active");
                    break;
                }
                else{
                    req.setAttribute("error", "<script>alert('Your account in currently in active. Please request admin to make you active by placing request in CONTACT US section.');</script>");
                    RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
                    rd.forward(req, resp);
                }
            }
        }
        
        if(log){
            System.out.println("LogIn Granted");    
//            RequestDispatcher rd=req.getRequestDispatcher(req.getParameter("ReqPage"));
//            rd.forward(req, resp);
            resp.sendRedirect("index.jsp");
        }else{
            System.out.println("LogIn Denied");
//            req.setAttribute("ReqPage", req.getParameter("ReqPage"));
//            RequestDispatcher rd=req.getRequestDispatcher("Unauthorised.jsp");
//            rd.forward(req, resp);
            resp.sendRedirect("Unauthorised.jsp");
        }        
    }    

}
