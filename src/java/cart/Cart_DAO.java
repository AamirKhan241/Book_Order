package cart;

import java.util.List;

public interface Cart_DAO {
    
    boolean insert(Cart c);
    boolean update(Cart c);
    boolean delete(int sNo);
    double sum(int cId);
    int item(int cId);
    
    Cart display(int sNo);
    List<Cart> displayC(int cNo);
    List<Cart> display();
    
}
