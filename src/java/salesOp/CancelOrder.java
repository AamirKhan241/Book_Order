package salesOp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import salesReport.SalesReport;
import salesReport.SalesReport_DAO;
import salesReport.SalesReport_DAO_Implt;

@WebServlet(urlPatterns = "/CancelOrder")
public class CancelOrder extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Cancelling");
        
        SalesReport_DAO srdao=new SalesReport_DAO_Implt();
        SalesReport sr=srdao.display(Integer.valueOf(req.getParameter("Invoice")));
        if(sr.getStatus().equals("Order Cancelled")){
            req.setAttribute("error", "<script>alert('Order Already cancelled');</script>");
            RequestDispatcher rd=req.getRequestDispatcher("ViewOrder.jsp?view="+req.getParameter("add"));
            rd.forward(req, resp);
        }else{
            sr.setStatus("Order Cancelled");

            if(srdao.update(sr)){
                System.out.println("Order Canceled successfully");
                resp.sendRedirect("ViewOrder.jsp?view="+req.getParameter("add"));                            
            }else{
                System.out.println("Error in Canceling order");
            }
        }
    }
}
