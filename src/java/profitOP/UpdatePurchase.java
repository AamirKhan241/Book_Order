package profitOP;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import purchaseReport.PurchaseReport;
import purchaseReport.PurchaseReport_DAO;
import purchaseReport.PurchaseReport_DAO_Implt;

@WebServlet(urlPatterns = "/UpdatePurchase")
public class UpdatePurchase extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PurchaseReport_DAO prdao=new PurchaseReport_DAO_Implt();
        PurchaseReport pr=prdao.display(Integer.valueOf(req.getParameter("id")));
        System.out.println(",,,");
        pr.setAmount_Paid(req.getParameter("amountpaid"));
                System.out.println(",,,");

        pr.setBalance(String.valueOf(Integer.valueOf(pr.getAmount_To_Pay())-Integer.valueOf(pr.getAmount_Paid())));
        System.out.println(",,,");
        
        if(prdao.update(pr)){
            System.out.println("Purchase Record Updated Successfully");
            resp.sendRedirect("ViewPurchase.jsp?view=all");
        }else{
            System.out.println("Error in updating purchase Record");
        }
        
    }
    
    
}
