package category;

import java.util.List;

public interface Category_DAO {
    
    boolean insert(Category c);
    boolean update(Category c);
    boolean delete(int id);
    
    Category display(int id);
    List<Category> display();
}
