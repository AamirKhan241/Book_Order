package cartOp;

import cart.Cart;
import cart.Cart_DAO;
import cart.Cart_DAO_Implt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import profile.Profiles_DAO;
import profile.Profiles_DAO_Implt;

@WebServlet(urlPatterns = "/AddToCart")
public class AddToCart extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Adding To Cart");
        Cart_DAO cdao=new Cart_DAO_Implt();
        Profiles_DAO pdao=new Profiles_DAO_Implt();
        Cart c=new Cart();
        
        c.setBook_id(req.getParameter("ISBN"));
        c.setQuantity(Integer.valueOf(req.getParameter("quant")));
        c.setcID(pdao.displayE(req.getSession().getAttribute("user").toString()).getId());
        
        if(cdao.insert(c)){
            System.out.println("Cart Updated");
            resp.sendRedirect("ViewCart.jsp");
        }else{
            System.out.println("Error in updating cart");
        }
    }
}
