package salesOp;

import books.Books;
import books.Books_DAO;
import books.Books_DAO_Implt;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import profit.Profit;
import profit.Profit_DAO;
import profit.Profit_DAO_Implet;
import sale.Sale;
import sale.Sale_DAO;
import sale.Sale_DAO_Implt;
import salesReport.SalesReport;
import salesReport.SalesReport_DAO;
import salesReport.SalesReport_DAO_Implt;

@WebServlet(urlPatterns = "/UpdateOrder")
public class UpdateOrder extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        System.out.println("Updating");
        
        SalesReport_DAO srdao=new SalesReport_DAO_Implt();
        SalesReport sr=srdao.display(Integer.valueOf(req.getParameter("invoice").toString()));
        
        sr.setStatus(req.getParameter("status"));
        sr.setAmountPaid(req.getParameter("amountpaid"));
        sr.setBalance(req.getParameter("balance"));
        
        if(srdao.update(sr)){
            System.out.println("Order Updated");
            if(Integer.valueOf(sr.getBalance()) == 0)
            {
                if(sr.getStatus().equals("Delivered")){
                    Sale_DAO sdao=new Sale_DAO_Implt();
                    Books_DAO bdao=new Books_DAO_Implt();


                    List<Sale> list=sdao.displayI(sr.getInvoice());
                    Profit profit=new Profit();
                    int prof=0;
                    for (Sale s : list) {
                        List<Books> blist=bdao.display(s.getISBN());
                        for (Books books : blist) {
                            prof+=(Integer.valueOf(books.getPrice()) - ((Integer.valueOf(books.getPrice())*10)/11));
                        }
                        profit.setSaleBY(s.getCustomer_id());
                        profit.setSaleFROM(s.getSupplier_id());
                    }

                    profit.setAmount(String.valueOf(prof));
                    Profit_DAO prodao=new Profit_DAO_Implet();
                    if(prodao.insert(profit)){
                        System.out.println("Profilt Calculated");
                    }else{
                        System.out.println("Error in calculating profit");
                    }
                }
            }
            resp.sendRedirect("ViewOrder.jsp?view=all");
        }else{
            System.out.println("Error in updating Order");
        }
    }
    
}
