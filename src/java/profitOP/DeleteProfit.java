package profitOP;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import profit.Profit_DAO;
import profit.Profit_DAO_Implet;

@WebServlet(urlPatterns = "/DeleteProfit")
public class DeleteProfit extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Profit_DAO pdao=new Profit_DAO_Implet();
        if(pdao.delete(Integer.valueOf(req.getParameter("Sno"))))
        {
            System.out.println("Profit Statement Deleted...");
            resp.sendRedirect("ViewProfit.jsp");
        }else{
            System.out.println("Error in deleting Profit Statement");
        }
    }
    
    
}
