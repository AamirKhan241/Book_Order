package purchaseOp;

import books.Books_DAO;
import books.Books_DAO_Implt;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import purchase.Purchase;
import purchase.Purchase_DAO;
import purchase.Purchase_DAO_Implt;
import purchaseReport.PurchaseReport;
import purchaseReport.PurchaseReport_DAO;
import purchaseReport.PurchaseReport_DAO_Implt;

@WebServlet(urlPatterns = "/Purchased")
public class Purchased extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Purchasing");
        boolean task=false;
        
        Books_DAO_Implt bdao=new Books_DAO_Implt();
        PurchaseReport_DAO prdao=new PurchaseReport_DAO_Implt();
        PurchaseReport pr=new PurchaseReport();
        Purchase p=new Purchase();

        pr.setSupplier_Id(Integer.valueOf(req.getSession().getAttribute("id").toString()));
        pr.setAmount_To_Pay(String.valueOf(Integer.valueOf(req.getParameter("copies")) * Integer.valueOf(req.getParameter("price"))));
        pr.setAmount_Paid(pr.getAmount_To_Pay());
        pr.setBalance("0");
        pr.setBook_ISBN(req.getParameter("isbn"));
        pr.setCopies(req.getParameter("copies"));
        pr.setPrice(req.getParameter("price"));
        if(prdao.insert(pr)){
            System.out.println("Purchase Report Generated...");
            pr=prdao.display(prdao.getNextID()-1);
            Purchase_DAO pdao=new Purchase_DAO_Implt();
            p=new Purchase();
            p.setAmount(pr.getAmount_To_Pay());
            p.setPurchase_Note_ID(prdao.getNextID()-1);
            p.setSupplier_ID(Integer.valueOf(req.getSession().getAttribute("id").toString()));
        
            if(pdao.insert(p)){
                System.out.println("Purchased Successfully");
                p=pdao.display(pdao.getNextId()-1);
                task=true;
            }else{
                System.out.println("Error in purchasing");
                prdao.delete(prdao.getNextID()-1);
                bdao.delete(bdao.getNextId()-1);
                task=false;
            }
        }else{
            System.out.println("Error in purchasing report...");
            bdao.delete(bdao.getNextId()-1);
            task=false;
        }
        
        if(task){
            PrintWriter out=resp.getWriter();
            out.println("<html><body><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" 
                +"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" 
                +"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" 
                +"<h2 style='text-color: green; text-align:center '>DONE</h2>"
                + "<center><a href='ViewPurchase.jsp?view=mine' class='btn btn-default'>View Record</a>"
                + "<a target='_blank' href='PurchaseNote.jsp?pNote="+pr.getPurchase_Note_Id()+"' class='btn btn-default' style='margin-left:2%'>Get Purchase Note</a><br>"
                        + "<a href='ViewBook.jsp' class='btn btn-default' >View Books</a></center></body></html>");
        }
    }    
}
