package publisherOp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import publisher.Publisher_DAO;
import publisher.Publisher_DAO_Implt;

@WebServlet(urlPatterns = "/DeletePublisher")
public class DeletePublisher extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Publisher_DAO pdao=new Publisher_DAO_Implt();
        
        if(pdao.deleteS(Integer.valueOf(req.getParameter("id")))){
            System.out.println("Publisher deleted");
            resp.sendRedirect("ViewPublisher.jsp");
        }else{
            System.out.println("Error in deleting Publisher");
        }
    }
    
    
    
}
