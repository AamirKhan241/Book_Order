package booksOp;

import books.Books;
import books.Books_DAO;
import books.Books_DAO_Implt;
import singleCopy.ImageUpload;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/UpdateBook")
@MultipartConfig(fileSizeThreshold = 5*1024*1024,maxFileSize = 50*1024*1024,maxRequestSize = 5*1024*1024)
public class UpdateBook extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Books_DAO bdao=new Books_DAO_Implt();
        Books b=new Books();
        if(req.getPart("img").getInputStream().available() == 0 ){
            System.out.println("Image Not changed");
            b.setImage(bdao.display(Integer.valueOf(req.getParameter("Sno"))).getImage());
        }else{
            System.out.println("Image Changed");
            b=new Books();
            
            b.setImage(singleCopy.ImageUpload.getInstance().uploadImage(req.getPart("img"), this.getServletContext().getRealPath("/")));
        }
        
        b.setSerial_No(Integer.valueOf(req.getParameter("Sno")));
        b.setName(req.getParameter("bName"));
        b.setISBN(req.getParameter("isbn"));
        b.setPublisher(req.getParameter("publisher"));
        b.setStore_Location(req.getParameter("location"));
        b.setCopies(req.getParameter("copies"));
        b.setCategory(req.getParameter("category"));
        b.setEdition(req.getParameter("edition"));
        b.setPrice(req.getParameter("price"));
        b.setAuthor(req.getParameter("author"));
        
        if(bdao.update(b)){
            System.out.println("Book Updated Successfully...");
            resp.sendRedirect("ViewBook.jsp");
        }else{
            System.out.println("Error in updating Book...");
        }
    }
    
}
