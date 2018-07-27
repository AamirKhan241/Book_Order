package booksOp;

import books.Books;
import books.Books_DAO;
import books.Books_DAO_Implt;
import singleCopy.ImageUpload;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AddBook")
@MultipartConfig(fileSizeThreshold = 5*1024*1024,maxFileSize = 20*1024*1024,maxRequestSize = 50*1024*1024)
public class AddBook extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Books_DAO bdao=new Books_DAO_Implt();
        Books b=new Books();
        b.setName(req.getParameter("bName"));
        b.setISBN(req.getParameter("isbn"));
        b.setPublisher(req.getParameter("publisher"));
        b.setStore_Location(req.getParameter("location"));
        b.setCopies(req.getParameter("copies"));
        b.setCategory(req.getParameter("category"));
        b.setEdition(req.getParameter("edition"));
        b.setPrice(String.valueOf((int)(Integer.valueOf(req.getParameter("price"))*(1.1))));
        b.setAuthor(req.getParameter("author"));
        b.setAuthor(req.getParameter("author"));
        
        b.setImage(singleCopy.ImageUpload.getInstance().uploadImage(req.getPart("img"), this.getServletContext().getRealPath("/")));
        
        if(bdao.insert(b)){
            System.out.println("Book Added Successfully...");
            RequestDispatcher rd=req.getRequestDispatcher("Purchased");
            rd.forward(req, resp);
        }else{
            System.out.println("Error in Adding Book...");
        }
    }
}
