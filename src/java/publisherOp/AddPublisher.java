package publisherOp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import publisher.Publisher;
import publisher.Publisher_DAO;
import publisher.Publisher_DAO_Implt;

@WebServlet(urlPatterns = "/AddPublisher")
public class AddPublisher extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Publisher_DAO pdao=new Publisher_DAO_Implt();
        Publisher p=new Publisher();
        
        p.setSupplier_Id(Integer.valueOf(req.getSession().getAttribute("id").toString()));
        p.setName(req.getParameter("pName"));
        
        if(pdao.insert(p)){
            System.out.println("Publisher Added Successfully");
            resp.sendRedirect("ViewPublisher.jsp");
        }else{
            System.out.println("Error in adding Publisher");
        }
    }
}
