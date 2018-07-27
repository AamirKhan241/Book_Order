package salesOp;

import books.Books;
import books.Books_DAO;
import books.Books_DAO_Implt;
import cart.Cart;
import cart.Cart_DAO;
import cart.Cart_DAO_Implt;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import profile.Profiles_DAO;
import profile.Profiles_DAO_Implt;
import publisher.Publisher_DAO;
import publisher.Publisher_DAO_Implt;
import sale.Sale;
import sale.Sale_DAO;
import sale.Sale_DAO_Implt;
import salesReport.SalesReport;
import salesReport.SalesReport_DAO;
import salesReport.SalesReport_DAO_Implt;
import singleCopy.Mail;

@WebServlet(urlPatterns = "/PlaceOrder")
public class PlaceOrder extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SalesReport_DAO srdao=new SalesReport_DAO_Implt();
        Cart_DAO cdao=new Cart_DAO_Implt();
        Sale_DAO sdao=new Sale_DAO_Implt();
                
        SalesReport sr;
        Sale s;
        boolean task=false;
        if(req.getParameter("page").equals("ViewCart.jsp")){
            System.out.println("Cart Recieved");
            sr=new SalesReport();
            sr.setcID(Integer.parseInt(req.getSession().getAttribute("id").toString()));
            sr.setTransMode(req.getParameter("mode"));
            sr.setAmountToPay(req.getParameter("dAmt"));
            sr.setrName(req.getParameter("dName"));
            sr.setrAddress(req.getParameter("dAddress"));
            sr.setrContact(req.getParameter("dContact"));
            sr.setDescription(req.getParameter("Description"));
            
            if(req.getParameter("mode").equals("COD")){
                sr.setAmountPaid("0");
                sr.setBalance(sr.getAmountToPay());
            }else{
                sr.setAmountPaid(sr.getAmountToPay());
                sr.setBalance("0");
            }
            
            if(srdao.insert(sr)){
                System.out.println("Sales Report Generated");
                sr=srdao.display(srdao.getNextInvoice()-1);
                
                int id[]=new int[cdao.item(sr.getcID())],count=0;
                
                List<Cart> list=cdao.displayC(sr.getcID());
                s=new Sale();
                
                s.setInvoice(sr.getInvoice());
                s.setCustomer_id(sr.getcID());
                
                Books_DAO bdao=new Books_DAO_Implt();
                List<Books> b;
                for (Cart cart : list) {
                    b=bdao.display(cart.getBook_id());
                    s.setISBN(cart.getBook_id());
                    for (Books books : b) {
                        s.setCopies(String.valueOf(cart.getQuantity()));
                        s.setAmount(String.valueOf(cart.getQuantity() * Integer.valueOf(books.getPrice())));
                        Publisher_DAO pub=new Publisher_DAO_Implt();
                        s.setSupplier_id(pub.displaySi(books.getPublisher()).getSupplier_Id());
                        
                        if(sdao.insert(s)){
                            System.out.println("Sale generated");
                            id[count++]=sdao.getNextId()-1;
                            
                            books.setCopies(String.valueOf(Integer.valueOf(books.getCopies()) - Integer.valueOf(s.getCopies())));
                            bdao.update(books);
                            cdao.delete(cart.getSerial_no());
                            task=true;
                            if(Integer.valueOf(books.getCopies()) <=10){
                                Mail m=new Mail();
                                Profiles_DAO pdao=new Profiles_DAO_Implt();
                                if(m.sentMail(pdao.display(s.getSupplier_id()).getEmail(), "FEW ITEMS LEFT", pdao.display(s.getSupplier_id()).getFirst_Name()+
                                        ",<br>    We inform you that only "+books.getCopies()
                                        + " copies are left of book with book isbn '"+books.getISBN()+"'.\n Kindly, add more books. <br>Thank You;"))
                                    System.out.println("Mail Send");
                                else
                                    System.out.println("Error in sending mail");
                            }
                        }else{
                            System.out.println("Error in generating sale");
                            for (int i : id) {
                                sdao.delete(i);
                            }
                            srdao.delete(sr.getInvoice());
                            task=false;
                        }
                        break;
                    }
                }
            }else{
                System.out.println("Error in generating Sales Report.");
                task=false;
            }
            if(!task){
                System.out.println("Error in generating Sales Report.");
            }
        }else{
            Books_DAO bdao=new Books_DAO_Implt();
            Books b=bdao.display(Integer.valueOf(req.getParameter("sNo")));
            sr=new SalesReport();
            sr.setcID(Integer.parseInt(req.getSession().getAttribute("id").toString()));
            sr.setTransMode(req.getParameter("mode"));
            sr.setAmountToPay(req.getParameter("dAmt"));
            sr.setrName(req.getParameter("dName"));
            sr.setrAddress(req.getParameter("dAddress"));
            sr.setrContact(req.getParameter("dContact"));
            sr.setDescription(req.getParameter("Description"));
            
            if(req.getParameter("mode").equals("COD")){
                sr.setAmountPaid("0");
                sr.setBalance(sr.getAmountToPay());
            }else{
                sr.setAmountPaid(sr.getAmountToPay());
                sr.setBalance("0");
            }
            
            if(srdao.insert(sr)){
                System.out.println("Sales Report Generated");
                sr=srdao.display(srdao.getNextInvoice()-1);

                s=new Sale();
                s.setCustomer_id(Integer.valueOf(req.getSession().getAttribute("id").toString()));
                s.setAmount(req.getParameter("dAmt"));
                s.setInvoice(sr.getInvoice());
                s.setISBN(b.getISBN());
                s.setCopies(req.getParameter("dquantity"));
                Publisher_DAO pub=new Publisher_DAO_Implt();
                s.setSupplier_id(pub.displaySi(b.getPublisher()).getSupplier_Id());

                if(sdao.insert(s)){
                    System.out.println("Sale added successfully");                   
                    System.out.println("Profit Calculated");
                    b.setCopies(String.valueOf(Integer.valueOf(b.getCopies()) - Integer.valueOf(s.getCopies())));
                    bdao.update(b);
                    task=true;
                    if(Integer.valueOf(b.getCopies()) <=10){
                                Mail m=new Mail();
                                Profiles_DAO pdao=new Profiles_DAO_Implt();

                                if(m.sentMail(pdao.display(s.getSupplier_id()).getEmail(), "FEW ITEMS LEFT", pdao.display(s.getSupplier_id()).getFirst_Name()+",<br> We inform you that only "+b.getCopies()+""
                                        + " copies are left of book with book isbn '"+b.getISBN()+"'. Kindly, add more books.<br> Thank You;"))
                                    System.out.println("Mail Send");
                                else
                                    System.out.println("Error in sending mail");
                    }
                }else{
                    System.out.println("Error in adding sale");
                    srdao.delete(sr.getInvoice());
                    task=false;
                }
            }else{
                System.out.println("Error in generating Sales Report.");
                task=false;
            }
        }
        if(task){
            PrintWriter out=resp.getWriter();
            out.println("<html><body><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" 
                +"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" 
                +"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" 
                +"<h2 style='text-color: green; text-align:center '>ORDER PLACED SUCCESSFULLY !! </h2>"
                + "<center><a href='ViewOrder.jsp?view=mine' class='btn btn-default'>View Orders</a>"
                + "<a target='_blank' href='Invoice.jsp?Invoice="+sr.getInvoice()+"' class='btn btn-default' style='margin-left:2%'>Get Invoice</a></center></body></html>");
        }
    }
}