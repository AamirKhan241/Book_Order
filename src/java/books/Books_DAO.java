package books;

import java.util.List;

public interface Books_DAO {
    
    boolean insert(Books b);
    boolean update(Books b);
    boolean delete(int Sno);
    
    Books display(int sno);
    List<Books> display();
    List<Books> display(String ISBN);
    List<Books> displayC(String category);
}
