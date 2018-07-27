package categoryOp;

import category.Category_DAO;
import category.Category_DAO_Implt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DeleteCategory")
public class DeleteCategory extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category_DAO cdao=new Category_DAO_Implt();
        if(cdao.delete(Integer.valueOf(req.getParameter("id")))){
            System.out.println("Category Deleted Successfully...");
            resp.sendRedirect("ViewCategory.jsp");
        }else{
            System.out.println("Error in deleting category...");
        }
    }
}
