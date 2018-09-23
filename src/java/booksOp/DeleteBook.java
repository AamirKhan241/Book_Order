package booksOp;

import books.Books_DAO;
import books.Books_DAO_Implt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DeleteBook")
public class DeleteBook extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Books_DAO bdao=new Books_DAO_Implt();
        if(bdao.delete(Integer.valueOf(req.getParameter("Sno")))){
            System.out.println("Book Deleted Successfully...");
            resp.sendRedirect("ViewBook.jsp");
        }else{
            System.out.println("Error in deleting Book...");
        }
    }
    
    
    
}
