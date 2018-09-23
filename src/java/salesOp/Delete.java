package salesOp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sale.Sale_DAO;
import sale.Sale_DAO_Implt;
import salesReport.SalesReport_DAO;
import salesReport.SalesReport_DAO_Implt;


@WebServlet(urlPatterns = "/DeleteOrder")
public class Delete extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sale_DAO sdao=new Sale_DAO_Implt();
        
        if(sdao.deleteI(Integer.valueOf(req.getParameter("Invoice").toString()))){
            System.out.println("sale deleted");
            SalesReport_DAO srdao=new SalesReport_DAO_Implt();
            if(srdao.delete(Integer.valueOf(req.getParameter("Invoice").toString()))){
                System.out.println("Order deleted");
                resp.sendRedirect("ViewOrder.jsp?view="+req.getParameter("add"));
            }else{
                System.out.println("Error in deleting Order sale report");
            }
        }else{
            System.out.println("Error in deleting Order sale");
        }
    }

    
}