package categoryOp;

import category.Category;
import category.Category_DAO;
import category.Category_DAO_Implt;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import singleCopy.ImageUpload;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = "/AddCategory")
@MultipartConfig(fileSizeThreshold = 5*1024*1024,maxFileSize = 20*1024*1024,maxRequestSize = 5*1024*1024)
public class AddCategory extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category_DAO cdao=new Category_DAO_Implt();
        Category c=new Category();
        c.setName(req.getParameter("cName"));
        c.setDescription(req.getParameter("desc"));
        
        c.setImage(singleCopy.ImageUpload.getInstance().uploadImage(req.getPart("img"), this.getServletContext().getRealPath("/")));
        
        if(cdao.insert(c)){
            System.out.println("Category added successfully added...");
            resp.sendRedirect("ViewCategory.jsp");
        }else
            System.out.println("Error in adding Category...");
    }
    
    
}
