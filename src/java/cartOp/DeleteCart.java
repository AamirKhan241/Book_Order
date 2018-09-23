package cartOp;

import cart.Cart_DAO;
import cart.Cart_DAO_Implt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DeleteCart")
public class DeleteCart extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Cart_DAO cdao=new Cart_DAO_Implt();
        if(cdao.delete(Integer.valueOf(req.getParameter("Sno")))){
            System.out.println("Deleted");
            resp.sendRedirect("ViewCart.jsp");
        }else{
            System.out.println("Error in deleting");
        }
    }    
}
