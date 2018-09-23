package cartOp;

import books.Books;
import books.Books_DAO;
import books.Books_DAO_Implt;
import cart.Cart;
import cart.Cart_DAO;
import cart.Cart_DAO_Implt;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/UpdateCart")
public class UpdateCart extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data[]=req.getParameter("id").split(",");
        
        Cart_DAO cdao=new Cart_DAO_Implt();
        Cart c=cdao.display(Integer.valueOf(data[1]));
        Books_DAO bdao=new Books_DAO_Implt();
        Books b=bdao.display(Integer.valueOf(data[2]));
        if(((c.getQuantity() == 1) && data[0].equals("min")) || (c.getQuantity() == Integer.valueOf(b.getCopies()) && data[0].equals("plus"))){    
            if((c.getQuantity() == 1) && data[0].equals("min")){
                cdao.delete(c.getSerial_no());
                System.out.println("Cart Updated");
                resp.sendRedirect("ViewCart.jsp");
            }
            else if((c.getQuantity() == Integer.valueOf(b.getCopies())) && data[0].equals("plus")){
                req.setAttribute("Message", "<center><div class='alert alert-danger' role='alert' style='width:50%;' >" +
                "  <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
                "  <strong>Warning!</strong> You reached the maximum number of copies!</div></center>"
            + "<script>window.setTimeout(function() {$('.alert').fadeTo(500, 0).slideUp(500, function(){$(this).remove();});}, 2000);</script>");
                RequestDispatcher rd=req.getRequestDispatcher("ViewCart.jsp");
                System.out.println("Cart Updated");
                rd.forward(req, resp);
            }
        }else{        
            if(data[0].equals("plus"))
                c.setQuantity(c.getQuantity()+1);
            else
                c.setQuantity(c.getQuantity()-1);
        
            if(cdao.update(c)){
                System.out.println("Cart Updated");
                resp.sendRedirect("ViewCart.jsp");
            }else{
                System.out.println("Error in updating Cart");
            }
        }
        
    }
    
    
    
}
